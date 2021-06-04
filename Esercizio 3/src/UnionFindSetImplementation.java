
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an union find set implementation.
 * 
 * @author Agostino and Andrea
 * @param <T> Type of the union find set elements
 */
public class UnionFindSetImplementation<T> implements UnionFindSet<T> {

    private List<Node<T>> forest = new ArrayList<>();

    @Override
    public List<Node<T>> makeSet(List<T> set) throws UnionFindSetException {
        if (set == null)
            throw new UnionFindSetException("UnionFindSetImplementation makeSet: set parameter cannot be null.");
        if (set.isEmpty())
            throw new UnionFindSetException("UnionFindSetImplementation makeSet: set parameter cannot be empty.");

        if (!forest.isEmpty())
            forest.clear();

        for (T t : set)
            forest.add(new Node<>(t));

        return forest;
    }

    private Node<T> getRappresentante(Node<T> node) {
        if (node != node.getParent())
            node.setParent(getRappresentante(node.getParent()));

        return node.getParent();
    }

    @Override
    public Node<T> find(T value) throws UnionFindSetException {
        if (value == null)
            throw new UnionFindSetException("UnionFindSetImplementation find: value parameter cannot be null.");

        for (Node<T> node : forest)
            if (value.equals(node.getValue()))
                return getRappresentante(node);

        throw new UnionFindSetException("UnionFindSetImplementation find: value not found.");
    }

    @Override
    public List<Node<T>> union(T x, T y) throws UnionFindSetException {
        if (x == null)
            throw new UnionFindSetException("UnionFindSetImplementation union: x parameter cannot be null.");

        if (y == null)
            throw new UnionFindSetException("UnionFindSetImplementation union: y parameter cannot be null.");

        if (x.equals(y))
            return forest;

        Node<T> rX = find(x);
        Node<T> rY = find(y);

        if (rX.getRank() < rY.getRank())
            rX.setParent(rY);

        else {
            rX.setParent(rY);
            if (rY.getRank() == rX.getRank())
                rY.setRank(rY.getRank() + 1);
        }
        return forest;
    }

    public void printSet() {
        for (int i = 0; i < forest.size(); i++)
            System.out.print(forest.get(i).toString());

    }

    public boolean isEmpty() {
        return forest.isEmpty();
    }
}
