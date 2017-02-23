package Assign1.SpaceManagement;

/**
 * Created by Max on 2/21/2017.
 */
public class SpaceManagementNode {

    private SpaceManagementNode parent;
    private int depth;
    private int initialX, initialY, endX, endY;
    private SpaceManagementState state;

    public SpaceManagementNode(int initialX, int initialY, int endX, int endY){
        this.initialX = initialX;
        this.initialY = initialY;
        this.endX = endX;
        this.endY = endY;
    }

    public SpaceManagementNode(SpaceManagementState state){
        this.state = state;
        depth = 0;
    }

    public int getDepth(){
        return depth;
    }

    public int[] getCoordinate(){
        return new int []{initialX, initialY, endX, endY};
    }

    public SpaceManagementNode getParent(){
        return parent;
    }

    public int[] getStartingPosition(){
        return new int[] {initialX, initialY};
    }

    public int[] getEndPosition(){
        return new int[] {endX, endY};
    }

    public SpaceManagementState getState(){
        return state;
    }

    public void setParent(SpaceManagementNode parent){
        this.parent = parent;
        this.state = new SpaceManagementState(parent.getState());
        this.depth = parent.getDepth() + 1;
        state.move(initialX, initialY, endX, endY);
    }
}
