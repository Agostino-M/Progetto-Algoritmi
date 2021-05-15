package src;

public class Node<T> {
    public T element;
    public Node<T> parent;

    public T getElement() {
        return element;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }
}
