package Assign1;

import java.util.ArrayList;

/**
 * Bridge and Torch problem base
 * Written by Max DeMelo - 100937368
 * 2/18/2017
 */
public class BridgeTorch {

    //Left of bridge means not crossed (LoB)
    //Right of bridge means has crossed (RoB)
    private ArrayList peopleLoB, peopleRoB;
    private boolean torchLeft;
    private int numberOfPeople;

    public BridgeTorch(ArrayList peopleLoB){
        this.peopleLoB = peopleLoB;
        numberOfPeople = peopleLoB.size();
        peopleRoB = new ArrayList();
        torchLeft = true;
    }

    public int move(int[] peopleMoving){
        int timeTaken = -1;
        if ((peopleMoving.length == 2) && (torchLeft)){
            timeTaken = moveRight(peopleMoving);
        }
        else if ((peopleMoving.length == 1) &&(torchLeft)){
            timeTaken = moveRight(peopleMoving[0]);
        }
        else if (peopleMoving.length == 1){
            timeTaken = moveLeft(peopleMoving[0]);
        }
        toggleTorch();
        return timeTaken;
    }

    private int moveRight(int[] peopleMoving) {
        int person1, person2;
        person1 = moveRight(peopleMoving[0]);
        person2 = moveRight(peopleMoving[1]);
        if(person1 != -1){
            if(person2 != -1){
                return (person1 > person2) ? person1 : person2;
            }
        }
        return -1;
    }

    private int moveRight(int personMoving){
        int index = peopleLoB.indexOf(personMoving);
        if(index != -1){
            peopleLoB.remove(index);
            peopleRoB.add(personMoving);
            return personMoving;
        }
        return -1;
    }

    private int moveLeft(int personMoving){
        int index = peopleRoB.indexOf(personMoving);
        if(index != -1){
            peopleRoB.remove(index);
            peopleLoB.add(personMoving);
            return personMoving;
        }
        return -1;
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

    public ArrayList getPeopleLoB(){
        return peopleLoB;
    }

    public ArrayList getPeopleRoB(){
        return peopleRoB;
    }
}
