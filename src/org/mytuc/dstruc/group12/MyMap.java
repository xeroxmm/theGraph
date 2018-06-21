package org.mytuc.dstruc.group12;

import org.mytuc.dstruc.group12.helper.DEBUG;

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
		DEBUG.log("New Node (ID: "+node.name+", Factor: "+node.speedFactor+", Type: "+node.type+")");
		this.nodes.put(node.name, node);
	}
	
	public void init(String begin, String end, float initSpeed) {
		this.begin = this.getNode(begin);
		this.end = this.getNode(end);
		this.initSpeed = initSpeed;

		DEBUG.log("\nMap initialized: From "+this.begin.name+" to "+this.end.name);
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
