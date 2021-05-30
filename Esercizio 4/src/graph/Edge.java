package src.graph;

/**
 * Edge represent an edge with source, destination and weight. It provides
 * setters, getters and an ad hoc ovveride of toString and equals.
 * 
 * @author Agostino
 */
public class Edge<G, T> {
    private G source;
    private G destination;
    private T weight;

    public Edge(G destination) {
        this.destination = destination;
    }

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
        return "\tS:" + source + "\tD:" + destination + "\tW:" + weight + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Edge<?, ?> edge = (Edge<?,?>) o;

        return this.destination.equals(edge.destination);
    }
}
