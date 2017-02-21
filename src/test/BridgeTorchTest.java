package test;

import Assign1.Bridge_and_Torch.BridgeTorch;
import Assign1.Bridge_and_Torch.BridgeTorchNode;
import org.junit.Test;
import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Tests for Bridge and Torch base
 * Written by Max DeMelo - 100937368
 * 2/18/2017
 */
public class BridgeTorchTest {

    private BridgeTorch bt;
    private ArrayList<Integer> startup;
    private int distance;

    @org.junit.Before
    public void setUp() throws Exception {
        startup = new ArrayList();
        startup.add(1);
        startup.add(2);
        startup.add(3);
        startup.add(5);
        bt = new BridgeTorch(startup);
        distance = 0;
    }

    @Test
    public void moveForward() {
        distance = bt.move(1,3);
        assertEquals(3, distance);
        ArrayList roB = new ArrayList();
        roB.add(1);
        roB.add(3);
        assertEquals(roB, bt.getPeopleRoB());
        assertFalse(bt.getTorch());
    }

    @Test
    public void moveBack() {
        distance += bt.move(1, 2);
        assertFalse(bt.getPeopleLoB().contains(1));
        assertFalse(bt.getPeopleLoB().contains(2));
        distance += bt.move(1);
        assertEquals(3, distance);
        assertTrue(bt.getPeopleLoB().contains(1));
        assertFalse(bt.getPeopleLoB().contains(2));
    }

    @Test
    public void checkPossibleMovesLeft(){
        LinkedList<BridgeTorchNode> possibleMoves = bt.getPossibleMoves();
        LinkedList<BridgeTorchNode> correctMoves = new LinkedList<>();
        correctMoves.add(new BridgeTorchNode(1,2));
        correctMoves.add(new BridgeTorchNode(1,3));
        correctMoves.add(new BridgeTorchNode(1,5));
        correctMoves.add(new BridgeTorchNode(2,3));
        correctMoves.add(new BridgeTorchNode(2,5));
        correctMoves.add(new BridgeTorchNode(3,5));
        for (int i = 0; i < correctMoves.size(); i ++){
            assertEquals(correctMoves.get(i).getPerson1(), possibleMoves.get(i).getPerson1());
            assertEquals(correctMoves.get(i).getPerson2(), possibleMoves.get(i).getPerson2());
        }
    }

    @Test
    public void checkPossibleMovesRight(){
        distance = bt.move(1,2);
        LinkedList<BridgeTorchNode> possibleMoves = bt.getPossibleMoves();
        LinkedList<BridgeTorchNode> correctMoves = new LinkedList<>();
        correctMoves.add(new BridgeTorchNode(1));
        correctMoves.add(new BridgeTorchNode(2));
        for (int i = 0; i < correctMoves.size(); i ++){
            assertEquals(correctMoves.get(i).getPerson1(), possibleMoves.get(i).getPerson1());
        }
    }

    @Test
    public void checkWin() {
        int total = 0;
        total += bt.move(1,2);
        assertFalse(bt.checkWin());
        total += bt.move(1);
        total += bt.move(1,3);
        total += bt.move(1);
        total += bt.move(1,5);
        assertTrue(bt.checkWin());
        assertEquals(12, total);
    }
}