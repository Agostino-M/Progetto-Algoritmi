package src.graphusage;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.graph.*;
import src.unionfindset.*;

public class KruskalUsage {

    private static String filepath = "./italian_dist_graph.csv";
    private static final Charset ENCODING = StandardCharsets.UTF_8;

    public static void main(String[] args) {

        Graph<String, Float> graph = new Graph<>(true);
        Path inputFilePath = Paths.get(filepath);
        int max = 2000000;

        System.out.println("Loading data from file...");

        try (BufferedReader fileinputrReader = Files.newBufferedReader(inputFilePath, ENCODING)) {
            String line = null;

            while ((line = fileinputrReader.readLine()) != null && max > 0) {
                String[] lineElements = line.split(",");

                graph.addEdge(lineElements[0], lineElements[1], Float.parseFloat(lineElements[2]));
                max--;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (GraphException e) {
            e.printStackTrace();
        }

        System.out.println("DONE\n");

        System.out.println("Graph generated:\n" + graph);

        try {
            System.out.println("Applying Kruskal ... ");

            List<Edge<String, Float>> resultEdges = new MstKruskal<String, Float>().kruskal(graph,
                    new EdgeFloatComparator());

            System.out.println("Edges found: \n" + resultEdges);
            System.out.println("Number of edges found: " + resultEdges.size() + "\n" + printResult(resultEdges));

        } catch (UnionFindSetException e) {
            e.printStackTrace();
        }
    }

    public static String printResult(List<Edge<String, Float>> list) {
        Map<String, Integer> temp = new HashMap<>();
        int contNode = 0;
        float accWeight = 0;

        for (Edge<String, Float> edge : list) {
            System.out.println(edge);

            if (!temp.containsKey(edge.getSource())) {
                temp.put(edge.getSource(), 0);
                contNode++;
            }

            if (!temp.containsKey(edge.getDestination())) {
                temp.put(edge.getDestination(), 0);
                contNode++;
            }

            accWeight += edge.getWeight();
        }

        return "Nuber of nodes: " + contNode + "\nTotal weight " + accWeight / 1000 + "km";
    }

}
