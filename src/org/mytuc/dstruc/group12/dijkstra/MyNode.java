package org.mytuc.dstruc.group12.dijkstra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyNode {
	String name;//the name of current node
	Map<String, MyEdge> edges;//the edge of current node connected
	List<MyEdge> shortPath;//Records the edge from begin to the current node
	float speedFactor;
	int type;//0:static,1:+-;2:*;3:^;-1:begin or end
	
	public MyNode(String line) {
		String[] params = line.split(";");
		this.name = params[0];
		if (params[1].equals("*speed")) {
			this.type = 3;
		}
		else if(params[1].equals("")) {
			
		}
		switch (params[1]) {
		case "*speed":this.type=3;break;
		case "-": {
			this.type = -1;
			break;
		}
		default: initSpeedFactory(params[1]);
		}
		this.edges = new HashMap<>();
		this.shortPath = new ArrayList<>();
	}
	
	
	private void initSpeedFactory(String param) {
		char firstChar = param.charAt(0);
		if (firstChar >= '0' && firstChar <= '9') {
			this.speedFactor = Float.valueOf(param);
			this.type = 0;
			return;
		}
		float value = Float.valueOf(param.substring(1));
		this.speedFactor= firstChar == '-' ? -value : value;
		this.type= firstChar == '*' ? 2 : 1;
	}


	public MyEdge getEdge(MyNode linkNode) {
		return edges.get(linkNode.getKey());
	}


	public List<MyEdge> getShortPath() {
		return this.shortPath;
	}
	
	public void addEdge(MyEdge edge) {
		MyNode linkNode = edge.getLinkNode(this);
		edges.put(linkNode.getKey(), edge);
	}

	public String getKey() {
		return this.name;
	}
	
	public double changeSpeed(double speed) {
		switch(type) {
		case 0: speed = this.speedFactor;break;
		case 1: speed += this.speedFactor;break;
		case 2: speed *= this.speedFactor;break;
		case 3: speed *= speed;break;
		default:break;
		}
		return speed >= 100 ? speed - (speed * (-1 / (speed/100) + 1.001)) : speed;
	}
	
	@Override
	public String toString() {
		return "[" + this.name + "]" + this.edges.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		return this.name.equals(((MyNode)o).name);
	}
}
