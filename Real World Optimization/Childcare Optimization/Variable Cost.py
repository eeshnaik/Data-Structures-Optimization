from gurobipy import Model, GRB, quicksum
import pandas as pd
import numpy as np
import math
from haversine import haversine, Unit
from itertools import combinations, product


def latlong_dist(coor1: tuple, coor2: tuple):
    lat1, lon1 = coor1[0], coor1[1]
    lat2, lon2 = coor2[0], coor2[1]

    distance = haversine((lat1, lon1), (lat2, lon2), unit=Unit.MILES)

    return distance


M = 10000

income = pd.read_csv('../../../Project 1/new_income.csv')
employ = pd.read_csv('../../../Project 1/new_employment.csv')
population = pd.read_csv('../../../Project 1/new_population.csv')
potential = pd.read_csv('../../../Project 1/new_potential_loc.csv')
existing = pd.read_csv('../../../Project 1/new_child_care.csv')

# Assign ids to potential facilities (handy later)
potential['potential_id'] = range(len(potential))

# Only want ages 0-14
population = population[['zip_code', '-5', '5-9', '10-14']]

population.rename(columns={'-5': '0-5'}, inplace=True)

# TA formula
population['0-12'] = np.floor((3 / 5) * population['10-14'] + population['0-5'] + population['5-9'])

# Combine infant, toddler, and preschool capacity into age 0-5 capacity column (TA's formulas)
existing['0-5 capacity'] = np.floor(
    existing['infant_capacity'] + existing['toddler_capacity'] + existing['preschool_capacity'] + (5 / 12) * existing[
        'children_capacity'])

# Drop facilities with total capacity of 0, (there were one or two)
existing = existing[existing['total_capacity'] != 0]

master_df = employ
master_df['income'] = income['average income']
master_df['0-5 pop'] = population['0-5']
master_df['0-12 pop'] = population['0-12']

master_df['is_hd'] = np.where((master_df['employment rate'] >= 0.60) | (master_df['income'] <= 60000), 1, 0)

m = Model('model')

master_cost_objective = 0
master_vars = {}

# How many rows to run the optimization on (useful for debugging). Master_df is whole thing
nrows = len(master_df)

# nrows = 5

# Iterate through zip codes
for i in range(nrows):

    row = master_df.iloc[i]
    zip1 = row['zip_code']

    # Dataframe of existing facilities in zip1
    current = existing[existing['zip_code'] == zip1]
    current_lats = current['latitude'].tolist()
    current_longs = current['longitude'].tolist()
    facility_ids = current['facility_id'].tolist()

    # Dataframe of potential building locations in zip1
    build_locs = potential[potential['zip_code'] == zip1]
    potential_lats = build_locs['latitude'].tolist()
    potential_longs = build_locs['longitude'].tolist()
    potential_ids = build_locs['potential_id'].tolist()

    # Hold binary variables for whether to build small, med, large in a potential location
    small = []
    med = []
    large = []

    build_cost_exp = 0
    expans_cost_exp = 0

    # Iterate through potential locations in zip1
    for l in range(len(build_locs)):
        sm = m.addVar(vtype=GRB.BINARY, name=f'{int(zip1)}_{potential_ids[l]}_small')
        md = m.addVar(vtype=GRB.BINARY, name=f'{int(zip1)}_{potential_ids[l]}_med')
        lg = m.addVar(vtype=GRB.BINARY, name=f'{int(zip1)}_{potential_ids[l]}_large')

        small.append(sm)
        med.append(md)
        large.append(lg)

        # Store variables in dictionary to see in output after problem is done
        master_vars[int(zip1), potential_ids[l], 's'] = sm
        master_vars[int(zip1), potential_ids[l], 'm'] = md
        master_vars[int(zip1), potential_ids[l], 'l'] = lg

        # Can only build 1 building in a potential location
        m.addConstr(sm + md + lg <= 1, name=f'only1size_{int(zip1)}_{potential_ids[l]}')

        build_cost_exp += (65000 * sm + 95000 * md + 115000 * lg)

    # Location constraints between all potential locations
    potential_pairs = list(combinations(range(len(potential_lats)), 2))
    for p in potential_pairs:
        loc1 = (potential_lats[p[0]], potential_longs[p[0]])
        loc2 = (potential_lats[p[1]], potential_longs[p[1]])

        # If too close, can only build 1 in both locations
        if latlong_dist(loc1, loc2) <= 0.06:
            m.addConstr(small[p[0]] + med[p[0]] + large[p[0]] + small[p[1]] + med[p[1]] + large[p[1]] <= 1, name=f'too_close_{potential_ids[p[0]]}_{potential_ids[p[1]]}_{int(zip1)}')

    # Location constraints between all potential and existing locations
    potential_existing_pairs = list(product(range(len(potential_lats)), range(len(current_lats))))
    for p in potential_existing_pairs:
        loc1 = (potential_lats[p[0]], potential_longs[p[0]])
        loc2 = (current_lats[p[1]], current_longs[p[1]])

        # If latitude or longitude is missing (1 or 2 are) continue
        if np.isnan(loc2[0]) or np.isnan(loc2[1]):
            continue

        # If too close to existing, don't build at the potential location
        if latlong_dist(loc1, loc2) <= 0.06:
            m.addConstr(small[p[0]] + med[p[0]] + large[p[0]] == 0, name=f'too_close_{potential_ids[p[0]]}_{facility_ids[p[1]]}_{int(zip1)}')

    # If there is atleast one existing facility in that zipcode
    if len(current['facility_id']) > 0:

        # Get total, 05 capacities for each existing facility
        total_capacities = list(current['total_capacity'])
        zerotofive_capacities = list(current['0-5 capacity'])

        # Create integer vars for # of 0-5 and 5-12 slots to add to each facility in the current zip code
        expans05 = [m.addVar(vtype=GRB.INTEGER, lb=0, name=f'exp05_{int(zip1)}_{facility["facility_id"]}') for
                    idx, facility in current.iterrows()]
        expans512 = [m.addVar(vtype=GRB.INTEGER, lb=0, name=f'exp512_{int(zip1)}_{facility["facility_id"]}') for
                     idx, facility in current.iterrows()]

        # Store variables in dictionary to see in output after problem is done
        for i in range(len(expans05)):
            master_vars[int(zip1), facility_ids[i], '05'] = expans05[i]
            master_vars[int(zip1), facility_ids[i], '512'] = expans512[i]

        # Expansion cannot result in more than 500 total slots at each facility
        for j in range(len(expans05)):
            if total_capacities[j] >= 500:
                m.addConstr(expans05[j] + expans512[j] == 0, name=f'maxslots_{zip1}_{facility_ids[j]}')
            else:
                max_capacity = min(500, 1.2 * total_capacities[j])
                m.addConstr(expans05[j] + expans512[j] + total_capacities[j] <= max_capacity,
                            name=f'maxslots_{zip1}_{facility_ids[j]}')

        new_totalcapacity_byzip_exp = quicksum(
            small[i] * 100 + med[i] * 200 + large[i] * 400 for i in range(len(build_locs))) + quicksum(
            expans05[i] + expans512[i] + total_capacities[i] for i in range(len(expans05)))

        new_05capacity_byzip_exp = quicksum(
            small[i] * 50 + med[i] * 100 + large[i] * 200 for i in range(len(build_locs))) + quicksum(
            expans05[i] + zerotofive_capacities[i] for i in range(len(expans05)))

        # High/low demand constraints
        if row['is_hd'] == 1:
            m.addConstr(new_totalcapacity_byzip_exp >= (1 / 2) * row['0-12 pop'],
                        name=f'{zip1}_totaldemand')
            m.addConstr(new_05capacity_byzip_exp >= (2 / 3) * row['0-5 pop'], name=f'{zip1}_05demand')

        else:
            m.addConstr(new_totalcapacity_byzip_exp >= (1 / 3) * row['0-12 pop'],
                        name=f'{zip1}_totaldemand')
            m.addConstr(new_05capacity_byzip_exp >= (2 / 3) * row['0-5 pop'], name=f'{zip1}_05demand')

        for k in range(len(expans05)):
            # TA's formula
            exp_rate = (expans05[k] + expans512[k]) / total_capacities[k]

            x1 = m.addVar(vtype=GRB.CONTINUOUS, lb=0, name=f'{zip1}_{facility_ids[k]}_exp_<0.10')
            x2 = m.addVar(vtype=GRB.CONTINUOUS, lb=0, name=f'{zip1}_{facility_ids[k]}_exp_<0.15')
            x3 = m.addVar(vtype=GRB.CONTINUOUS, lb=0, name=f'{zip1}_{facility_ids[k]}_exp_<0.2')

            y1 = m.addVar(vtype=GRB.BINARY, name=f'{zip1}_{facility_ids[k]}_exp_<0.10_ind')
            y2 = m.addVar(vtype=GRB.BINARY, name=f'{zip1}_{facility_ids[k]}_exp_<0.15_ind')
            y3 = m.addVar(vtype=GRB.BINARY, name=f'{zip1}_{facility_ids[k]}_exp_<0.2_ind')

            m.addConstr(x1 <= 0.1*y1)
            m.addConstr(x2 <= 0.05*y2)
            m.addConstr(x3 <= 0.05*y3)

            m.addConstr(x1 + x2 + x3 == exp_rate)

            m.addConstr(y2 <= y1)
            m.addConstr(y3 <= y2)

            m.addConstr(0.1 - x1 <= M*(1 - y2))
            m.addConstr(0.05 - x2 <= M * (1 - y3))

            expans_cost_exp += (100 * expans05[k])

            expans_cost_exp += ((20000 + 200 * total_capacities[k]) * x1) * y1
            expans_cost_exp += ((20000 + 400 * total_capacities[k]) * x2) * y2
            expans_cost_exp += ((20000 + 1000 * total_capacities[k]) * x3) * y3

    # If there are no facilities in that zipcode
    else:

        # New total capacity = built capacity only (no facilities to expand)
        new_totalcapacity_byzip_exp = quicksum(
            small[i] * 100 + med[i] * 200 + large[i] * 400 for i in range(len(build_locs)))
        new_05capacity_byzip_exp = quicksum(
            small[i] * 50 + med[i] * 100 + large[i] * 200 for i in range(len(build_locs)))

        # High/low demand constraints
        if row['is_hd'] == 1:
            m.addConstr(new_totalcapacity_byzip_exp >= (1 / 2) * master_df['0-12 pop'].iloc[i],
                        name=f'{zip1}_totaldemand')
            m.addConstr(new_05capacity_byzip_exp >= (2 / 3) * master_df['0-5 pop'].iloc[i], name=f'{zip1}_05demand')

        else:
            m.addConstr(new_totalcapacity_byzip_exp >= (1 / 3) * master_df['0-12 pop'].iloc[i],
                        name=f'{zip1}_totaldemand')
            m.addConstr(new_05capacity_byzip_exp >= (2 / 3) * master_df['0-5 pop'].iloc[i], name=f'{zip1}_05demand')

    master_cost_objective += build_cost_exp + expans_cost_exp

m.setObjective(master_cost_objective, GRB.MINIMIZE)
m.update()
m.optimize()


# Create output csv to check work
# output = []
# for i in range(nrows):
#     small_buildings = 0
#     med_buildings = 0
#     large_buildings = 0
#
#     zip1 = master_df.iloc[i]['zip_code']
#     current = existing[existing['zip_code'] == zip1]
#     build_locs = potential[potential['zip_code'] == zip1]
#
#     pot_ids = build_locs['potential_id'].tolist()
#
#     for id in pot_ids:
#
#         small_buildings += master_vars[int(zip1), id, 's'].X
#         med_buildings += master_vars[int(zip1), id, 'm'].X
#         large_buildings += master_vars[int(zip1), id, 'l'].X
#
#     existing_ids = current['facility_id'].tolist()
#
#     zerofive_added = 0
#     fivetwelve_added = 0
#     total_existing = 0
#
#     for id in existing_ids:
#         zerofive_added += master_vars[int(zip1), id, '05'].X
#         fivetwelve_added += master_vars[int(zip1), id, '512'].X
#
#     row = {
#         'zip_code': zip1,
#         '0-5_slots_built': 50*small_buildings + 100*med_buildings + 200*large_buildings,
#         'total_slots_built': 100*small_buildings + 200*med_buildings + 400*large_buildings,
#         'small_buildings': small_buildings,
#         'med_buildings': med_buildings,
#         'large_buildings': large_buildings,
#         '05_added': zerofive_added,
#         '512_added': fivetwelve_added,
#         '012_added': zerofive_added + fivetwelve_added,
#         '012_existing': sum(current['total_capacity']),
#         '05_existing': sum(current['0-5 capacity']),
#         'new_total_05': 50*small_buildings + 100*med_buildings + 200*large_buildings + zerofive_added + sum(current['0-5 capacity']),
#         'new_total_012': sum(current['total_capacity']) + zerofive_added + fivetwelve_added + 100*small_buildings + 200*med_buildings + 400*large_buildings,
#         '05_pop': master_df.iloc[i]['0-5 pop'],
#         '012_pop': master_df.iloc[i]['0-12 pop'],
#         'is_hd': master_df.iloc[i]['is_hd']
#     }
#
#     output.append(row)
#
# output_df = pd.DataFrame(output)
# output_df.to_csv('Q2output.csv')
