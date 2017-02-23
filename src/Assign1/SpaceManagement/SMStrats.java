package Assign1.SpaceManagement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by Max on 22/02/2017.
 */
public abstract class SMStrats {

    LinkedList<SpaceManagementNode> node_List;
    HashSet<SpaceManagementState> visited;
    boolean solved;
    int depth;
    SpaceManagementNode currNode;

    public SMStrats(){
        init();
        visited = new HashSet<>();
    }

    public void init(){
        node_List = new LinkedList<>();
        solved = false;
        depth = 0;
    }

    public void removeNode(SpaceManagementNode node){
        int index = node_List.indexOf(node);
        node_List.remove(index);
    }

    public ArrayList<SpaceManagementNode> getNew_Nodes(SpaceManagementNode currNode){
        ArrayList<SpaceManagementNode> new_Nodes = currNode.getState().getPossibleMoves();
        ArrayList<SpaceManagementNode> visitable = new ArrayList<>();
        for(SpaceManagementNode node : new_Nodes){
            node.setParent(currNode);
            if(!visited.contains(node.getState())){
                visited.add(node.getState());
                visitable.add(node);
            }
        }
        return visitable;
    }

    public LinkedList<SpaceManagementNode> interpretResults(SpaceManagementNode currNode, SpaceManagementState initialState){
        LinkedList<SpaceManagementNode> result = new LinkedList<>();
        if(currNode != null){
            while(currNode.getParent() != null){
                result.push(currNode);
                currNode = currNode.getParent();
            }
            result.push(new SpaceManagementNode(initialState));
        }
        else{
            result = null;
        }
        return result;
    }

    public abstract LinkedList<SpaceManagementNode> solve(SpaceManagementState initialState);
}
