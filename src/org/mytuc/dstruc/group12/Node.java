package org.mytuc.dstruc.group12;

import java.util.HashSet;
import java.util.Set;

public class Node {
    private int ID;
    private Set<Node> neighbours;
    private float speedFactor;
    private boolean isAbsoluteValue;
    private boolean isPoweredValue;

    // TODO extend Node with "real" (timed) distances and necessary methods

    public Node(int ID){
        this.ID = ID;
        neighbours = new HashSet<>();
        speedFactor = 1;
        isAbsoluteValue = false;
        isPoweredValue = false;
    }
    public void addNeighbour(Node neighbourNode){
        neighbours.add( neighbourNode );
    }
    public void setSpeedFactor(float factor){
        speedFactor = factor;
    }
    public void setFactorAsAbsoluteValue(){
        isAbsoluteValue = true;
        isPoweredValue = false;
    }
    public void setFactorAsPoweredValue(){
        isAbsoluteValue = false;
        isPoweredValue = true;
    }
    public void setFactorAsMultiplicator(){
        isAbsoluteValue = false;
        isPoweredValue = false;
    }

    public int getID() {
        return ID;
    }
}
