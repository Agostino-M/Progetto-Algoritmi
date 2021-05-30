package src.graphusage;

import java.util.Comparator;
import src.graph.Edge;

/**
 * @author Agostino
 */
public class EdgeComparator<T extends Comparable<T>, G extends Comparable<G>> extends MstKruskal<T, G>
        implements Comparable<Edge<T, G>> {

    public int compare(Edge<String, Float> r1, Edge<String, Float> r2) {
        return Float.compare(r1.getWeight(), r2.getWeight());
    }

    @Override
    public int compareTo(Edge<T, G> o) {

        return 0;
    }
}

// compcoppia<Edge<T,G>, Edge<T,G>>