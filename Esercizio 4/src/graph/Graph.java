package src.graph;

import java.util.*;

/**
 * Java program to implement Graph with the help of Generics
 * 
 * @author Agostino
 */
public class Graph<T, G> {

    // We use Hashmap to store the edges in the graph
    private Map<T, List<Edge<T, G>>> map;
    private boolean isNotOriented;

    public Graph(boolean isNotOriented) {
        map = new HashMap<>();
        this.isNotOriented = isNotOriented;
    }

    /**
     * This function adds a new node to the graph. Complexity: O(1)
     * 
     * @param s: value of the new node
     */
    public void addNode(T s) {
        map.put(s, new LinkedList<>());
    }

    /**
     * This function adds the edge between source to destination node. Complexity:
     * O(1)
     * 
     * @param source      node
     * @param destination node
     */
    public void addEdge(T source, T destination, G weight) {
        if (!map.containsKey(source))
            addNode(source);

        if (!map.containsKey(destination))
            addNode(destination);

        map.get(source).add(new Edge<>(source, destination, weight));

        if (isNotOriented) {
            map.get(destination).add(new Edge<>(destination, source, weight));
        }
    }

    /**
     * Complexity: O(1)
     * 
     * @return true if the graph is NOT oriented, false otherwise
     */
    public boolean getIsNotOriented() {
        return isNotOriented;
    }

    /**
     * This function gives whether a node is present or not. Complexity: O(1).
     * 
     * @param s: value of the node to find in the Graph
     * @return true if the node exist, false otherwise
     */
    public boolean hasNode(T s) {
        return map.containsKey(s);
    }

    /**
     * This function gives whether an edge is present or not. Complexity: O(1)*
     * 
     * @param s: source node
     * @param d: destination node
     * @return true if the edge exist, false otherwise
     */
    public boolean hasEdge(T s, T d) {
        return map.get(s).contains(new Edge<>(s, d));
    }

    /**
     * This funcition remove the edge from s to d. Complexity O(1)*
     * 
     * @param s: source node
     * @param d: destination node
     */
    public void deleteEdge(T s, T d) {
        if (map.containsKey(s) && map.containsKey(d)) {
            map.get(s).remove(new Edge<>(s, d));

            if (isNotOriented)
                map.get(d).remove(new Edge<>(d, s));

        }
    }

    /**
     * This function remove a defined node in the graph. Complexity: O(n).
     * 
     * @param s: source node
     */
    public void deleteNode(T s) {
        for (T v : map.keySet())
            deleteEdge(s, v);

        map.remove(s);
    }

    /**
     * This function gives the count of nodes. Complexity O(1).
     * 
     * @return the number of nodes in the graph
     */
    public int getNodeCount() {
        return map.keySet().size();
    }

    /**
     * This function gives the count of edges. Complexity O(n).
     * 
     * @return the number of edges in the graph
     */
    public int getEdgesCount() {
        int count = 0;
        for (T v : map.keySet()) {
            count += map.get(v).size();
        }

        if (isNotOriented) {
            count = count / 2;
        }
        return count;
    }

    /**
     * This function returns a List containing all the nodes of the graph.
     * Complexity O(n).
     * 
     * @return List<T>
     */
    public List<T> getNodes() {
        List<T> nodeList = new ArrayList<>();

        for (T v : map.keySet())
            nodeList.add(v);

        return nodeList;
    }

    /**
     * This function returns a List containing all the nodes of the graph.
     * Complexity O(n).
     * 
     * @return List<Edge<T>>
     */
    public List<Edge<T,G>> getEdges() {
        List<Edge<T,G>> edgeList = new ArrayList<>();
        List<Edge<T,G>> tempList;

        for (T v : map.keySet()) {
            tempList = map.get(v);

            for (int i = 0; i < tempList.size(); i++) {
                edgeList.add(tempList.get(i));
            }
        }

        return edgeList;
    }

    /**
     * This function return a List containing all the adjacent node from node s.
     * Complexity: O(1)*.
     * 
     * @param s: value of the
     * @return the list
     */
    public List<Edge<T,G>> getAdjacentNode(T s) {
        return map.get(s);
    }

    // Prints the adjancency list of each vertex.
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (T v : map.keySet()) {
            builder.append(v.toString() + ": ");
            for (Edge<T,G> w : map.get(v)) {
                builder.append(w.toString() + " ");
            }
            builder.append("\n");
        }

        return (builder.toString());
    }
}

class Main {

    public static void main(String args[]) {

        // Object of graph is created.
        Graph<Integer, Integer> g = new Graph<>(false);

        // edges are added.
        // Since the graph is bidirectional,
        // so boolean bidirectional is passed as true.
        g.addEdge(0, 1, 5);
        g.addEdge(0, 4, 4);
        g.addEdge(1, 2, 6);
        g.addEdge(1, 3, 8);
        g.addEdge(1, 4, 12);
        g.addEdge(2, 3, 41);
        g.addEdge(3, 4, 9);

        // print the graph.
        System.out.println("Graph:\n" + g.toString());

        // gives the no of vertices in the graph.
        System.out.println("Node count: " + g.getNodeCount());

        // gives the no of edges in the graph.
        System.out.println("Edge count: " + g.getEdgesCount());

        // tells whether the edge is present or not.
        System.out.println("Edge(3,4): " + g.hasEdge(3, 4));

        // tells whether vertex is present or not
        System.out.println("Node(5): " + g.hasNode(5));

        // tells whether vertex is present or not
        System.out.println("deleteNode(2):\n");
        g.deleteNode(2);

        System.out.println("\nGraph:\n" + g.toString());

        System.out.println("deleteEdge(3,4):\n");
        g.deleteEdge(3, 4);

        System.out.println("\nGraph:\n" + g.toString());

        System.out.println("Node(2): " + g.hasNode(2));

        System.out.println("Edge(3,2): " + g.hasEdge(3, 2));

        System.out.println("Lista nodi adiacenti a 1: " + g.getAdjacentNode(1).toString());

        System.out.println("Lista nodi del grafo: " + g.getNodes().toString());

        System.out.println("Lista archi del grafo: " + g.getEdges().toString());

        System.out.println("Non orientato? " + g.getIsNotOriented());

    }
}
