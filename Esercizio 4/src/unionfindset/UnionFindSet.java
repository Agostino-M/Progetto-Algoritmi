package src.unionfindset;

import java.util.List;

/**
 * UnionFindSet
 * 
 * @author Agostino
 */
public interface UnionFindSet<T> {

    /**
     * This function initialize the internal structure with the discreet partition
     * of the set.
     * 
     * @param set from where initialize the list
     * @return List: the new list
     * @throws UnionFindSetException if the set parameter is null
     */
    public List<Node<T>> makeSet(List<T> set) throws UnionFindSetException;

    /**
     * This function finds the representative node of a specified node.
     * 
     * @param value: the value of the node to find in the internal structure
     * @return Node<T>: the representative node
     * @throws UnionFindSetException if the value parameter is null or if the node
     *                               with that value was not found in the structure
     */
    public Node<T> find(T value) throws UnionFindSetException;

    /**
     * This function merge the first set that include the x value, with the set that
     * include y.
     * 
     * @param x : value of the node of the tree to merge
     * @param y : value of the other node of the tree to merge
     * @return List: a new list with the new merged set
     * @throws UnionFindSetException if x or y parameters are null
     */
    public List<Node<T>> union(T x, T y) throws UnionFindSetException;

}
