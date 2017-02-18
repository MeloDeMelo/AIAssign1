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

    public int move(ArrayList peopleMoving){
        if (peopleMoving.size() == 1){
            if (torchLeft){
                if(peopleLoB.contains(peopleMoving.get(0))){
                    peopleLoB.remove(peopleMoving.get(0));
                    peopleRoB.add(peopleMoving.get(0));
                }
                else{
                    return -1;
                }
            }
            else {
                if(peopleRoB.contains(peopleMoving.get(0))){
                    peopleRoB.remove(peopleMoving.get(0));
                    peopleLoB.add(peopleMoving.get(0));
                }
                else{
                    return -1;
                }
            }
            toggleTorch();
            return (int) peopleMoving.get(0);
        }
        else if((peopleMoving.size() == 2) && (torchLeft)){
            int longest = -1;
            if(peopleLoB.contains(peopleMoving.get(0))){
                peopleLoB.remove(peopleMoving.get(0));
                peopleRoB.add(peopleMoving.get(0));
                if(peopleLoB.contains(peopleMoving.get(1))){
                    peopleLoB.remove(peopleMoving.get(1));
                    peopleRoB.add(peopleMoving.get(1));
                    toggleTorch();
                    return ((int)peopleMoving.get(0) > (int)peopleMoving.get(1)) ? (int)peopleMoving.get(0) : (int)peopleMoving.get(1);
                }
            }
            return longest;
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
