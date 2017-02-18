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
    private ArrayList startup, move;
    private int distance;

    @org.junit.Before
    public void setUp() throws Exception {
        startup = new ArrayList();
        move = new ArrayList();
        startup.add(1);
        startup.add(2);
        startup.add(3);
        startup.add(5);
        bt = new BridgeTorch(startup);
        distance = 0;
    }

    @Test
    public void moveForward() {
        move.add(1);
        move.add(3);
        distance = bt.move(move);
        assertEquals(distance, 3);
        assertEquals(move, bt.getPeopleRoB());
        assertFalse(bt.getTorch());
    }

    @Test
    public void moveBack() {
        move.add(1);
        distance = bt.move(move);
        assertFalse(bt.getPeopleLoB().contains(1));
        distance = bt.move(move);
        assertEquals(distance, 1);
        assertTrue(bt.getPeopleLoB().contains(1));
    }

    @Test
    public void checkWin() {
        int total = 0;
        move.add(1);
        move.add(2);
        total += bt.move(move);
        assertFalse(bt.checkWin());
        move.remove(1);
        total += bt.move(move);
        move.add(3);
        total += bt.move(move);
        move.remove(1);
        total += bt.move(move);
        move.add(5);
        total += bt.move(move);
        assertTrue(bt.checkWin());
        assertEquals(total, 12);
    }
}