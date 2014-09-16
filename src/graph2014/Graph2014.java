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
public class Graph2014 {
    
    private Node nodes[];
    private int globalDepth;
    
    /**
     * This is the constructor method of the Graph
     * @param number 
     */
    public Graph2014(int number){
        this.globalDepth = 0;
        nodes = new Node[number];
        for(int i = 0; i < number; i++){
            nodes[i] = new Node(number, i);
        }
    }
    
    /**
     * This method is used to add a new edge between
     * a pair of nodes in the Graph
     * @param id1
     * @param id2 
     */
    public void addEdge(int id1, int id2){        
        nodes[id1].addEdge(nodes[id2]);
        nodes[id2].addEdge(nodes[id1]);
    }
    
    /**
     * This method is used to add labels to a given
     * edge present in the Graph
     * @param id
     * @param label 
     */
    public void setLabel(int id, String label){
        nodes[id].setLabel(label);
    }
    
    /**
     * This method is used to find Articulation Points in a given Graph by 
     * calling the visitNode method
     * @param id 
     */
    public void findAP(int id){        
        System.out.println("\n\n*********************************************" +
                "**** \n" + "******Biconnected components starting at "
                + nodes[id].getLabel() + "*******");
        visitNode(id);
        purgeNodes();
    }
    
    /**
     * This is a recursive method used to visit a given node and the
     * neighborhood of this node to find Leafs and Articulation Points 
     * @param id
     * @return nodes[id].getBacklink()
     */    
    public int visitNode(int id){
        int minVal = 0;
        int backVal = 0;
        boolean isRoot = false;
        boolean leaf = true;
        boolean articPoint = false;
        int children = 0;
        globalDepth++;
        LinkedList<Node> List = nodes[id].getNeighbors();
        nodes[id].setColor(true);
        nodes[id].setDepth(globalDepth);
        if(nodes[id].getDepth() == 1){
            isRoot = true;
        }
        System.out.println("   Depth First Index of " + nodes[id].getLabel() +
                " is " + nodes[id].getDepth());
        
        /*
            This block of code is used to set the back link of each node
            while going down on the Graph
        */
        backVal = List.get(0).getDepth();               
        for(int i = 1; i < List.size(); i++){
            if(List.get(i).getDepth() < backVal){
                backVal = List.get(i).getDepth();
            }                
        }
        nodes[id].setBacklink(backVal);
        //--------------------------------------------------------------
        
        for(int i = 0; i < List.size(); i++){
            
            if(!List.get(i).getColor()){   // if the node hasn't been colored
                if(isRoot){          
                    children++;    // used to determine if the root can be an AP
                }
                leaf = false;
                minVal = visitNode(List.get(i).getId());
                if(minVal == nodes[id].getDepth()){
                    
                    if(isRoot){
                        if(children > 1){ // if true, the root can be an AP
                            articPoint = true;                            
                        }
                    }
                    else{
                        articPoint = true;
                    }
                    
                }
                /*
                    This block of code is used to update the backlink of
                    the node when the backlink received from the neighbors
                    is smallet that the value the node already have
                */
                else{ 
                    if(minVal < nodes[id].getBacklink()){
                        nodes[id].setBacklink(minVal);
                    }
                }
                //---------------------------------------------------------
            }
            
        }
                
        if(leaf == true){
            System.out.println("      Set backlink of " + nodes[id].getLabel() +
                    " to " + nodes[id].getBacklink());
            System.out.println("      Found a Leaf: " + nodes[id].getLabel());
            
        }
        
        /*
            After visiting all neighbors of a node, if this node is an AP
            this block of code will print it
        */
        if(articPoint){
            if(isRoot){
                System.out.println("========================================" +
                        "============");
                System.out.println("         Found articulation point at ROOT: "
                        + nodes[id].getLabel());
                System.out.println("========================================" +
                        "============");
            }
            else{
                
                System.out.println("========================================" +
                        "============");
                System.out.println("         Found articulation point:         "
                        + nodes[id].getLabel());
                System.out.println("========================================" +
                        "============");
            }
        }
        //-----------------------------------------------------------------
        
        
        return nodes[id].getBacklink();
    }
    
    /**
     * This method is used to print all the neighbors of a specific node
     * @param id 
     */
//    public void printNeighborsNode(int id){
//        LinkedList<Node> List = nodes[id].getNeighbors();
//        if(nodes[id] != null){
//            System.out.println("Node " + nodes[id].getLabel()
//                    + " is connected to: ");
//            for(int i = 0; i < List.size(); i++){
//                System.out.println(List.get(i).getLabel());
//            }
//            
//        }
//    }
    
    /**
     * This method is used to clean the nodes after the AP are found. This way,
     * the findAP method can be used more than one time for one Graph.
     */
    public void purgeNodes(){
        globalDepth=0;
        for(int i = 0; i < nodes.length; i++){
            nodes[i].setDepth(999);
            nodes[i].setBacklink(0);
            nodes[i].setColor(false);
        }
    }
    
    public static void main(String[] args) {
        
        Graph2014 myGraph = new Graph2014(9);
        myGraph.addEdge(0, 1);
	myGraph.addEdge(0, 2);
	myGraph.addEdge(1, 2);
	myGraph.addEdge(1, 3);
	myGraph.addEdge(2, 3);
	myGraph.addEdge(3, 4);
	myGraph.addEdge(3, 5);
	myGraph.addEdge(4, 5);
	myGraph.addEdge(4, 6);
	myGraph.addEdge(5, 7);
	myGraph.addEdge(6, 7);
	myGraph.addEdge(7, 8);

	myGraph.setLabel(0, "A");
	myGraph.setLabel(1, "B");
	myGraph.setLabel(2, "C");
	myGraph.setLabel(3, "D");
	myGraph.setLabel(4, "E");
	myGraph.setLabel(5, "F");
	myGraph.setLabel(6, "G");
	myGraph.setLabel(7, "H");
	myGraph.setLabel(8, "I");
        
        myGraph.findAP(5);
        myGraph.findAP(0);
        myGraph.findAP(3);
        
        
        Graph2014 myGraph2 = new Graph2014(11);
        myGraph2.addEdge(0, 1);
	myGraph2.addEdge(0, 4);
	myGraph2.addEdge(0, 7);
	myGraph2.addEdge(1, 4);
	myGraph2.addEdge(1, 5);
	myGraph2.addEdge(2, 5);
	myGraph2.addEdge(2, 9);
	myGraph2.addEdge(3, 6);
	myGraph2.addEdge(3, 10);
	myGraph2.addEdge(4, 8);
	myGraph2.addEdge(5, 8);
	myGraph2.addEdge(5, 9);
	myGraph2.addEdge(6, 9);
	myGraph2.addEdge(6, 10);
	myGraph2.addEdge(9, 10);

	myGraph2.setLabel(0, "A");
	myGraph2.setLabel(1, "B");
	myGraph2.setLabel(2, "C");
	myGraph2.setLabel(3, "D");
	myGraph2.setLabel(4, "E");
	myGraph2.setLabel(5, "F");
	myGraph2.setLabel(6, "G");
	myGraph2.setLabel(7, "H");
	myGraph2.setLabel(8, "I");
	myGraph2.setLabel(9, "J");
	myGraph2.setLabel(10, "K");
        
        myGraph2.findAP(0);
        myGraph2.findAP(5);
        myGraph2.findAP(7);
        
        Graph2014 myGraph3 = new Graph2014(11);  
        myGraph3.addEdge(0, 1); 
        myGraph3.addEdge(1, 5); 
        myGraph3.addEdge(2, 3);  
        myGraph3.addEdge(2, 6); 
        myGraph3.addEdge(2, 7); 
        myGraph3.addEdge(3, 7); 
        myGraph3.addEdge(4, 5); 
        myGraph3.addEdge(4, 8); 
        myGraph3.addEdge(4, 9); 
        myGraph3.addEdge(5, 6); 
        myGraph3.addEdge(5, 8); 
        myGraph3.addEdge(5, 9); 
        myGraph3.addEdge(5, 10); 
        myGraph3.addEdge(6, 10); 
        myGraph3.addEdge(8, 9); 
        myGraph3.setLabel(0, "A"); 
        myGraph3.setLabel(1, "B"); 
        myGraph3.setLabel(2, "C"); 
        myGraph3.setLabel(3, "D"); 
        myGraph3.setLabel(4, "E"); 
        myGraph3.setLabel(5, "F"); 
        myGraph3.setLabel(6, "G"); 
        myGraph3.setLabel(7, "H"); 
        myGraph3.setLabel(8, "I"); 
        myGraph3.setLabel(9, "J"); 
        myGraph3.setLabel(10, "K"); 
       
        myGraph3.findAP(0);
        myGraph3.findAP(6);
        myGraph3.findAP(5);
        
       
    }

    
    
}
