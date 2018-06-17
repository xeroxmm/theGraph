package org.mytuc.dstruc.group12;

import org.mytuc.dstruc.group12.helper.DEBUG;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws Exception {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        NodeController nodeCtrl = new NodeController();

        DEBUG.logHeading("Read In arces.csv");
        nodeCtrl.readInData( new File("resources/arces.csv") );

        DEBUG.logHeading("Read In nodes.csv");
        nodeCtrl.readInData( new File("resources/arces.csv") );

        ShortestPathController shortestPathCtrl = new ShortestPathController( nodeCtrl );
        shortestPathCtrl.calculateShortestPath(0,21);
    }
}
