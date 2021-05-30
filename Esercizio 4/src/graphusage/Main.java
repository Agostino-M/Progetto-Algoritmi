package src.graphusage;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import src.graph.*;
import src.unionfindset.*;

public class Main {

    private static String filepath = "./italian_dist_graph.csv";
    private static final Charset ENCODING = StandardCharsets.UTF_8;

    public static void main(String[] args) {

        System.out.println("Loading data from file...");
        Path inputFilePath = Paths.get(filepath);

        try (BufferedReader fileinputrReader = Files.newBufferedReader(inputFilePath, ENCODING)) {
            String line = null;
            while ((line = fileinputrReader.readLine()) != null) {
                String[] lineElements = line.split(",");
                Edge recordToBeAdded = new Edge(lineElements[0], Integer.parseInt(lineElements[1]));
                orderedArray.add(recordToBeAdded);
            }
        }
        System.out.println("DONE\n");
    }

}
