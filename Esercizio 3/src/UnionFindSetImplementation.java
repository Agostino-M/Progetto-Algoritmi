
import java.util.ArrayList;

/**
 * This class represents an union find set implementation.
 * 
 * @author Agostino
 * @param <T>; Type of the union find set elements
 */
public class UnionFindSetImplementation<T> { // implements UnionFindSet<T>

    private ArrayList<Node<T>> forest = new ArrayList<>();

    /**
     * 
     * @param set: the set from where initialize the list structure
     * @return the new arraylist
     * @throws UnionFindSetException if the set parameter is null
     */
    public ArrayList<Node<T>> makeSet(ArrayList<Node<T>> set) throws UnionFindSetException {
        if (set == null)
            throw new UnionFindSetException("UnionFindSetImplementation makeSet: set parameter cannot be null.");
        if (set.isEmpty())
            throw new UnionFindSetException("UnionFindSetImplementation makeSet: set parameter cannot be empty.");

        if (!forest.isEmpty())
            forest.clear();

        forest.addAll(set);
        return forest;
    }

    private Node<T> getRappresentante(Node<T> node) {
        if (node != node.getParent())
            node.setParent(getRappresentante(node.getParent()));

        return node.getParent();
    }

    /**
     * 
     * @param value: the value of the node to find in the internal structure
     * @return the representative node
     * @throws UnionFindSetException if the value parameter is null or if the node
     *                               with that value was not found in the structure
     */
    public Node<T> find(T value) throws UnionFindSetException {
        if (value == null)
            throw new UnionFindSetException("UnionFindSetImplementation find: value parameter cannot be null.");

        for (int i = 0; i < forest.size(); i++) {
            if (value.equals(forest.get(i).getValue()))
                return getRappresentante(forest.get(i));
        }

        throw new UnionFindSetException("UnionFindSetImplementation find: value not found.");
    }

    /**
     * 
     * @param x: value of the node of the tree to merge
     * @param y: value of the other node of the tree to merge
     * @return a new list with the new merged set
     * @throws UnionFindSetException if the parameters are null
     */
    public ArrayList<Node<T>> union(T x, T y) throws UnionFindSetException {
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

    private void printSet() {
        for (int i = 0; i < forest.size(); i++) {
            System.out.print("Valore: " + forest.get(i).getValue());

            if (forest.get(i) == forest.get(i).getParent())
                System.out.print(" R");

            System.out.println();
        }
    }

    public boolean isEmpty() {
        return forest.isEmpty();
    }
}
