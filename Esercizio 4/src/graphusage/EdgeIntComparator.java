package src.graphusage;

import java.util.Comparator;
import src.graph.Edge;

public class EdgeIntComparator implements Comparator<Edge<String, Integer>> {

    @Override
    public int compare(Edge<String, Integer> r1, Edge<String, Integer> r2) {
        return Integer.compare(r1.getWeight(), r2.getWeight());
    }

}
