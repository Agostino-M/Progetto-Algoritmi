package src;

import java.util.List;

import java.util.ArrayList;

/**
 * This class represents an union find set implementation.
 * 
 * @author Agostino
 * @param <T>; Type of the union find set elements
 */
public class UnionFindSetImplementation<T> { // implements UnionFindSet<T>

    private ArrayList<Node<T>> forest = new ArrayList<>();

    public ArrayList<Node<T>> makeSet(ArrayList<Node<T>> set) { // O(1)
        forest.addAll(set);
        return forest;
    }

    private Node<T> getRappresentante(Node<T> node) {
        if (node != node.getParent())
            node.setParent(getRappresentante(node.getParent()));

        return node.getParent();
    }

    public Node<T> find(T value) {
        for (int i = 0; i < forest.size(); i++) {
            if (value.equals(forest.get(i).getValue())) {
                return getRappresentante(forest.get(i));
            }
        }
        return null;
    }

    public ArrayList<Node<T>> union(T x, T y) {
        if (x.equals(y))
            return forest;

        Node<T> rX = find(x);
        Node<T> rY = find(y);

        if (rX == rY)
            return forest;

        if (rX.getRank() < rY.getRank())
            rX.setParent(rY);

        else {
            rX.setParent(rY);
            if (rY.getRank() == rX.getRank())
                rY.setRank(rY.getRank() + 1);

        }
        return forest;
    }

    public static void main(String[] args) {
        ArrayList<Node<Integer>> testInt = new ArrayList<>();
        testInt.add(new Node<>(1));
        testInt.add(new Node<>(2));
        testInt.add(new Node<>(3));
        testInt.add(new Node<>(4));
        testInt.add(new Node<>(5));
        testInt.add(new Node<>(6));

        UnionFindSetImplementation<Integer> unionFindSet = new UnionFindSetImplementation<>();

        ArrayList<Node<Integer>> finalArray;
        finalArray = unionFindSet.makeSet(testInt);

        for (int i = 0; i < finalArray.size(); i++) {
            if (finalArray.get(i) == finalArray.get(i).getParent())
                System.out.println("Nodo Rappresentante");

            System.out.println("Valore: " + finalArray.get(i).getValue());
        }

        finalArray = unionFindSet.union(2, 3);
        System.out.println("\nunion(2,3)\nIl rappresentante di 3 è: " + unionFindSet.find(3).getValue());

        finalArray = unionFindSet.union(6, 2);
        System.out.println("\nunion(6,2)\nIl rappresentante di 2 è: " + unionFindSet.find(2).getValue());
        System.out.println("Il rappresentante di 3 è: " + unionFindSet.find(3).getValue());
        System.out.println("Il rappresentante di 6 è: " + unionFindSet.find(6).getValue());
        System.out.println("Il rank di 2 è: " + unionFindSet.find(2).getRank());

        finalArray = unionFindSet.union(5, 1);
        finalArray = unionFindSet.union(6, 1);
        System.out.println("\nunion(6,1)\nIl rappresentante di 5 è: " + unionFindSet.find(5).getValue());
        System.out.println("Il rank di 2 è: " + unionFindSet.find(2).getRank());
    }

}
