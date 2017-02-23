package Assign1.SpaceManagement;

import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Max on 22/02/2017.
 */
public class SMDepthFirst extends SMStrats{
    private boolean goDeeper;

    private boolean moreAtDepth(){
        for(SpaceManagementNode node : node_List){
            if(node.getDepth() == depth)
                return true;
        }
        return false;
    }
    public LinkedList<SpaceManagementNode> solve(SpaceManagementState initialState) {
        currNode = new SpaceManagementNode(initialState);
        goDeeper = false;
        do{
            ArrayList<SpaceManagementNode> new_nodes = getNew_Nodes(currNode);
            goDeeper = (!new_nodes.isEmpty());
            node_List.addAll(new_nodes);

            if(currNode.getState().checkWin()){
                solved = true;
            }
            else if(goDeeper){
                for(SpaceManagementNode node : node_List){
                    if(node.getDepth() > depth){
                        depth ++;
                        currNode = node;
                        removeNode(node);
                        break;
                    }
                }
            }
            else if(moreAtDepth()){
                for(SpaceManagementNode node : node_List){
                    if(node.getDepth() == depth){
                        currNode = node;
                        removeNode(node);
                        break;
                    }
                }
            }
            else if(!node_List.isEmpty()){
                for(SpaceManagementNode node : node_List){
                    if(node.getDepth() == depth-1){
                        depth --;
                        currNode = node;
                        removeNode(node);
                        break;
                    }
                }
            }
            else{
                currNode = null;
                solved = true;
            }
        }while(!solved);

        return interpretResults(currNode, initialState);
    }
}
