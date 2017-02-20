package Assign1;

import java.util.LinkedList;

/**
 * Created by Max on 2/20/2017.
 */
public class BTDepthFirst implements BTStrats{

    LinkedList<BridgeTorchNode> node_List, new_Nodes;
    boolean solved, goDeeper;
    BridgeTorchNode currNode;
    int depth;

    public BTDepthFirst(){
        DepthFirst_init();
    }

    private void DepthFirst_init(){
        node_List = new LinkedList<>();
        new_Nodes = new LinkedList<>();
        solved = false;
        goDeeper = false;
        depth = 0;
    }

    private boolean moreAtDepth(){
        for(BridgeTorchNode node : node_List){
            if(node.getDepth() == depth)
                return true;
        }
        return false;
    }

    private void removeNode(BridgeTorchNode node){
        int index = node_List.indexOf(node);
        node_List.remove(index);
    }

    public LinkedList<BridgeTorchNode> solve(BridgeTorch initialState){
        DepthFirst_init();
        currNode = new BridgeTorchNode(initialState);
        LinkedList<BridgeTorchNode> result = new LinkedList<>();

        do{
            new_Nodes = currNode.getState().getPossibleMoves();
            goDeeper = (!new_Nodes.isEmpty())? true : false;
            for(BridgeTorchNode node : new_Nodes){
                node.setParentNode(currNode);
            }
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

        if(currNode != null){
            while(currNode.getParentNode() != null){
                result.push(currNode);
                currNode = currNode.getParentNode();
            }
            return result;
        }
        else{
            return null;
        }
    }
}
