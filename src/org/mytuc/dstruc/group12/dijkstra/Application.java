package org.mytuc.dstruc.group12.dijkstra;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Application {
	
	public static void main(String... args) throws IOException {
		MyMap map = loadMap();
		List<MyEdge> shortPath = Dijkstra.getShortPath(map, map.begin, map.end, 1);
		MyNode curNode = map.getBegin();
		// print the minimum time path
		double time = 0;
		double speed = 1;
		for (MyEdge edge : shortPath) {
			MyNode nextNode = edge.getLinkNode(curNode);
			time += edge.distance / speed;
			System.out.println(curNode.name + "->" + nextNode.name);
			curNode = nextNode;
			speed = curNode.changeSpeed(speed);
		}
		System.out.println("time cose:" + time);
	}

	private static MyMap loadMap() throws IOException {
		MyMap map = new MyMap();
		Files.readAllLines(Paths.get("resources/nodes_UTF-8.csv"))
		.stream()
		.skip(1)
		.forEach(line -> {
			map.addNode(new MyNode(line));
		});
		
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
