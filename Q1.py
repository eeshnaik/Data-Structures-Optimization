from gurobipy import Model, GRB, quicksum
import pandas as pd
import numpy as np
import math
from haversine import haversine, Unit
import matplotlib.pyplot as plt


def haversine(latlong1: tuple, latlong2: tuple):

    distance = haversine(latlong1, latlong2, unit=Unit.MILES)

    return distance


income = pd.read_csv('new_income.csv')
employ = pd.read_csv('new_employment.csv')
population = pd.read_csv('new_population.csv')
potential = pd.read_csv('new_potential_loc.csv')
existing = pd.read_csv('new_child_care.csv')

# Only want ages 0-14
population = population[['zip_code', '-5', '5-9', '10-14']]

population.rename(columns={'-5': '0-5'}, inplace=True)

# TA formula
population['0-12'] = np.floor((3 / 5)*population['10-14'] + population['0-5'] + population['5-9'])

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

nrows = len(master_df)

# Iterate through zip codes
for i in range(nrows):

    row = master_df.iloc[i]
    zip1 = row['zip_code']
    # number of small, med, large to build in that zip
    xs = m.addVar(vtype=GRB.INTEGER, lb=0, name=f'{int(zip1)}_small')
    xm = m.addVar(vtype=GRB.INTEGER, lb=0, name=f'{int(zip1)}_med')
    xl = m.addVar(vtype=GRB.INTEGER, lb=0, name=f'{int(zip1)}_large')

    master_vars[int(zip1), 'small'] = xs
    master_vars[int(zip1), 'med'] = xm
    master_vars[int(zip1), 'large'] = xl

    build_cost_exp = (65000 * xs + 95000 * xm + 115000 * xl)
    expans_cost_exp = 0

    # Dataframe of existing facilities in zip1
    current = existing[existing['zip_code'] == zip1]

    # If there is atleast one existing facility in that zipcode
    if len(current['facility_id']) > 0:

        # Get total, 05 capacities for each existing facility
        total_capacities = list(current['total_capacity'])
        zerotofive_capacities = list(current['0-5 capacity'])
        facility_ids = list(current['facility_id'])

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
                max_capacity = min(500, 1.2*total_capacities[j])
                m.addConstr(expans05[j] + expans512[j] + total_capacities[j] <= max_capacity, name=f'maxslots_{zip1}_{facility_ids[j]}')

        new_totalcapacity_byzip_exp = (xs * 100 + xm * 200 + xl * 400) + quicksum(
            expans05[i] + expans512[i] + total_capacities[i] for i in range(len(expans05)))

        new_05capacity_byzip_exp = (xs * 50 + xm * 100 + xl * 200) + quicksum(
            expans05[i] + zerotofive_capacities[i] for i in range(len(expans05)))

        # High/low demand constraints
        if row['is_hd'] == 1:
            m.addConstr(new_totalcapacity_byzip_exp >= (1/2) * row['0-12 pop'], name=f'{zip1}_totaldemand')
            m.addConstr(new_05capacity_byzip_exp >= (2/3) * row['0-5 pop'], name=f'{zip1}_05demand')

        else:
            m.addConstr(new_totalcapacity_byzip_exp >= (1/3) * row['0-12 pop'], name=f'{zip1}_totaldemand')
            m.addConstr(new_05capacity_byzip_exp >= (2/3) * row['0-5 pop'], name=f'{zip1}_05demand')

        for k in range(len(expans05)):
            # TA's formula
            expans_cost_exp += (20000 + 200 * total_capacities[k]) * ((expans05[k]+expans512[k])/total_capacities[k]) + (100*expans05[k])

    # If there are no facilities in that zipcode
    else:

        new_totalcapacity_byzip_exp = (xs * 100 + xm * 200 + xl * 400)
        new_05capacity_byzip_exp = (xs * 50 + xm * 100 + xl * 200)

        # High/low demand constraints
        if row['is_hd'] == 1:
            m.addConstr(new_totalcapacity_byzip_exp >= (1/2) * row['0-12 pop'], name=f'{zip1}_totaldemand')
            m.addConstr(new_05capacity_byzip_exp >= (2/3) * row['0-5 pop'], name=f'{zip1}_05demand')

        else:
            m.addConstr(new_totalcapacity_byzip_exp >= (1/3) * row['0-12 pop'], name=f'{zip1}_totaldemand')
            m.addConstr(new_05capacity_byzip_exp >= (2/3) * row['0-5 pop'], name=f'{zip1}_05demand')

    master_cost_objective += build_cost_exp + expans_cost_exp

m.setObjective(master_cost_objective, GRB.MINIMIZE)

m.update()
m.optimize()

# Create output csv to check work
output = []
for i in range(nrows):
    small_buildings = 0
    med_buildings = 0
    large_buildings = 0

    zip1 = master_df.iloc[i]['zip_code']
    current = existing[existing['zip_code'] == zip1]

    small_buildings += master_vars[int(zip1), 'small'].X
    med_buildings += master_vars[int(zip1), 'med'].X
    large_buildings += master_vars[int(zip1), 'large'].X

    existing_ids = current['facility_id'].tolist()

    zerofive_added = 0
    fivetwelve_added = 0
    total_existing = 0

    for id in existing_ids:
        zerofive_added += master_vars[int(zip1), id, '05'].X
        fivetwelve_added += master_vars[int(zip1), id, '512'].X

    row = {
        'zip_code': zip1,
        '0-5_slots_built': 50*small_buildings + 100*med_buildings + 200*large_buildings,
        'total_slots_built': 100*small_buildings + 200*med_buildings + 400*large_buildings,
        'small_buildings': small_buildings,
        'med_buildings': med_buildings,
        'large_buildings': large_buildings,
        '05_added': zerofive_added,
        '512_added': fivetwelve_added,
        '012_added': zerofive_added + fivetwelve_added,
        '012_existing': sum(current['total_capacity']),
        '05_existing': sum(current['0-5 capacity']),
        'new_total_05': 50*small_buildings + 100*med_buildings + 200*large_buildings + zerofive_added + sum(current['0-5 capacity']),
        'new_total_012': sum(current['total_capacity']) + zerofive_added + fivetwelve_added + 100*small_buildings + 200*med_buildings + 400*large_buildings,
        '05_pop': master_df.iloc[i]['0-5 pop'],
        '012_pop': master_df.iloc[i]['0-12 pop'],
        'is_hd': master_df.iloc[i]['is_hd']
    }

    output.append(row)

output_df = pd.DataFrame(output)
output_df.to_csv('Q1output.csv')
