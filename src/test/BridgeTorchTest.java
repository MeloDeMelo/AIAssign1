package test;

import Assign1.BridgeTorch;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Tests for Bridge and Torch base
 * Written by Max DeMelo - 100937368
 * 2/18/2017
 */
public class BridgeTorchTest {

    private BridgeTorch bt;
    private ArrayList startup;
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
        int[] move = {1,3};
        distance = bt.move(move);
        assertEquals(3, distance);
        ArrayList roB = new ArrayList();
        roB.add(1);
        roB.add(3);
        assertEquals(roB, bt.getPeopleRoB());
        assertFalse(bt.getTorch());
    }

    @Test
    public void moveBack() {
        int[] move = {1};
        distance = bt.move(move);
        assertFalse(bt.getPeopleLoB().contains(1));
        distance = bt.move(move);
        assertEquals(1, distance);
        assertTrue(bt.getPeopleLoB().contains(1));
    }

    @Test
    public void checkWin() {
        int total = 0;
        int[] move = {1,2};
        int[] moveBack = {1};
        total += bt.move(move);
        assertFalse(bt.checkWin());
        total += bt.move(moveBack);
        int[] move1 = {1,3};
        total += bt.move(move1);
        total += bt.move(moveBack);
        int[] move2 = {1,5};
        total += bt.move(move2);
        assertTrue(bt.checkWin());
        assertEquals(12, total);
    }
}