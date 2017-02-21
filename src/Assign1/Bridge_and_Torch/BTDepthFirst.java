package Assign1.Bridge_and_Torch;

import java.util.LinkedList;

/**
 * Solves Bridge and Torch problem with Deapth First Search
 * Written by Max DeMelo - 100937368
 * 2/20/2017
 */
public class BTDepthFirst extends BTStrats{

    private boolean goDeeper;

    public BTDepthFirst(){
        init();
    }

    private boolean moreAtDepth(){
        for(BridgeTorchNode node : node_List){
            if(node.getDepth() == depth)
                return true;
        }
        return false;
    }

    public LinkedList<BridgeTorchNode> solve(BridgeTorch initialState){
        currNode = new BridgeTorchNode(initialState);
        goDeeper = false;

        do{
            LinkedList<BridgeTorchNode> new_Nodes = getNew_Nodes(currNode);
            goDeeper = (!new_Nodes.isEmpty());
            node_List.addAll(new_Nodes);

            if(currNode.getState().checkWin()){
                solved = true;
            }else if(goDeeper){
                for(BridgeTorchNode node : node_List){
                    if(node.getDepth() > depth){
                        depth ++;
                        currNode = node;
                        removeNode(node);
                        break;
                    }
                }
            }else if(moreAtDepth()){
                for(BridgeTorchNode node : node_List){
                    if(node.getDepth() == depth){
                        currNode = node;
                        removeNode(node);
                        break;
                    }
                }
            }else if(!node_List.isEmpty()){
                for(BridgeTorchNode node : node_List){
                    if(node.getDepth() == depth-1){
                        depth --;
                        currNode = node;
                        removeNode(node);
                        break;
                    }
                }
            }else{
                currNode = null;
                solved = true;
            }
        }while(!solved);

        return interpretResults(currNode, initialState);
    }
}
