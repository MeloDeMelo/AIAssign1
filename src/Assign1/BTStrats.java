package Assign1;

import java.util.LinkedList;

/**
 * abstract strat for ease of making new strats
 * Written by Max DeMelo - 100937368
 * 2/20/2017
 */
public abstract class BTStrats {

    LinkedList<BridgeTorchNode> node_List;
    boolean solved;
    int depth;
    BridgeTorchNode currNode;

    public BTStrats(){init();}

    public void init(){
        node_List = new LinkedList<>();
        solved = false;
        depth = 0;
    }

    public void removeNode(BridgeTorchNode node){
        int index = node_List.indexOf(node);
        node_List.remove(index);
    }

    public LinkedList<BridgeTorchNode> getNew_Nodes(BridgeTorchNode currNode){
        LinkedList<BridgeTorchNode> new_Nodes = currNode.getState().getPossibleMoves();
        for(BridgeTorchNode node : new_Nodes){
            node.setParentNode(currNode);
        }
        return new_Nodes;
    }

    public LinkedList<BridgeTorchNode> interpretResults(BridgeTorchNode currNode, BridgeTorch initialState){
        LinkedList<BridgeTorchNode> result = new LinkedList<>();
        if(currNode != null){
            while(currNode.getParentNode() != null){
                result.push(currNode);
                currNode = currNode.getParentNode();
            }
            result.push(new BridgeTorchNode(initialState));
        }
        else{
            result = null;
        }
        return result;
    }

    public abstract LinkedList<BridgeTorchNode> solve(BridgeTorch initialState);
}
