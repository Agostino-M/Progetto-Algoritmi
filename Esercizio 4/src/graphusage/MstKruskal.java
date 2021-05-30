package src.graphusage;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import src.graph.*;
import src.unionfindset.*;

/* class Pair<T extends Comparable<T>, U extends Comparable<U>> implements Comparable<Pair<T, U>> {
    public int compare(final Pair<T, U> p1, final Pair<T, U> p2) {
        // this first compares the first field. If the first fields are the same, the
        // second fields are compared
        // If you have a different requirement, implement it accordingly.
        return Comparator.comparing(Pair::getFirst).thenComparing(Pair::getSecond).compare(p1, p2);
    }
} */

public class MstKruskal<T, G> {

    public List<Edge<T, G>> kruskal(Graph<T, G> g, Comparator<Edge<T, G>> comparator) throws UnionFindSetException {
        UnionFindSet<T> unionFindSet = new UnionFindSetImplementation<>();
        List<Node<T>> nodeList = new ArrayList<>();

        List<Edge<T, G>> result = new ArrayList<>(); // A

        for (T nodeValue : g.getNodes())
            nodeList.add(new Node<>(nodeValue));

        unionFindSet.makeSet(nodeList);

        List<Edge<T, G>> edgeList = g.getEdges(); // L

        // TODO: sorting di edgeList e ordinamento non decrescente (usare comparator)
        edgeList.sort(comparator);

        while (!edgeList.isEmpty()) {
            Edge<T, G> tempEdge = edgeList.get(0);
            edgeList.remove(0);
            T s = tempEdge.getSource();
            T d = tempEdge.getDestination();

            if (!unionFindSet.find(s).equals(unionFindSet.find(d))) {
                result.add(tempEdge);
                unionFindSet.union(s, d);
            }

        }

        return result;
    }

    public static void main(String[] args) {
        Graph<String, Integer> g = new Graph<>(true);
        EdgeComparator comparator = new EdgeComparator();

        g.addEdge("a", "b", 4);
        g.addEdge("a", "h", 8);
        g.addEdge("b", "h", 11);
        g.addEdge("b", "c", 8);
        g.addEdge("h", "i", 7);
        g.addEdge("i", "c", 2);
        g.addEdge("h", "g", 1);
        g.addEdge("i", "g", 6);
        g.addEdge("g", "f", 2);
        g.addEdge("c", "f", 4);
        g.addEdge("c", "d", 7);
        g.addEdge("b", "f", 14);
        g.addEdge("d", "e", 9);
        g.addEdge("f", "e", 10);

        List<Edge<String, Integer>> temp = kruskal(g, comparator);

    }

}
