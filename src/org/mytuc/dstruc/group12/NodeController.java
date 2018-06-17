package org.mytuc.dstruc.group12;

import org.mytuc.dstruc.group12.helper.DEBUG;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class NodeController {
    private Map<Integer, Node> nodeList; // <NodeID, NodeItself>

    public NodeController() {
        this.nodeList = new HashMap<>();
    }

    public boolean readInData(File file) throws Exception {
        String delimiter = ";";
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line = br.readLine();
        if(line == null)
            throw new Exception("Empty file");

        // analyse headline
        CSVType flag = analyseCSVHead(line, delimiter);

        while((line = br.readLine()) != null){
            // TODO analyze line -> new Node
            // TODO analyze line -> update Node neighbours
            // TODO analyze line -> update Node speedFactors

            DEBUG.log("Add new Node: ....");
        }

        return false;
    }

    private CSVType analyseCSVHead(String next, String delimiter) throws Exception {
        String[] temp = next.split(delimiter);
        if(temp.length == 3 && temp[0].equals("from") && temp[1].equals("to") && temp[2].equals("distance"))
            return CSVType.EDGE;
        else if(temp.length == 2 && temp[0].equals("ID") && temp[1].equals("modifier"))
            return CSVType.MODIFIER;
        else
            throw new Exception("Unsupported File Header");
    }

    public Optional<Node> findNodeById(int id){
        return Optional.of( nodeList.get(id));
    }

    public enum CSVType {
        EDGE, MODIFIER
    }
}
