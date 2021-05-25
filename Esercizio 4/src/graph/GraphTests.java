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

    private Graph<Integer> graphInt;
    private Graph<String> graphString;

    @Before
    public void createUnionFindSets() {
        graphInt = new Graph<>(true);
        graphString = new Graph<>(true);

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
    public void testAdjacentNodesInt() {
        List<Edge<Integer>> expectedList = new ArrayList<>();

        expectedList.add(new Edge<>(0));
        expectedList.add(new Edge<>(2));
        expectedList.add(new Edge<>(3));
        expectedList.add(new Edge<>(4));

        assertEquals(graphInt.getAdjacentNode(1), expectedList);
    }

    @Test
    public void testAdjacentNodesString() {
        List<Edge<String>> expectedList = new ArrayList<>();

        expectedList.add(new Edge<>("piace"));
        expectedList.add(new Edge<>("non"));

        assertEquals(graphString.getAdjacentNode("mi"), expectedList);
    }
}
