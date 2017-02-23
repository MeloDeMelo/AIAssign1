package Assign1.Bridge_and_Torch;

import java.util.LinkedList;

/**
 * Solves Bridge and Torch problem with Breadth First Search
 * Written by Max DeMelo - 100937368
 * 2/20/2017
 */
public class BTBreadthFirst extends BTStrats{

    public LinkedList<BridgeTorchNode> solve(BridgeTorch initialState) {
        currNode = new BridgeTorchNode(initialState);

        do{
            node_List.addAll(getNew_Nodes(currNode));

            if(currNode.getState().checkWin()){
                solved = true;
            }else if (!node_List.isEmpty()){
                currNode = node_List.poll();
            }else{
                solved = true;
                currNode = null;
            }
        }while(!solved);

        return interpretResults(currNode, initialState);
    }
}
