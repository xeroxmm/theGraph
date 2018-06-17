package org.mytuc.dstruc.group12;

import java.util.ArrayList;
import java.util.Optional;

public class ShortestPathController {
    private ArrayList<Node> path;
    private NodeController nodeCtrl;

    private Node startNode;
    private Node endNode;
    // maybe dispensable
    private Node nextNode;

    public ShortestPathController(NodeController nodeCtrl){
        this.nodeCtrl = nodeCtrl;
        initialize();
    }

    private void initialize() {
        this.path = new ArrayList<>();
    }
    public void setNodeController(NodeController nodeCtrl){
        this.nodeCtrl = nodeCtrl;
    }
    public void calculateShortestPath(Node from, Node to){
        initialize();
    }
    public void calculateShortestPath(int from, int to){
        if(!setStartConditions(from, to))
            return;

        // todo calculate shortest path
    }
    public ArrayList<Node> getPathAsNodeList(){
        return this.path;
    }
    public int[] getPathAsNodeIDList(){
        int[] pathIDs = new int[path.size()];
        for(int i = 0; i < path.size(); i++){
            pathIDs[i] = path.get(i).getID();
        }
        return pathIDs;
    }

    private boolean setStartConditions(int from, int to) {
        Optional<Node> fromNodeX = nodeCtrl.findNodeById( from );
        Optional<Node> toNodeX = nodeCtrl.findNodeById( from );
        if(!fromNodeX.isPresent() || !toNodeX.isPresent())
            return false;

        startNode = fromNodeX.get();
        endNode = toNodeX.get();

        return true;
    }
}
