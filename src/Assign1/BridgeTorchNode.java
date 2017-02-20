package Assign1;

/**
 * Bridge and Torch problem Node
 * Written by Max DeMelo - 100937368
 * 2/19/2017
 */
public class BridgeTorchNode {

    //protected BridgeTorchNode parentNode;
    private boolean twoPeople;
    private int person1, person2;
    //protected int depth, cost;

    public BridgeTorchNode(int person1){
        this.twoPeople = false;
        this.person1 = person1;
    }

    public BridgeTorchNode(int person1, int person2){
        this.twoPeople = true;
        this.person1 = person1;
        this.person2 = person2;
    }
    /*
    public void setParentNode(BridgeTorchNode parentNode){
        this.parentNode = parentNode;
    }
    public BridgeTorchNode getParentNode(){
        return parentNode;
    }*/

    public int getPerson1(){
        return person1;
    }

    public int getPerson2(){
        if (twoPeople)
            return person2;
        return -1;
    }

    /*
    public int getDepth(){
        return depth;
    }

    public int getCost(){
        return cost;
    }*/

}
