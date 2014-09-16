/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graph2014;
import java.util.LinkedList;
        
/**
 *
 * @author Wladimir Neto
 */
public class Node {
    private boolean color;
    private String label;
    private int id;
    private int depth;
    private int backlink;
    private Node edge[];
    
    
    /**
     * 
     * @param Edge
     * @param id 
     */
    public Node(int Edge, int id){
        
        edge = new Node[Edge];
        this.id = id;
        this.depth = 999;
        this.backlink = 0;
        this.color = false;
        
    }
    
    /**
     * 
     * @return List of neighbors of a given Node
     */
    public LinkedList getNeighbors(){
        LinkedList<Node> neighborhood;
        neighborhood = new LinkedList();
        for(Node neighbors : edge){
            if(neighbors != null){
                neighborhood.add(neighbors);
            }
        }
        return neighborhood;
        
    }
    
    /**
     * 
     * @return color
     */
    public boolean getColor(){
        return color;
    }
    
    
    /**
     * 
     * @return label
     */
    public String getLabel() {
        return label;
    }
    
    
    /**
     * 
     * @return id
     */
    
    public int getId() {
        return id;
    }
    
    
    /**
     * 
     * @return depth
     */
    public int getDepth() {
        return depth;
    }
    
    
    /**
     * 
     * @return 
     */
    public int getBacklink() {
        return backlink;
    }
    
    /**
     * 
     * @return edge
     */
    public Node[] getEdge() {
        return edge;
    }
    
    /**
     * 
     * @param color 
     */
    public void setColor(boolean color){
        this.color = color;
    }
    
    /**
     * 
     * @param label 
     */
    public void setLabel(String label) {
        this.label = label;
    }
    
    /**
     * 
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * 
     * @param depth 
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }
    
    /**
     * 
     * @param backlink 
     */
    public void setBacklink(int backlink) {
        this.backlink = backlink;
    }
    
    /**
     * 
     * @param node 
     */
    public void addEdge(Node node) {
        edge[node.getId()] = node;
    }
    
}
