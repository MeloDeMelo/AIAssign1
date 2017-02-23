package Assign1.SpaceManagement;

import java.util.LinkedList;

/**
 * Created by Max on 22/02/2017.
 */
public class SMBreadthFirst extends SMStrats{

    public LinkedList<SpaceManagementNode> solve(SpaceManagementState initialState) {
        currNode = new SpaceManagementNode(initialState);
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
