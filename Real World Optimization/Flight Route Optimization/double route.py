from gurobipy import Model, GRB, quicksum
import pandas as pd
import numpy as np
import math
from haversine import haversine, Unit
import matplotlib.pyplot as plt


def geo_distance(latlong1: tuple, latlong2: tuple):
    distance = haversine(latlong1, latlong2, unit=Unit.MILES)

    return distance


def find_subtours(flights):
    # Step 1: Create a dictionary mapping airports to their destinations
    flight_map = {}
    for dep, arr in flights:
        flight_map[dep] = arr

    # Step 2: Initialize a set to track visited airports
    visited = set()
    subtours = []

    # Step 3: Traverse through the flight map to detect subtours
    def traverse(current):
        path = []
        while current not in visited:
            visited.add(current)
            path.append(current)
            current = flight_map.get(current)
            if current is None:  # If we don't have a connection
                return path, False
        return path, current == path[0]  # If we encounter a loop

    # Step 4: Try to visit all airports and check for cycles
    for airport in flight_map:
        if airport not in visited:
            path, is_subtour = traverse(airport)
            if is_subtour:  # If we encounter a cycle (subtour)
                subtours.append(path)

    if subtours:
        print("Subtours detected:")
        for subtour in subtours:
            print(" -> ".join(subtour))
    else:
        print("No subtours detected.")


def find_flight_path(flights, start_airport):
    # Create a dictionary to map each airport to its destination
    flight_map = {departure: destination for departure, destination in flights}

    # Initialize the path and a set to track visited airports
    path = []
    visited = set()

    # Start from the given starting airport
    current_airport = start_airport

    while current_airport in flight_map:
        # If the airport has already been visited, it means we've encountered a cycle
        if current_airport in visited:
            path.append(current_airport)
            return path

        # Add the current airport to the path and mark it as visited
        visited.add(current_airport)
        path.append(current_airport)

        # Move to the next airport
        current_airport = flight_map[current_airport]

    # Add the final destination airport to the path
    path.append(current_airport)

    return path


df = pd.read_csv('airports.csv')

airports = df['airport_code'].tolist()


noise = np.random.uniform(-0.05, 0.05, size=df['latitude_deg'].shape)
df['latitude_deg'] = df['latitude_deg'] * (1 + noise)
df['longitude_deg'] = df['longitude_deg'] * (1 + noise)

distances = {}
for i in range(len(airports)):
    for j in range(len(airports)):
        if i != j:
            origin = df.iloc[i]
            dest = df.iloc[j]

            orgin_latlong = (origin['latitude_deg'], origin['longitude_deg'])
            dest_latlong = (dest['latitude_deg'], dest['longitude_deg'])

            distances[origin['airport_code'], dest['airport_code']] = geo_distance(orgin_latlong, dest_latlong)

# Initialize model
model = Model("MultiDepotTSP")

X = {}

for key in distances.keys():
    X[key[0], key[1], 'A'] = model.addVar(vtype=GRB.BINARY, name=f'{key[0]}_{key[1]}_A')
    X[key[0], key[1], 'B'] = model.addVar(vtype=GRB.BINARY, name=f'{key[0]}_{key[1]}_B')

model.setObjective(
    quicksum(distances[key] * (X[key[0], key[1], 'A'] + X[key[0], key[1], 'B']) for key in distances.keys()),
    GRB.MINIMIZE)

# Constraints
for air in airports:
    outflow_a = quicksum(X[air, end, 'A'] for end in airports if air != end)
    inflow_a = quicksum(X[start, air, 'A'] for start in airports if start != air)

    outflow_b = quicksum(X[air, end, 'B'] for end in airports if air != end)
    inflow_b = quicksum(X[start, air, 'B'] for start in airports if start != air)

    model.addConstr(inflow_a == outflow_a)
    model.addConstr(inflow_b == outflow_b)

    # Each plane must leave its respective airport A -> JFK, B -> LAX
    if air == 'JFK':
        model.addConstr(outflow_a == 1)
    elif air == 'LAX':
        model.addConstr(outflow_b == 0)
    else:
        model.addConstr(inflow_a + inflow_b >= 1)

# MTZ
N = len(airports)
u1 = model.addVars(airports, vtype=GRB.CONTINUOUS, lb=1, name="u1")
u2 = model.addVars(airports, vtype=GRB.CONTINUOUS, lb=1, name="u2")

for (i, j) in distances.keys():
    if i == 'JFK' or j == 'JFK' or i == j:
        continue
    else:
        model.addConstr(u1[i] + 1 <= u1[j] + 10000 * (1 - X[i, j, 'A']))

for (i, j) in distances.keys():
    if i == 'LAX' or j == 'LAX' or i == j:
        continue
    else:
        model.addConstr(u1[i] + 1 <= u1[j] + 10000 * (1 - X[i, j, 'B']))

model.addConstr(u1['JFK'] == 1, name="u1_start")
model.addConstr(u2['LAX'] == 1, name="u2_start")

model.setParam('MIPGap', 0.1)
model.optimize()

# Results
edges_a = [(key[0], key[1]) for key in distances.keys() if X[key[0], key[1], 'A'].X > 0.5]
edges_b = [(key[0], key[1]) for key in distances.keys() if X[key[0], key[1], 'B'].X > 0.5]

path = find_flight_path(edges_a, 'JFK')

lats = [df[df['airport_code'] == air]['latitude_deg'].iloc[0] for air in path]
longs = [df[df['airport_code'] == air]['longitude_deg'].iloc[0] for air in path]

