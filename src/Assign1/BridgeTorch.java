package Assign1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Bridge and Torch problem base
 * Written by Max DeMelo - 100937368
 * 2/18/2017
 */
public class BridgeTorch {

    //Left of bridge means not crossed (LoB)
    //Right of bridge means has crossed (RoB)
    private ArrayList<Integer> peopleLoB, peopleRoB;
    private boolean torchLeft;
    private int numberOfPeople;

    public BridgeTorch(ArrayList<Integer> peopleLoB){
        this.peopleLoB = peopleLoB;
        numberOfPeople = peopleLoB.size();
        peopleRoB = new ArrayList();
        torchLeft = true;
    }

    //Moves a person left over the bridge
    public int move(int personMoving){
        if (!torchLeft){
            if(peopleRoB.contains(personMoving)){
                peopleRoB.remove(peopleRoB.indexOf(personMoving));
                peopleLoB.add(personMoving);
                toggleTorch();
                return personMoving;
            }
        }
        return -1;
    }

    //Moves two people right over the bridge
    public int move(int person1, int person2){
        if (torchLeft){
            if ((peopleLoB.contains(person1)) && (peopleLoB.contains(person2))) {
                peopleLoB.remove(peopleLoB.indexOf(person1));
                peopleRoB.add(person1);
                peopleLoB.remove(peopleLoB.indexOf(person2));
                peopleRoB.add(person2);
                toggleTorch();
                return (person1 > person2) ? person1 : person2;
            }
        }
        return -1;
    }

    public LinkedList<BridgeTorchNode> getPossibleMoves(){
        LinkedList<BridgeTorchNode> possibleMoves = new LinkedList<>();
        if (!torchLeft){
            for (int i = 0; i < peopleRoB.size(); i ++){
                possibleMoves.add(new BridgeTorchNode(peopleRoB.get(i)));
            }
        }else{
            for (int i = 0; i < peopleLoB.size(); i ++){
                for(int k = i + 1; k < peopleLoB.size(); k++) {
                    possibleMoves.add(new BridgeTorchNode(peopleLoB.get(i),peopleLoB.get(k)));
                }
            }
        }
        return possibleMoves;
    }

    public boolean checkWin(){
        return (peopleLoB.isEmpty()) && (peopleRoB.size() == numberOfPeople) && (!torchLeft);
    }

    private void toggleTorch(){
        torchLeft = !torchLeft;
    }

    public boolean getTorch(){
        return torchLeft;
    }

    public List<Integer> getPeopleLoB(){
        return peopleLoB;
    }

    public List<Integer> getPeopleRoB(){
        return peopleRoB;
    }
}
