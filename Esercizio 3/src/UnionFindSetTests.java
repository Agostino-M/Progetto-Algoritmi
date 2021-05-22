
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;

/**
 * It defines a test suite for the UnionFindSet library
 * 
 * @author Agostino
 */
public class UnionFindSetTests {

    private UnionFindSetImplementation<Integer> unionFindSetInteger;
    private UnionFindSetImplementation<String> unionFindSetString;
    private ArrayList<Node<Integer>> setInt;
    private ArrayList<Node<String>> setString;

    @Before
    public void createUnionFindSets() {
        unionFindSetInteger = new UnionFindSetImplementation<>();
        unionFindSetString = new UnionFindSetImplementation<>();

        setInt = new ArrayList<>();
        setInt.add(new Node<>(1));
        setInt.add(new Node<>(2));
        setInt.add(new Node<>(3));
        setInt.add(new Node<>(4));
        setInt.add(new Node<>(5));
        setInt.add(new Node<>(6));

        setString = new ArrayList<>();
        setString.add(new Node<>("La"));
        setString.add(new Node<>("Prego"));
        setString.add(new Node<>("Prof"));
        setString.add(new Node<>("Non"));
        setString.add(new Node<>("Ci"));
        setString.add(new Node<>("Bocci"));

    }

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
