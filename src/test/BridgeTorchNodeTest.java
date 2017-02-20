package test;

import Assign1.BridgeTorch;
import Assign1.BridgeTorchNode;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Max on 2/20/2017.
 */
public class BridgeTorchNodeTest {

    private BridgeTorch bt;
    private ArrayList<Integer> startup;
    private int distance;
    private BridgeTorchNode firstNode;
    private LinkedList<BridgeTorchNode> moves;

    @org.junit.Before
    public void setUp() throws Exception {
        startup = new ArrayList();
        startup.add(1);
        startup.add(2);
        startup.add(3);
        startup.add(5);
        bt = new BridgeTorch(startup);
        distance = 0;
        firstNode = new BridgeTorchNode(bt);
        moves = new LinkedList<>();
    }

    @Test
    public void setParent(){
        moves = firstNode.getState().getPossibleMoves();
        for (BridgeTorchNode move : moves){
             move.setParentNode(firstNode);
        }
        int larger;
        for (BridgeTorchNode move : moves){
            assertEquals(1, move.getDepth());
            larger = (move.getPerson1() > move.getPerson2())? move.getPerson1() : move.getPerson2();
            assertEquals(larger, move.getCost());
            assertEquals(firstNode, move.getParentNode());
        }
    }
}
