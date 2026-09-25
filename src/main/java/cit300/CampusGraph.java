package cit300;

import java.util.*;

public class CampusGraph {

    // Adjacency list: each location maps to a list of directly connected locations
    private Map<String, List<String>> adjacencyList;

    public CampusGraph() {
        adjacencyList = new LinkedHashMap<>(); // preserves insertion order for predictable display
    }

    // Add a new campus location (vertex). Returns false if it already exists.
    public boolean addLocation(String locationName) {
        if (adjacencyList.containsKey(locationName)) {
            return false; // duplicate location
        }
        adjacencyList.put(locationName, new ArrayList<>());
        return true;
    }

    // Remove a campus location and all connections/roads to and from it.
    // Returns false if the location doesn't exist.
    public boolean removeLocation(String locationName) {
        if (!adjacencyList.containsKey(locationName)) {
            return false;
        }
        adjacencyList.remove(locationName);
        // Remove any edges pointing to this location from other locations
        for (List<String> neighbours : adjacencyList.values()) {
            neighbours.remove(locationName);
        }
        return true;
    }

    // Add a connection/road (edge) between two locations. Undirected: adds both directions.
    // Returns false if either location doesn't exist, or the connection already exists.
    public boolean addConnection(String location1, String location2) {
        if (!adjacencyList.containsKey(location1) || !adjacencyList.containsKey(location2)) {
            return false; // one or both locations don't exist
        }
        if (adjacencyList.get(location1).contains(location2)) {
            return false; // connection already exists
        }
        adjacencyList.get(location1).add(location2);
        adjacencyList.get(location2).add(location1);
        return true;
    }

    // Remove a connection/road between two locations. Returns false if it doesn't exist.
    public boolean removeConnection(String location1, String location2) {
        if (!adjacencyList.containsKey(location1) || !adjacencyList.containsKey(location2)) {
            return false;
        }
        boolean removed1 = adjacencyList.get(location1).remove(location2);
        boolean removed2 = adjacencyList.get(location2).remove(location1);
        return removed1 && removed2;
    }

    // Display the full campus network: each location and its direct connections
    public void displayNetwork() {
        if (adjacencyList.isEmpty()) {
            System.out.println("No campus locations added yet.");
            return;
        }
        System.out.println("----- Campus Network (Adjacency List) -----");
        for (String location : adjacencyList.keySet()) {
            System.out.println(location + " -> " + adjacencyList.get(location));
        }
    }

    // Display neighbours (directly connected locations) of a single location
    public void displayNeighbours(String locationName) {
        if (!adjacencyList.containsKey(locationName)) {
            System.out.println("Location not found: " + locationName);
            return;
        }
        System.out.println(locationName + " is connected to: " + adjacencyList.get(locationName));
    }

    // Breadth-First Search traversal starting from a given location
    public List<String> bfsTraversal(String startLocation) {
        List<String> visitedOrder = new ArrayList<>();
        if (!adjacencyList.containsKey(startLocation)) {
            System.out.println("Start location not found: " + startLocation);
            return visitedOrder;
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(startLocation);
        visited.add(startLocation);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            visitedOrder.add(current);
            for (String neighbour : adjacencyList.get(current)) {
                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    queue.add(neighbour);
                }
            }
        }
        return visitedOrder;
    }

    // Depth-First Search traversal starting from a given location
    public List<String> dfsTraversal(String startLocation) {
        List<String> visitedOrder = new ArrayList<>();
        if (!adjacencyList.containsKey(startLocation)) {
            System.out.println("Start location not found: " + startLocation);
            return visitedOrder;
        }
        Set<String> visited = new HashSet<>();
        dfsHelper(startLocation, visited, visitedOrder);
        return visitedOrder;
    }

    private void dfsHelper(String current, Set<String> visited, List<String> visitedOrder) {
        visited.add(current);
        visitedOrder.add(current);
        for (String neighbour : adjacencyList.get(current)) {
            if (!visited.contains(neighbour)) {
                dfsHelper(neighbour, visited, visitedOrder);
            }
        }
    }

    public boolean locationExists(String locationName) {
        return adjacencyList.containsKey(locationName);
    }

    public int getLocationCount() {
        return adjacencyList.size();
    }
}