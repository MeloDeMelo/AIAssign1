package Assign1;

import java.util.LinkedList;

/**
 * Solves Bridge and Torch problem with Breadth First Search
 * Written by Max DeMelo - 100937368
 * 2/20/2017
 */
public class BTBreadthFirst extends BTStrats{

    public BTBreadthFirst(){
        init();
    }

    public LinkedList<BridgeTorchNode> solve(BridgeTorch initialState) {
        currNode = new BridgeTorchNode(initialState);
        int searchedNodes = 0;

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
            searchedNodes++;
        }while(!solved);

        System.out.println("Number of nodes looked through: " + searchedNodes);
        return interpretResults(currNode, initialState);
    }
}
