package src.graph;

import java.util.*;

/**
 * Java program to implement Graph with the help of Generics
 *  
 * @author Agostino and Andrea
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
     * @throws GraphException
     */
    public void addNode(T s) throws GraphException {
        if (s == null)
            throw new GraphException("Graph addNode: source parameter s cannot be null.");
        if (hasNode(s))
            throw new GraphException("Graph addNode: node " + s + " already exists in this Graph.");

        map.put(s, new LinkedList<>());
    }

    /**
     * This function adds the edge between source to destination node. Complexity:
     * O(1)
     * 
     * @param source      node
     * @param destination node
     * @throws GraphException
     */
    public void addEdge(T s, T d, G w) throws GraphException {
        if (s == null)
            throw new GraphException("Graph addEdge: source parameter s cannot be null.");
        if (d == null)
            throw new GraphException("Graph addEdge: destination parameter d cannot be null.");
        if (w == null)
            throw new GraphException("Graph addEdge: weight parameter w cannot be null.");

        if (!map.containsKey(s))
            addNode(s);

        if (!map.containsKey(d))
            addNode(d);

        map.get(s).add(new Edge<>(s, d, w));

        if (isNotOriented) {
            map.get(d).add(new Edge<>(d, s, w));
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
     * @throws GraphException
     */
    public boolean hasNode(T s) throws GraphException {
        if (s == null)
            throw new GraphException("Graph hasNode: source parameter s cannot be null.");
        return map.containsKey(s);
    }

    /**
     * This function gives whether an edge is present or not. Complexity: O(1)*
     * 
     * @param s: source node
     * @param d: destination node
     * @return true if the edge exist, false otherwise
     * @throws GraphException
     */
    public boolean hasEdge(T s, T d) throws GraphException {
        if (s == null)
            throw new GraphException("Graph hasEdge: source parameter s cannot be null.");
        if (d == null)
            throw new GraphException("Graph hasEdge: destination parameter d cannot be null.");

        if (map.get(s) != null)
            return map.get(s).contains(new Edge<>(s, d));

        return false;
    }

    /**
     * This function remove a defined node in the graph. Complexity: O(n).
     * 
     * @param s: source node
     * @throws GraphException
     */
    public void deleteNode(T s) throws GraphException {
        if (s == null)
            throw new GraphException("Graph deleteNode: source parameter s cannot be null.");
        if (!map.containsKey(s))
            throw new GraphException("Graph deleteNode: source parameter " + s + " not exists.");

        for (T v : map.keySet())
            if (hasEdge(v, s))
                deleteEdge(s, v);

        map.remove(s);
    }

    /**
     * This funcition remove the edge from s to d. Complexity O(1)*
     * 
     * @param s: source node
     * @param d: destination node
     * @throws GraphException
     */
    public void deleteEdge(T s, T d) throws GraphException {
        if (s == null)
            throw new GraphException("Graph deleteEdge: source parameter s cannot be null.");
        if (d == null)
            throw new GraphException("Graph deleteEdge: destination parameter d cannot be null.");
        if (!map.containsKey(s))
            throw new GraphException("Graph deleteEdge: source parameter " + s + " not exists.");
        if (!map.containsKey(d))
            throw new GraphException("Graph deleteEdge: destination parameter " + d + " not exists.");

        map.get(s).remove(new Edge<>(s, d));

        if (isNotOriented)
            map.get(d).remove(new Edge<>(d, s));

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
    public List<Edge<T, G>> getEdges() {
        List<Edge<T, G>> edgeList = new ArrayList<>();

        for (T v : map.keySet())
            for (int i = 0; i < map.get(v).size(); i++)
                edgeList.add(map.get(v).get(i));

        return edgeList;
    }

    /**
     * This function return a List containing all the adjacent node from node s.
     * Complexity: O(1)*.
     * 
     * @param s : value of the node
     * @return List<Edge<T, G>>
     * @throws GraphException
     */
    public List<T> getAdjacentNode(T s) throws GraphException {
        if (s == null)
            throw new GraphException("Graph getAdjacentNode: source parameter s cannot be null.");
        if (!map.containsKey(s))
            throw new GraphException("Graph getAdjacentNode: node " + s + " not exists.");

        List<T> resultList = new ArrayList<>();

        for (int i = 0; i < map.get(s).size(); i++)
            resultList.add(map.get(s).get(i).getDestination());

        return resultList;
    }

    /**
     * This function returns the generic value G of the label (weight) of a edge
     * Complexity: O(1)*
     * 
     * @param s
     * @param d
     * @return
     * @throws GraphException
     */
    public G getEdgeLabel(T s, T d) throws GraphException {
        if (s == null)
            throw new GraphException("Graph getEdgeLabel: source parameter s cannot be null.");
        if (d == null)
            throw new GraphException("Graph getEdgeLabel: destination parameter d cannot be null.");
        if (!hasEdge(s, d))
            throw new GraphException("Graph getEdgeLabel: edge (" + s + ", " + d + ") does not exists.");

        for (int i = 0; i < map.get(s).size(); i++)
            if (map.get(s).get(i).getDestination() == d)
                return map.get(s).get(i).getWeight();

        return null;
    }

    // Prints the adjancency list of each vertex.
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (T v : map.keySet()) {
            builder.append(v.toString().toUpperCase() + ":");
            for (Edge<T, G> edge : map.get(v))
                builder.append(edge.toString() + " ");

            builder.append("\t\n\n");
        }

        return (builder.toString());
    }
}
