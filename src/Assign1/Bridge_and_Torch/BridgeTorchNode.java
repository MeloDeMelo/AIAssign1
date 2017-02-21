package Assign1.Bridge_and_Torch;

/**
 * Bridge and Torch problem Node
 * Written by Max DeMelo - 100937368
 * 2/19/2017
 */
public class BridgeTorchNode {

    private BridgeTorchNode parentNode;
    private boolean torchLeft;
    private int person1, person2;
    private int depth, cost;
    private BridgeTorch state;

    //node for moving people to the left of the bridge
    public BridgeTorchNode(int person1){
        this.torchLeft = false;
        this.person1 = person1;
    }

    //node for moving people to the right of the bridge
    public BridgeTorchNode(int person1, int person2){
        this.torchLeft = true;
        this.person1 = person1;
        this.person2 = person2;
    }

    //Will be the parent node of the search
    public BridgeTorchNode(BridgeTorch state){
        this.state = state;
        cost = 0;
        depth = 0;
        person1 = -1;
        person2 = -1;
    }

    public BridgeTorch getState(){
        return state;
    }

    public void setParentNode(BridgeTorchNode parentNode){
        this.state = new BridgeTorch(parentNode.getState());
        this.parentNode = parentNode;
        this.depth = parentNode.getDepth() + 1;
        this.state = new BridgeTorch(parentNode.getState());
        if(torchLeft){
            this.cost = parentNode.getCost() + state.move(person1, person2);
        }
        else{
            this.cost = parentNode.getCost() + state.move(person1);
        }
    }
    public BridgeTorchNode getParentNode(){
        return parentNode;
    }

    public int getPerson1(){
        return person1;
    }

    public int getPerson2(){
        if (torchLeft)
            return person2;
        return -1;
    }

    public int getDepth(){
        return depth;
    }

    public int getCost(){
        return cost;
    }

}
