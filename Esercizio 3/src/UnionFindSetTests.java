
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.Before;

/**
 * This class defines a test suite for the UnionFindSet library
 * 
 * @author Agostino and Andrea
 */
public class UnionFindSetTests {

    private UnionFindSetImplementation<Integer> unionFindSetInteger;
    private UnionFindSetImplementation<String> unionFindSetString;
    private List<Integer> setInt;
    private List<String> setString;

    @Before
    public void createUnionFindSets() {
        unionFindSetInteger = new UnionFindSetImplementation<>();
        unionFindSetString = new UnionFindSetImplementation<>();

        setInt = new ArrayList<>();
        setInt.add(1);
        setInt.add(2);
        setInt.add(3);
        setInt.add(4);
        setInt.add(5);
        setInt.add(6);

        setString = new ArrayList<>();
        setString.add("La");
        setString.add("Prego");
        setString.add("Prof");
        setString.add("Non");
        setString.add("Ci");
        setString.add("Bocci");

    }

    @Test
    public void testIsEmptyIntZeroEl() {
        assertTrue(unionFindSetInteger.isEmpty());
    }

    @Test
    public void testIsEmptyStringZeroEl() {
        assertTrue(unionFindSetString.isEmpty());
    }

    @Test
    public void testMakeSetInt() throws UnionFindSetException {
        List<Node<Integer>> arrExpected = new ArrayList<>();
        arrExpected.add(new Node<>(1));
        arrExpected.add(new Node<>(2));
        arrExpected.add(new Node<>(3));
        arrExpected.add(new Node<>(4));
        arrExpected.add(new Node<>(5));
        arrExpected.add(new Node<>(6));

        assertEquals(arrExpected, unionFindSetInteger.makeSet(setInt));
    }

    @Test
    public void testUnionInt() throws UnionFindSetException {
        List<Node<Integer>> arrExpected = new ArrayList<>();
        arrExpected.add(new Node<>(1));
        arrExpected.add(new Node<>(2));
        arrExpected.add(new Node<>(3));
        arrExpected.add(new Node<>(4));
        arrExpected.add(new Node<>(5));
        arrExpected.add(new Node<>(6));

        arrExpected.get(0).setParent(arrExpected.get(5));
        arrExpected.get(5).setRank(1);

        List<Node<Integer>> actualArr = unionFindSetInteger.makeSet(setInt);
        unionFindSetInteger.union(1, 6);

        assertEquals(arrExpected, actualArr);
    }

    @Test
    public void testMakeSetString() throws UnionFindSetException {
        List<Node<String>> arrExpected = new ArrayList<>();
        arrExpected.add(new Node<>("La"));
        arrExpected.add(new Node<>("Prego"));
        arrExpected.add(new Node<>("Prof"));
        arrExpected.add(new Node<>("Non"));
        arrExpected.add(new Node<>("Ci"));
        arrExpected.add(new Node<>("Bocci"));

        assertEquals(arrExpected, unionFindSetString.makeSet(setString));
    }

    @Test
    public void testUnionString() throws UnionFindSetException {
        List<Node<String>> arrExpected = new ArrayList<>();
        arrExpected.add(new Node<>("La"));
        arrExpected.add(new Node<>("Prego"));
        arrExpected.add(new Node<>("Prof"));
        arrExpected.add(new Node<>("Non"));
        arrExpected.add(new Node<>("Ci"));
        arrExpected.add(new Node<>("Bocci"));

        arrExpected.get(4).setParent(arrExpected.get(5));
        arrExpected.get(5).setRank(1);

        List<Node<String>> actualArr = unionFindSetString.makeSet(setString);
        unionFindSetString.union("Ci", "Bocci");

        assertEquals(arrExpected, actualArr);
    }

}
