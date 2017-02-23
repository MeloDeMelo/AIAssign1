package Assign1.Bridge_and_Torch;

import java.util.LinkedList;

/**
 * Solves Bridge and Torch problem with A* Strategy
 * Written by Max DeMelo - 100937368
 * 2/21/2017
 */
public class BTAStar extends BTStrats{

    private int heuristic(BridgeTorchNode currNode){
        int value = 0;
        for(Integer person : currNode.getState().getPeopleLoB()){
            value += person;
        }
        return value;
    }

    private int getHeuristicValue(BridgeTorchNode node){
        return node.getCost() + heuristic(node);
    }

    public LinkedList<BridgeTorchNode> solve(BridgeTorch initialState) {
        currNode = new BridgeTorchNode(initialState);

        do{

            node_List.addAll(getNew_Nodes(currNode));

            if(currNode.getState().checkWin()){
                solved = true;
            }
            else if (!node_List.isEmpty()){
                BridgeTorchNode bestNode = null;
                for(BridgeTorchNode node : node_List){
                    if(getHeuristicValue(node) <= getHeuristicValue(currNode))
                        bestNode = currNode;
                }
                if(bestNode == null)
                    currNode = node_List.poll();
                else {
                    currNode = bestNode;
                    removeNode(currNode);
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
