package src.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.Before;

/**
 * It defines a test suite for the Graph library
 * 
 * @author Agostino
 */
public class GraphTests {

    private Graph<Integer, Integer> graphInt;
    private Graph<String, String> graphString;
    private Graph<String, Integer> graphIntString;

    @Before
    public void createUnionFindSets() {
        graphInt = new Graph<>(true);
        graphString = new Graph<>(true);
        graphIntString = new Graph<>(true);

        graphInt.addEdge(0, 1, 5);
        graphInt.addEdge(0, 4, 4);
        graphInt.addEdge(1, 2, 6);
        graphInt.addEdge(1, 3, 8);
        graphInt.addEdge(1, 4, 12);
        graphInt.addEdge(2, 3, 41);
        graphInt.addEdge(3, 4, 9);

        graphString.addEdge("Ciao", "Amici", "Che");
        graphString.addEdge("fate", "io", "Niente");
        graphString.addEdge("studio", "algoritmi", "e");
        graphString.addEdge("mi", "piace", "TANTISSIMO");
        graphString.addEdge("Sperando", "Che", "il");
        graphString.addEdge("mio", "Prof", "preferito");
        graphString.addEdge("non", "mi", "BOCCI");

        graphIntString.addEdge("Ciao", "Amici", 10);
        graphIntString.addEdge("fate", "io", 2);
        graphIntString.addEdge("studio", "algoritmi", 5);
        graphIntString.addEdge("mi", "piace", 4);
        graphIntString.addEdge("Sperando", "Che", 4);
        graphIntString.addEdge("mio", "Prof", 8);
        graphIntString.addEdge("non", "mi", 1);
    }

    @Test
    public void testZeroNodesInt() {
        graphInt = new Graph<>(false);

        assertEquals(graphInt.getNodeCount(), 0);
    }

    @Test
    public void testZeroEdgesInt() {
        graphInt = new Graph<>(false);

        assertEquals(graphInt.getEdgesCount(), 0);
    }

    @Test
    public void testZeroNodesIntString() {
        graphIntString = new Graph<>(false);

        assertEquals(graphIntString.getNodeCount(), 0);
    }

    @Test
    public void testAddNodeInt() {
        graphInt = new Graph<>(false);
        graphInt.addNode(0);

        List<Integer> arrayExpected = new ArrayList<>();
        arrayExpected.add(0);

        assertEquals(graphInt.getNodes(), arrayExpected);
    }

    @Test
    public void testAddNodeString() {
        graphString = new Graph<>(false);
        graphString.addNode("Albero");

        List<String> arrayExpected = new ArrayList<>();
        arrayExpected.add("Albero");

        assertEquals(graphString.getNodes(), arrayExpected);
    }

    @Test
    public void testAddNodeIntString() {
        graphIntString = new Graph<>(false);
        graphIntString.addNode("Albero");

        List<String> arrayExpected = new ArrayList<>();
        arrayExpected.add("Albero");

        assertEquals(graphIntString.getNodes(), arrayExpected);
    }

    @Test
    public void testAddEdgeInt() {
        assertEquals(graphInt.getEdgesCount(), 7);
    }

    @Test
    public void testHasEdgeInt() {
        assertTrue(graphInt.hasEdge(4, 0));
    }

    @Test
    public void testHasNodeInt() {
        assertTrue(graphInt.hasNode(4));
    }

    @Test
    public void testHasNodeString() {
        assertTrue(graphString.hasNode("Ciao"));
    }

    @Test
    public void testHasNodeIntString() {
        assertTrue(graphIntString.hasNode("Ciao"));
    }

    @Test
    public void testDeleteNodeInt() {
        graphInt.deleteNode(2);
        assertFalse(graphInt.hasNode(2));
    }

    @Test
    public void testDeleteNodeString() {
        graphString.deleteNode("Ciao");
        assertFalse(graphString.hasNode("Ciao"));
    }

    @Test
    public void testDeleteNodeIntString() {
        graphIntString.deleteNode("Ciao");
        assertFalse(graphIntString.hasNode("Ciao"));
    }

    @Test
    public void testDeleteEdgeInt() {
        graphInt.deleteEdge(1, 3);
        assertFalse(graphInt.hasEdge(1, 3));
    }

    @Test
    public void testDeleteEdgeString() {
        graphString.deleteEdge("Ciao", "Amici");
        assertFalse(graphString.hasEdge("Ciao", "Amici"));
    }

    @Test
    public void testDeleteEdgeIntString() {
        graphIntString.deleteEdge("Ciao", "Amici");
        assertFalse(graphIntString.hasEdge("Ciao", "Amici"));
    }

    @Test
    public void testAdjacentNodesInt() {
        List<Edge<Integer, Integer>> expectedList = new ArrayList<>();

        expectedList.add(new Edge<>(0));
        expectedList.add(new Edge<>(2));
        expectedList.add(new Edge<>(3));
        expectedList.add(new Edge<>(4));

        assertEquals(graphInt.getAdjacentNode(1), expectedList);
    }

    @Test
    public void testAdjacentNodesString() {
        List<Edge<String, String>> expectedList = new ArrayList<>();

        expectedList.add(new Edge<>("piace"));
        expectedList.add(new Edge<>("non"));

        assertEquals(graphString.getAdjacentNode("mi"), expectedList);
    }

    @Test
    public void testAdjacentNodesIntString() {
        List<Edge<String, Integer>> expectedList = new ArrayList<>();

        expectedList.add(new Edge<>("piace"));
        expectedList.add(new Edge<>("non"));

        assertEquals(graphIntString.getAdjacentNode("mi"), expectedList);
    }
}