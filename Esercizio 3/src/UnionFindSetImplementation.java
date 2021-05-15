package src;

import java.util.List;
import java.util.ArrayList;

/**
 * This class represents an ordered array. Elements in the array are always
 * orderd according to a criteria specified by a comparator at creation time
 * 
 * @author Agostino
 * @param <T>; Type of the ordered array elements
 */
public class UnionFindSetImplementation<T> implements UnionFindSet<Node> {

    private int rank;
    private ArrayList<T> p = null;

    public UnionFindSetImplementation() {
        rank = 0;
        p = new ArrayList<>();
    }

    @Override
    public List<T> makeSet(ArrayList<T> set) {
        p.addAll(set);
        return p;
    }

    @Override
    public T find(ArrayList<T> set) {
        return null;
    }

    @Override
    public List<T> union(T x, T y, ArrayList<T> p) {
        
        return null;
    }

    public static void main(String[] args) {
        ArrayList<Integer> testInt = new ArrayList<>();
        testInt.add(1);
        testInt.add(2);
        testInt.add(3);
        testInt.add(4);

        UnionFindSet<Integer> union = new UnionFindSetImplementation<>();

        System.out.println(union.makeSet(testInt).toString());

    }

}
