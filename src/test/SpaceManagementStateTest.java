package test;

import Assign1.SpaceManagement.SpaceManagementState;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Max on 2/21/2017.
 */
public class SpaceManagementStateTest {

    SpaceManagementState sms;

    @org.junit.Before
    public void setUp() throws Exception{
        sms = new SpaceManagementState(3,3,1);
    }

    @Test
    public void init(){
        ArrayList<Integer> alreadyChosen = new ArrayList<>();
        boolean doubles = true;
        int numOfBlanks = 0;
        for(int i = 0; i < sms.getWidth(); i ++){
            for (int k = 0; k < sms.getHeight(); k ++){
                if (sms.getAtPosition(i,k) == -1)
                    numOfBlanks ++;
                else{
                    if(alreadyChosen.contains(sms.getAtPosition(i,k)))
                        doubles = false;
                    else
                        alreadyChosen.add(sms.getAtPosition(i,k));
                }
            }
        }
        assertEquals(sms.getBlankSpaces(), numOfBlanks);
        assertTrue(doubles);
    }

    @Test
    public void checkWin(){

    }

    @Test
    public void getPossibleMoves(){

    }

    @Test
    public void move(){

    }
}
