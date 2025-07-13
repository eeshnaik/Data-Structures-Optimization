from gurobipy import Model, GRB, quicksum
import pandas as pd
import numpy as np
import math
from haversine import haversine, Unit
import matplotlib.pyplot as plt


def geo_distance(latlong1: tuple, latlong2: tuple):
    distance = haversine(latlong1, latlong2, unit=Unit.MILES)

    return round(distance, 2)


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


def find_path(flights, start='JFK', end='LAX'):
    # Step 1: Create a dictionary of flights
    flight_map = {}
    for dep, arr in flights:
        flight_map[dep] = arr

    # Step 2: Initialize the path with the starting airport
    path = [start]
    current_airport = start

    # Step 3: Traverse the path until we reach the destination airport
    while current_airport != end:
        if current_airport in flight_map:
            current_airport = flight_map[current_airport]
            path.append(current_airport)
        else:
            # If there is no direct connection from the current airport, we can't complete the path
            print("No valid path from", start, "to", end)
            return None

    # Return the complete path
    print(' -> '.join(path))


df = pd.read_csv('airports.csv')

n = len(df)
df = df[:10]

airports = df['airport_code']

distances = {}
for i in range(len(airports)):
    for j in range(len(airports)):
        if i != j:
            origin = df.iloc[i]
            dest = df.iloc[j]

            orgin_latlong = (origin['latitude_deg'], origin['longitude_deg'])
            dest_latlong = (dest['latitude_deg'], dest['longitude_deg'])

            distances[origin['airport_id']+1, dest['airport_id']+1] = geo_distance(orgin_latlong, dest_latlong)


num_nodes = len(airports)

mtz = Model("TSP_MTZ")

# Create variables
vars = mtz.addVars([(i, j) for i in range(1, num_nodes + 1) for j in range(1, num_nodes + 1) if i != j],
                     vtype=GRB.BINARY, name="e")

# Subtour elimination decision variables
u = mtz.addVars(range(1, num_nodes + 1), vtype=GRB.CONTINUOUS, name="u", lb=1, ub=num_nodes)

# Objective: minimize the total distance traveled
mtz.setObjective(quicksum(vars[i, j] * distances[i, j]
                               for i in range(1, num_nodes + 1) for j in range(1, num_nodes + 1) if i != j), GRB.MINIMIZE)

# Constraints
# Each node is entered and left exactly once
for i in range(1, num_nodes + 1):
    mtz.addConstr(quicksum(vars[i, j] for j in range(1, num_nodes + 1) if i != j) == 1)
    mtz.addConstr(quicksum(vars[j, i] for j in range(1, num_nodes + 1) if i != j) == 1)

# Subtour elimination constraints (MTZ)
for i in range(2, num_nodes + 1):
    for j in range(2, num_nodes + 1):
        if i != j:
            mtz.addConstr(u[i] +1 <= u[j] + ( num_nodes -1) * (1-vars[i, j]))

mtz.addConstr(u[1] == 1)  # Fix the position of the first node

mtz.setParam('MIPGap', 0.01)
# Solve the model
mtz.optimize()

edges = [(df.iloc[i-1]['airport_code'], df.iloc[j-1]['airport_code']) for i in range(1, num_nodes + 1) for j in range(1, num_nodes + 1) if i != j and vars[i, j].X > 0.5].remove(('JFK', 'LAX'))
idxs = [(i, j) for i in range(1, num_nodes + 1) for j in range(1, num_nodes + 1) if i != j and vars[i, j].X > 0.5]

print(quicksum(distances[ed] for ed in idxs))
find_path(edges)




