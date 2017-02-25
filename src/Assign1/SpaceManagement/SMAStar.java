package Assign1.SpaceManagement;

import java.util.LinkedList;

/**
 * Created by Max on 22/02/2017.
 */
public class SMAStar extends SMStrats{

    possibleSMSHeuristics heuristic;

    public SMAStar(possibleSMSHeuristics heuristic){
        this.heuristic = heuristic;
        init();
    }

    private int tilesOutOfPlace(SpaceManagementNode currnode){
        int incorrectTiles = 0;
        int numberRange = currnode.getState().getWidth()*currnode.getState().getHeight() - currnode.getState().getBlankSpaces();
        int correctPosition = 1;
        int i, k = 0;
        int rightLimit = currnode.getState().getWidth()-1, bottomLimit = currnode.getState().getHeight()-1, leftLimit = 0, topLimit = 0;

        while(correctPosition < numberRange) {
            for (i = leftLimit; i <= rightLimit; i++) {
                if(correctPosition > numberRange)
                    break;
                else if (correctPosition != currnode.getState().getAtPosition(i,k))
                    incorrectTiles ++;
                correctPosition++;
            }
            i--;
            topLimit++;
            for (k = topLimit; k <= bottomLimit; k++) {
                if(correctPosition > numberRange)
                    break;
                else if (correctPosition != currnode.getState().getAtPosition(i,k))
                    incorrectTiles ++;
                correctPosition++;
            }
            k--;
            rightLimit--;

            for (i = rightLimit; i >= leftLimit; i--) {
                if(correctPosition > numberRange)
                    break;
                else if (correctPosition != currnode.getState().getAtPosition(i,k))
                    incorrectTiles ++;
                correctPosition++;
            }
            i++;
            bottomLimit--;
            for (k = bottomLimit; k >= topLimit; k--) {
                if(correctPosition > numberRange)
                    break;
                else if (correctPosition != currnode.getState().getAtPosition(i,k))
                    incorrectTiles ++;
                correctPosition++;
            }
            k++;
            leftLimit++;
        }
        return incorrectTiles;
    }

    private int totalRelativePosition(SpaceManagementNode currNode){
        int numberRange = currNode.getState().getWidth()*currNode.getState().getHeight() - currNode.getState().getBlankSpaces();
        int correctPosition = 1;
        int totalDistance = 0;
        int i, k = 0;
        int rightLimit = currNode.getState().getWidth()-1, bottomLimit = currNode.getState().getHeight()-1, leftLimit = 0, topLimit = 0;

        while(correctPosition < numberRange) {
            for (i = leftLimit; i <= rightLimit; i++) {
                if(correctPosition > numberRange)
                    break;
                else if (correctPosition != currNode.getState().getAtPosition(i,k))
                    totalDistance += distance(correctPosition,currNode,i,k);
                correctPosition++;
            }
            i--;
            topLimit++;
            for (k = topLimit; k <= bottomLimit; k++) {
                if(correctPosition > numberRange)
                    break;
                else if (correctPosition != currNode.getState().getAtPosition(i,k))
                    totalDistance += distance(correctPosition,currNode,i,k);
                correctPosition++;
            }
            k--;
            rightLimit--;
            for (i = rightLimit; i >= leftLimit; i--) {
                if(correctPosition > numberRange)
                    break;
                else if (correctPosition != currNode.getState().getAtPosition(i,k))
                    totalDistance += distance(correctPosition,currNode,i,k);
                correctPosition++;
            }
            i++;
            bottomLimit--;
            for (k = bottomLimit; k >= topLimit; k--) {
                if(correctPosition > numberRange)
                    break;
                else if (correctPosition != currNode.getState().getAtPosition(i,k))
                    totalDistance += distance(correctPosition,currNode,i,k);
                correctPosition++;
            }
            k++;
            leftLimit++;
        }
        return totalDistance;
    }

    private int distance(int value, SpaceManagementNode node, int x, int y){
        for(int i = 0; i < node.getState().getWidth(); i++){
            for(int k = 0; k < node.getState().getHeight(); k ++){
                if(node.getState().getAtPosition(i, k) == value)
                    return Math.abs(x - i) + Math.abs(y - k);
            }
        }
        return 0;
    }

    private int heuristicValue(SpaceManagementNode currNode){
        switch (heuristic){
            case FirstHeuristic :
                return tilesOutOfPlace(currNode);
            case SecondHeuristic :
                return totalRelativePosition(currNode);
            case ThirdHeuristic :
                return (tilesOutOfPlace(currNode) + totalRelativePosition(currNode))/2;
            default:
                return 0;
        }
    }

    private int getHeuristicValue(SpaceManagementNode node){
        return node.getDepth() + heuristicValue(node);
    }

    public LinkedList<SpaceManagementNode> solve(SpaceManagementState initialState){
        currNode = new SpaceManagementNode(initialState);

        do{

            node_List.addAll(getNew_Nodes(currNode));

            if(currNode.getState().checkWin()){
                solved = true;
            }
            else if (!node_List.isEmpty()){
                SpaceManagementNode bestNode = node_List.getFirst();
                for(SpaceManagementNode node : node_List){
                    if(getHeuristicValue(node) <= getHeuristicValue(bestNode))
                        bestNode = node;
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

    public enum possibleSMSHeuristics {
        FirstHeuristic("Tiles out of place"),
        SecondHeuristic("Total relative distance to correct position"),
        ThirdHeuristic("Average of the two previous methods");

        private String description;
        possibleSMSHeuristics(String description){
            this.description = description;
        }
        public String toString(){
            return description;
        }
    }
}
