/**
 * This class represents a sigle generic node object in the UnionFindSet tree.
 * It provides setters, getters and a redefinition of equals and toString.
 * 
 * @author Agostino and Andrea
 * @param <T> Type of the Node stored into an arraylist in UnionFindSet class
 */
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Node<?> node = (Node<?>) o;

        if (parent == null || value == null || !this.value.equals(node.value) || this.rank != node.rank
                || !parent.value.equals(node.parent.value) || parent.rank != node.parent.rank)
            return false;

        return true;
    }

    @Override
    public String toString() {
        String temp = "Valore: " + value;

        if (this == this.parent)
            temp += " (R)";

        return temp + "\n";
    }
}
