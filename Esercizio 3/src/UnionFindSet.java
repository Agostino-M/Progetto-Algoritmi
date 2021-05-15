package src;

import java.util.List;
import java.util.ArrayList;

/**
 * UnionFindSet
 * Generico T --> Nodo<T> --> Albero P --> Foresta U
 */
public interface UnionFindSet<T> {

    /**
     * 
     * @param set
     * @return
     */
    public List<T> makeSet(ArrayList<T> set);

    /**
     * ˘
     * @param set
     * @return
     */
    public T find(ArrayList<T> set);

    /**
     * 
     * @param set
     * @return
     */
    public List<T> union(T x, T y, ArrayList<T> p);
}