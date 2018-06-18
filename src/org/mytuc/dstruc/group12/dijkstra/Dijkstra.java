package org.mytuc.dstruc.group12.dijkstra;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstra {
	
	public static List<MyEdge> getShortPath(MyMap map, MyNode begin, MyNode end, double initSpeed) {
		Map<String, NodeWrapper> nodesWrapper = initNodesWrapperMap(map, begin);
		PriorityQueue<NodeWrapper> unknowQueue = initUnknowQueue(nodesWrapper, begin);
		//若未求得最短耗时的节点队列还有节点，则继续循环
		while (unknowQueue.size() > 0) {
			//弹出未确定最短耗时队列中当前耗时最少的节点。该节点已经不可能有更小的耗时。
			NodeWrapper curNode = unknowQueue.poll();
			//使用上一步弹出的确定最短耗时节点更新其他节点的耗时
			updateWeight(begin, curNode, nodesWrapper, unknowQueue, initSpeed);
		}
		return end.getShortPath();
	}
	/**
	 * 记录全部节点的HashMap。和最小队列共用实例。
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
	 * 初始化最小队列。调用其poll方法可获得耗时最短的节点
	 * @param map
	 * @param begin
	 */
	private static PriorityQueue<NodeWrapper> initUnknowQueue(Map<String, NodeWrapper> nodesWrapper, MyNode begin) {
		PriorityQueue<NodeWrapper> unknowQueue = new PriorityQueue<>();
		nodesWrapper.entrySet().forEach(entry -> unknowQueue.add(entry.getValue()));
		return unknowQueue;
		
	}

	/**
	 * 调整未知最短耗时节点的耗时
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
	 * 计算从起点到node点的耗时
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