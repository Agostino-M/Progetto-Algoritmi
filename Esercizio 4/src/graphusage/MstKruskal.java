package src.graphusage;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

import src.graph.*;
import src.unionfindset.*;

public class MstKruskal<T, G> {

    /**
     * This function finds a minimum spanning forest of a undirected edge-weighted graph. 
     * @param g : the undirected graph
     * @param comparator : used for sorting the edge list
     * @return the edge list of the MST
     * @throws UnionFindSetException
     */
    public List<Edge<T, G>> kruskal(Graph<T, G> g, Comparator<Edge<T, G>> comparator) throws UnionFindSetException {
        UnionFindSet<T> unionFindSet = new UnionFindSetImplementation<>();
        List<Edge<T, G>> result = new ArrayList<>(); // A
        List<Edge<T, G>> edgeList = g.getEdges(); // L

        edgeList.sort(comparator);
        unionFindSet.makeSet(g.getNodes());

        while (!edgeList.isEmpty()) {
            Edge<T, G> tempEdge = edgeList.get(0);
            edgeList.remove(0);
            T s = tempEdge.getSource();
            T d = tempEdge.getDestination();

            if (unionFindSet.find(s) != unionFindSet.find(d)) {
                result.add(tempEdge);
                unionFindSet.union(s, d);
            }
        }

        return result;
    }
}
