package src;

public class Node<T> {
    private T value;
    private Node<T> parent;
    private int rank;

    public Node(T value) {
        this.value = value;
        this.parent = this;
        this.rank = 0;
    }

    public T getValue() {
        return value;
    }

    public Node<T> getParent() {
        return parent;
    }

    public int getRank() {
        return rank;
    }

    public void setValue(T element) {
        this.value = element;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
