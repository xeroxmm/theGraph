package org.mytuc.dstruc.group12;

import org.mytuc.dstruc.group12.helper.DEBUG;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		DEBUG.setOutput( true );
		DEBUG.saveOutputToFile("resources/output.txt");

	    MyMap map = loadMap();
		List<MyEdge> shortPath = Dijkstra.getShortPath(map, map.begin, map.end, 1);
		MyNode curNode = map.getBegin();
		// print the minimum time path
		double time = 0;
		double speed = 1;
		DEBUG.logHeading("Shortest Path");
		for (MyEdge edge : shortPath) {
			MyNode nextNode = edge.getLinkNode(curNode);
			time += edge.distance / speed;
			DEBUG.log(curNode.name + "->" + nextNode.name +" (t: "+Math.round(edge.distance / speed)+"s , v: "+speed+" )");
			curNode = nextNode;
			speed = curNode.changeSpeed(speed);
		}
        DEBUG.logHeading("Result");
        DEBUG.log("time cost:" + time);

        DEBUG.store();
	}

	private static MyMap loadMap() throws IOException {
		MyMap map = new MyMap();
		DEBUG.logHeading("Read In nodes.csv");
		Files.readAllLines(Paths.get("resources/nodes_UTF-8.csv"))
		.stream()
		.skip(1)
		.forEach(line -> {
			map.addNode(new MyNode(line));
		});

        DEBUG.logHeading("Read In arces.csv");
		Files.readAllLines(Paths.get("resources/arces_UTF-8.csv"))
		.stream()
		.skip(1)
		.forEach(line -> {
			new MyEdge(map, line);
		});
		map.init("0", "21", 1);
		return map;
	}

}
