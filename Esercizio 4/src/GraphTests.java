package src;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;

/**
 * It defines a test suite for the Graph library
 * 
 * @author Agostino
 */
public class GraphTests {

    private Graph<Integer> graphInt;
    private Graph<String> graphString;

    @Before
    public void createUnionFindSets() {
        graphInt = new Graph<>(true);
        graphString = new Graph<>(true);

        graphInt.addEdge(0, 1);
        graphInt.addEdge(0, 4);
        graphInt.addEdge(1, 2);
        graphInt.addEdge(1, 3);
        graphInt.addEdge(1, 4);
        graphInt.addEdge(2, 3);
        graphInt.addEdge(3, 4);

        graphString.addEdge("Ciao", "Amici");
        graphString.addEdge("Che", "Fate");
        graphString.addEdge("Io", "Niente");
        graphString.addEdge("Studio", "Algoritmi");
        graphString.addEdge("Sperando", "Che");
        graphString.addEdge("Il", "Prof");
        graphString.addEdge("Mi", "Promuova");
    }

    // TODO test all the method in src.Graph

    @Test
    public void testIsEmptyInt_zeroEl() {
        assertTrue(unionFindSetInteger.isEmpty());
    }

    @Test
    public void testIsEmptyString_zeroEl() {
        assertTrue(unionFindSetString.isEmpty());
    }

    @Test
    public void testMakeSet_int() throws UnionFindSetException {
        ArrayList<Node<Integer>> arrExpected = new ArrayList<>();
        arrExpected.add(new Node<>(1));
        arrExpected.add(new Node<>(2));
        arrExpected.add(new Node<>(3));
        arrExpected.add(new Node<>(4));
        arrExpected.add(new Node<>(5));
        arrExpected.add(new Node<>(6));

        ArrayList<Node<Integer>> actualArr = unionFindSetInteger.makeSet(setInt);
        assertEquals(arrExpected, actualArr);
    }

    @Test
    public void testUnion_int() throws UnionFindSetException {
        ArrayList<Node<Integer>> arrExpected = new ArrayList<>();
        arrExpected.add(new Node<>(1));
        arrExpected.add(new Node<>(2));
        arrExpected.add(new Node<>(3));
        arrExpected.add(new Node<>(4));
        arrExpected.add(new Node<>(5));
        arrExpected.add(new Node<>(6));

        arrExpected.get(0).setParent(arrExpected.get(5));
        arrExpected.get(5).setRank(1);

        ArrayList<Node<Integer>> actualArr = unionFindSetInteger.makeSet(setInt);
        unionFindSetInteger.union(1, 6);

        assertEquals(arrExpected, actualArr);
    }

    @Test
    public void testMakeSet_string() throws UnionFindSetException {
        ArrayList<Node<String>> arrExpected = new ArrayList<>();
        arrExpected.add(new Node<>("La"));
        arrExpected.add(new Node<>("Prego"));
        arrExpected.add(new Node<>("Prof"));
        arrExpected.add(new Node<>("Non"));
        arrExpected.add(new Node<>("Ci"));
        arrExpected.add(new Node<>("Bocci"));

        ArrayList<Node<String>> actualArr = unionFindSetString.makeSet(setString);

        assertEquals(arrExpected, actualArr);
    }

    @Test
    public void testUnion_string() throws UnionFindSetException {
        ArrayList<Node<String>> arrExpected = new ArrayList<>();
        arrExpected.add(new Node<>("La"));
        arrExpected.add(new Node<>("Prego"));
        arrExpected.add(new Node<>("Prof"));
        arrExpected.add(new Node<>("Non"));
        arrExpected.add(new Node<>("Ci"));
        arrExpected.add(new Node<>("Bocci"));

        arrExpected.get(4).setParent(arrExpected.get(5));
        arrExpected.get(5).setRank(1);

        ArrayList<Node<String>> actualArr = unionFindSetString.makeSet(setString);
        unionFindSetString.union("Ci", "Bocci");

        assertEquals(arrExpected, actualArr);
    }

}
