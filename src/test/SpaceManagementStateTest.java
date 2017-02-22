package test;

import Assign1.SpaceManagement.SpaceManagementNode;
import Assign1.SpaceManagement.SpaceManagementState;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Max on 2/21/2017.
 */
public class SpaceManagementStateTest {

    private SpaceManagementState sms;

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
        int[][] newBoard = {{1,8,7},{2,-1,6},{3,4,5}};
        sms.setBoard(newBoard);
        assertTrue(sms.checkWin());
    }

    @Test
    public void move(){
        int position1 = sms.getAtPosition(0,2);
        int position2 = sms.getAtPosition(2,0);
        sms.move(0,2,2,0);
        assertEquals(position2, sms.getAtPosition(0,2));
        assertEquals(position1, sms.getAtPosition(2,0));
    }
}
