package org.mytuc.dstruc.group12;

import org.mytuc.dstruc.group12.helper.DEBUG;

public class MyEdge  {
	MyNode start;
	MyNode end;
	float distance;//the distance of edge
	
	public MyEdge(MyMap map, String line) {
		String[] params = line.split(";");
		this.start = map.getNode(params[0]);
		this.end = map.getNode(params[1]);
		this.start.addEdge(this);
		this.distance = Float.valueOf(params[2]);
		DEBUG.log("New Edge from "+start.name+" -> "+end.name);
	}
	/**
	 * 
         Get the other side of the edge. This assumes that the given node is the connection point of the edge and 
	 no longer checks it.
	 * @param node
	 * @return
	 */
	public MyNode getLinkNode(MyNode node) {
		return this.start.getKey().equals(node.getKey()) ? this.end : this.start;
	}
	
	@Override
	public String toString() {
		return "[" + this.start.name + "]->[" + this.end.name + "]:" + this.distance;
	}

}
