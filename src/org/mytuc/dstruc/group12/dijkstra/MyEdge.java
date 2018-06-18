package org.mytuc.dstruc.group12.dijkstra;

public class MyEdge  {
	MyNode start;//边的一端
	MyNode end;//边的另一端
	float distance;//边的距离
	
	public MyEdge(MyMap map, String line) {
		String[] params = line.split(";");
		this.start = map.getNode(params[0]);
		this.end = map.getNode(params[1]);
		this.start.addEdge(this);
		this.distance = Float.valueOf(params[2]);
	}
	/**
	 * 获取边的另一端。这里假定给定node是边的连接点，不再进行检查。
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