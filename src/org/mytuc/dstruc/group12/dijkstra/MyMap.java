package org.mytuc.dstruc.group12.dijkstra;

import java.util.HashMap;
import java.util.Map;

public class MyMap {
	Map<String, MyNode> nodes;
	MyNode begin;
	MyNode end;
	float initSpeed;
	
	public MyMap() {
		this.nodes = new HashMap<>();
	}
	
	public void addNode(MyNode node) {
		this.nodes.put(node.name, node);
	}
	
	public void init(String begin, String end, float initSpeed) {
		this.begin = this.getNode(begin);
		this.end = this.getNode(end);
		this.initSpeed = initSpeed;
	}
	
	MyNode getBegin() {
		return this.begin;
	}
	public double getInitSpeed() {
		return this.initSpeed;
	}
	MyNode getEnd() {
		return this.end;
	}
	
	MyNode getNode(String key) {
		return nodes.get(key);
	}
	
}
