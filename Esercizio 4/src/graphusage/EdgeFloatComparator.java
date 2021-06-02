package src.graphusage;

import java.util.Comparator;
import src.graph.Edge;

/**
 * 
 * @author Agostino and Andrea
 */
public class EdgeFloatComparator implements Comparator<Edge<String, Float>> {
    
    @Override
    public int compare(Edge<String, Float> r1, Edge<String, Float> r2) {
        return Float.compare(r1.getWeight(), r2.getWeight());
    }

}