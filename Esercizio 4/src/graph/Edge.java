package src.graph;

import java.util.Objects;

/**
 * Edge represent an edge with source, destination and weight. It provides
 * setters, getters and an ad hoc ovveride of toString and equals.
 * 
 * @author Agostino and Andrea
 */
public class Edge<G, T> {
    private G source;
    private G destination;
    private T weight;

    public Edge(G source, G destination) {
        this.destination = destination;
        this.source = source;
    }

    public Edge(G source, G destination, T weight) {
        this.destination = destination;
        this.source = source;
        this.weight = weight;
    }

    public G getSource() {
        return source;
    }

    public G getDestination() {
        return destination;
    }

    public T getWeight() {
        return weight;
    }

    public void setSource(G source) {
        this.source = source;
    }

    public void setDestination(G destination) {
        this.destination = destination;
    }

    public void setWeight(T weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "\n\t- S:" + source + "\tD:" + destination + "\tW:" + weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Edge<?, ?> edge = (Edge<?, ?>) o;

        return Objects.equals(source, edge.source) && Objects.equals(destination, edge.destination);
    }
}
