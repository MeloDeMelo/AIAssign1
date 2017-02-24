package Assign1.Bridge_and_Torch;

import java.util.LinkedList;

/**
 * Solves Bridge and Torch problem with A* Strategy
 * Written by Max DeMelo - 100937368
 * 2/21/2017
 */
public class BTAStar extends BTStrats{

    private possibleHeuristics heuristic;

    public BTAStar(possibleHeuristics heuristic){
        this.heuristic = heuristic;
        init();
    }

    private int sumOfPeopleLeftOfBridgeHeuristicValue(BridgeTorchNode currNode){
        int value = 0;
        for(Integer person : currNode.getState().getPeopleLoB()){
            value += person;
        }
        return value;
    }

    private int bestTravelTimeRemaining(BridgeTorchNode currNode){
        int value = 0;
        int minValue = Integer.MIN_VALUE;
        for(Integer person : currNode.getState().getPeopleLoB()){
            value += person;
            if(minValue > person)
                minValue = person;
        }
        if(minValue != Integer.MIN_VALUE)
            value += minValue * (currNode.getState().getPeopleLoB().size() - 2);
        return value;
    }

    private int heuristicValue(BridgeTorchNode currNode){
        switch (heuristic){
            case FirstHeuristic :
                return sumOfPeopleLeftOfBridgeHeuristicValue(currNode);
            case SecondHeuristic :
                return bestTravelTimeRemaining(currNode);
            case ThirdHeuristic :
                return (bestTravelTimeRemaining(currNode) + sumOfPeopleLeftOfBridgeHeuristicValue(currNode))/2;
            default:
                return 0;
        }
    }

    private int getHeuristicValue(BridgeTorchNode node){
        return node.getCost() + heuristicValue(node);
    }

    public LinkedList<BridgeTorchNode> solve(BridgeTorch initialState) {
        currNode = new BridgeTorchNode(initialState);

        do{

            node_List.addAll(getNew_Nodes(currNode));

            if(currNode.getState().checkWin()){
                solved = true;
            }
            else if (!node_List.isEmpty()){
                BridgeTorchNode bestNode = node_List.getFirst();
                for(BridgeTorchNode node : node_List){
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

    public enum possibleHeuristics {
        FirstHeuristic("The sum of People Still Left of Bridge"),
        SecondHeuristic("Best Travel Time Remaining"),
        ThirdHeuristic("Average of the previous two heuristics");

        private String description;
        possibleHeuristics(String description){
            this.description = description;
        }
        public String toString(){
            return description;
        }
    }
}
