package org.mytuc.dstruc.group12.dijkstra;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstra {
	
	public static List<MyEdge> getShortPath(MyMap map, MyNode begin, MyNode end, double initSpeed) {
		Map<String, NodeWrapper> nodesWrapper = initNodesWrapperMap(map, begin);
		PriorityQueue<NodeWrapper> unknowQueue = initUnknowQueue(nodesWrapper, begin);
		//if we have not get shortpath and node,continue loop
		while (unknowQueue.size() > 0) {
			//The node that has currently spent the least time in the shortest time-consuming queue is ejected.
			//This node can no longer be less time-consuming。
			NodeWrapper curNode = unknowQueue.poll();
			//Use the last popup to determine the time required to update other nodes with the shortest
			//time-consuming node
			updateWeight(begin, curNode, nodesWrapper, unknowQueue, initSpeed);
		}
		return end.getShortPath();
	}
	/**
	 * 
          Record the HashMap for all nodes. Share the instance with the smallest queue
	 * @param map
	 * @param begin
	 * @return
	 */
	private static Map<String, NodeWrapper> initNodesWrapperMap(MyMap map, MyNode begin) {
		Map<String, NodeWrapper> nodesWrapper = new HashMap<>();
		map.nodes.entrySet().forEach(entry -> {
			double weight = entry.getKey().equals(begin.getKey()) ? 0 : Double.MAX_VALUE;
			nodesWrapper.put(entry.getKey(), new NodeWrapper(entry.getValue(), weight));
		});
		return nodesWrapper;
	}

	/**
	 * Initialize the minimum queue. Call its poll method to get the node with the shortest time
	 * @param map
	 * @param begin
	 */
	private static PriorityQueue<NodeWrapper> initUnknowQueue(Map<String, NodeWrapper> nodesWrapper, MyNode begin) {
		PriorityQueue<NodeWrapper> unknowQueue = new PriorityQueue<>();
		nodesWrapper.entrySet().forEach(entry -> unknowQueue.add(entry.getValue()));
		return unknowQueue;
		
	}

	/**
	 * Adjust the time-consuming time of unknown and shortest time-consuming nodes
	 * @param curNode
	 */
	private static void updateWeight(MyNode begin, NodeWrapper curNode, Map<String, NodeWrapper> nodesWrapper, PriorityQueue<NodeWrapper> unknowQueue, double initSpeed) {
		MyNode realNode = curNode.node;
		for (MyEdge edge : realNode.edges.values()) {
			MyNode linkNode = edge.getLinkNode(realNode);
			NodeWrapper linkWrapper = nodesWrapper.get(linkNode.getKey());
			
			double weight = calculateWeight(begin, realNode.getShortPath(), linkNode, initSpeed);
			if (weight < linkWrapper.weight) {
				if (unknowQueue.remove(linkWrapper)) {
					linkWrapper.weight = weight;
					unknowQueue.add(linkWrapper);
					linkNode.shortPath.clear();
					linkNode.shortPath.addAll(realNode.shortPath);
					linkNode.shortPath.add(edge);
				}
			}
		}
	}
	/**
	 * 
          Calculate the time from the starting point to the node point
	 * @param begin
	 * @param shortPath
	 * @param node
	 * @param initSpeed
	 * @return
	 */
	private static double calculateWeight(MyNode begin, List<MyEdge> shortPath, MyNode node, double initSpeed) {
		double weight = 0;
		double speed = initSpeed;
		MyNode curNode = begin;
		for (MyEdge edge : shortPath) {
			speed = curNode.changeSpeed(speed);
			if (speed <= 0) {
				return Double.MAX_VALUE;
			}
			weight += edge.distance / speed;
			curNode = edge.getLinkNode(curNode);
		}
		MyEdge edge = curNode.getEdge(node);
		speed = curNode.changeSpeed(speed);
		weight += edge.distance / speed;
		return weight*10000 - node.changeSpeed(speed);
	}

}

class NodeWrapper implements Comparable<NodeWrapper> {
	double weight;
	MyNode node;
	
	NodeWrapper(MyNode node) {
		this.node = node;
		this.weight = 0;
	}
	
	NodeWrapper(MyNode node, double weight) {
		this.node = node;
		this.weight = weight;
	}

	@Override
	public int compareTo(NodeWrapper o) {
		return Double.compare(this.weight, o.weight);
	}
	@Override
	public String toString() {
		return node + ":" + weight;
	}
	
	@Override
	public boolean equals(Object o) {
		NodeWrapper other = (NodeWrapper) o;
		return this.node.equals(other.node);
	}
}
