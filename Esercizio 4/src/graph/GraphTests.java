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
    public void createUnionFindSets() throws GraphException {
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
    public void testAddNodeInt() throws GraphException {
        graphInt = new Graph<>(false);
        graphInt.addNode(0);

        List<Integer> arrayExpected = new ArrayList<>();
        arrayExpected.add(0);

        assertEquals(graphInt.getNodes(), arrayExpected);
    }

    @Test
    public void testAddNodeString() throws GraphException {
        graphString = new Graph<>(false);
        graphString.addNode("Albero");

        List<String> arrayExpected = new ArrayList<>();
        arrayExpected.add("Albero");

        assertEquals(graphString.getNodes(), arrayExpected);
    }

    @Test
    public void testAddNodeIntString() throws GraphException {
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
    public void testHasEdgeInt() throws GraphException {
        assertTrue(graphInt.hasEdge(4, 0));
    }

    @Test
    public void testHasNodeInt() throws GraphException {
        assertTrue(graphInt.hasNode(4));
    }

    @Test
    public void testHasNodeString() throws GraphException {
        assertTrue(graphString.hasNode("Ciao"));
    }

    @Test
    public void testHasNodeIntString() throws GraphException {
        assertTrue(graphIntString.hasNode("Ciao"));
    }

    @Test
    public void testDeleteNodeInt() throws GraphException {
        graphInt.deleteNode(2);
        assertFalse(graphInt.hasNode(2));
    }

    @Test
    public void testDeleteNodeString() throws GraphException {
        graphString.deleteNode("Ciao");
        assertFalse(graphString.hasNode("Ciao"));
    }

    @Test
    public void testDeleteNodeIntString() throws GraphException {
        graphIntString.deleteNode("Ciao");
        assertFalse(graphIntString.hasNode("Ciao"));
    }

    @Test
    public void testDeleteEdgeInt() throws GraphException {
        graphInt.deleteEdge(1, 3);
        assertFalse(graphInt.hasEdge(1, 3));
    }

    @Test
    public void testDeleteEdgeString() throws GraphException {
        graphString.deleteEdge("Ciao", "Amici");
        assertFalse(graphString.hasEdge("Ciao", "Amici"));
    }

    @Test
    public void testDeleteEdgeIntString() throws GraphException {
        graphIntString.deleteEdge("Ciao", "Amici");
        assertFalse(graphIntString.hasEdge("Ciao", "Amici"));
    }

    @Test
    public void testAdjacentNodesInt() throws GraphException {
        List<Integer> expectedList = new ArrayList<>();

        expectedList.add(0);
        expectedList.add(2);
        expectedList.add(3);
        expectedList.add(4);

        assertEquals(graphInt.getAdjacentNode(1), expectedList);
    }

    @Test
    public void testAdjacentNodesString() throws GraphException {
        List<String> expectedList = new ArrayList<>();

        expectedList.add("piace");
        expectedList.add("non");

        assertEquals(graphString.getAdjacentNode("mi"), expectedList);
    }

    @Test
    public void testAdjacentNodesIntString() throws GraphException {
        List<String> expectedList = new ArrayList<>();

        expectedList.add("piace");
        expectedList.add("non");

        assertEquals(graphIntString.getAdjacentNode("mi"), expectedList);
    }

    @Test
    public void testGetLabelInt() throws GraphException {
        assertEquals(graphInt.getEdgeLabel(1, 3), (Integer) 8);
    }

    @Test
    public void testGetLabelString() throws GraphException {
        List<String> expectedList = new ArrayList<>();

        expectedList.add("piace");
        expectedList.add("non");

        assertEquals(graphString.getAdjacentNode("mi"), expectedList);
    }

    @Test
    public void testGetLabelIntString() throws GraphException {
        List<String> expectedList = new ArrayList<>();

        expectedList.add("piace");
        expectedList.add("non");

        assertEquals(graphIntString.getAdjacentNode("mi"), expectedList);
    }
}
