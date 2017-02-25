package Assign1.SpaceManagement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Max on 2/21/2017.
 */
public class SpaceManagementState {

    private int[][] board;
    private int blankSpaces, numberRange, width, height;
    private Random random;

    public SpaceManagementState(int width, int height, int blankSpaces){
        this.width = width;
        this.height = height;
        this.board = new int[width][height];
        this.blankSpaces = blankSpaces;
        this.numberRange = width*height-blankSpaces;
        random = new Random();
        board_init();
    }

    public SpaceManagementState(SpaceManagementState sms){
        this.width = sms.getWidth();
        this.height = sms.getHeight();
        this.board = new int[sms.getWidth()][sms.getHeight()];
        this.blankSpaces = sms.getBlankSpaces();
        this.numberRange = width*height-blankSpaces;
        setBoard(sms.getBoard());
    }

    public void move(int startingX, int startingY, int endX, int endY){
        if((startingX >= 0) && (startingX < width)){
            if((startingY >= 0) && (startingY < height)){
                if((endX >= 0) && (endX < width)){
                    if((endY >= 0) && (endY < height)){
                        int inbetween = getAtPosition(endX, endY);
                        board[endX][endY] = getAtPosition(startingX, startingY);
                        board[startingX][startingY] = inbetween;

                    }
                }
            }
        }
    }

    private void board_init(){
        ArrayList<Integer> alreadyChosen = new ArrayList<>();
        int currNum, blanks = 0;
        for(int i = 0; i < width; i ++) {
            for (int k = 0; k < height; k++) {
                if(alreadyChosen.isEmpty()){
                    currNum = random.nextInt(numberRange) + 1;
                }
                else if(alreadyChosen.size() != numberRange) {
                    do {
                        currNum = random.nextInt(numberRange) + 1;
                    } while (alreadyChosen.contains(currNum));
                }
                else{
                    currNum = -1;
                }
                if(blanks < blankSpaces){
                    if((random.nextBoolean()) && (random.nextBoolean())){
                        blanks ++;
                        currNum = -1;
                    }
                }
                if(currNum != -1)
                    alreadyChosen.add(currNum);
                board[i][k] = currNum;
            }
        }
    }

    public void reRandomize(){
        board_init();
    }

    public boolean checkWin(){
        int correctPosition = 1;
        int i, k = 0;
        int rightLimit = width-1, bottomLimit = height-1, leftLimit = 0, topLimit = 0;

        while(correctPosition < numberRange) {
            for (i = leftLimit; i <= rightLimit; i++) {
                if((correctPosition == numberRange) && (correctPosition == getAtPosition(i,k)))
                    return true;
                else if (correctPosition != getAtPosition(i,k))
                    return false;
                else
                    correctPosition++;
            }
            i--;
            topLimit++;
            for (k = topLimit; k <= bottomLimit; k++) {
                if((correctPosition == numberRange) && (correctPosition == getAtPosition(i,k)))
                    return true;
                else if (correctPosition != getAtPosition(i,k))
                    return false;
                else
                    correctPosition++;
            }
            k--;
            rightLimit--;

            for (i = rightLimit; i >= leftLimit; i--) {
                if((correctPosition == numberRange) && (correctPosition == getAtPosition(i,k)))
                    return true;
                else if (correctPosition != getAtPosition(i,k))
                    return false;
                else
                    correctPosition++;
            }
            i++;
            bottomLimit--;
            for (k = bottomLimit; k >= topLimit; k--) {
                if((correctPosition == numberRange) && (correctPosition == getAtPosition(i,k)))
                    return true;
                else if (correctPosition != getAtPosition(i,k))
                    return false;
                else
                    correctPosition++;
            }
            k++;
            leftLimit++;
        }
        return false;
    }

    public void setBoard(int[][] board){
        for(int i = 0; i < width; i ++){
            for(int k = 0; k < height; k ++){
                this.board[i][k] = board[i][k];
            }
        }
    }

    public int[][] getBoard(){
        return board;
    }

    public ArrayList<SpaceManagementNode> getPossibleMoves(){
        ArrayList<SpaceManagementNode> results = new ArrayList<>();

        for(int[] startCoordinate : getBlankSpaceCoordinates()){
            for(int[] endCoordinate : getSurroundingCoordinates(startCoordinate[0], startCoordinate[1])){
                if(board[endCoordinate[0]][endCoordinate[1]] != -1){
                    results.add(new SpaceManagementNode(startCoordinate[0], startCoordinate[1], endCoordinate[0], endCoordinate[1]));
                }
            }
        }

        for(int i = 0; i < height-1; i ++) {
            for(int k = 0; k < width; k ++) {
                for (int[] lCoordinate : getLCorrdinates(i, k)) {
                    results.add(new SpaceManagementNode(i, k, lCoordinate[0], lCoordinate[1]));
                }
            }
        }
        return results;
    }

    private ArrayList<int[]> getLCorrdinates(int x, int y){
        ArrayList<int[]> results = new ArrayList<>();
        if((notOutOfBounds(x-1,y+2)) && (getAtPosition(x-1, y+2) != -1)){
            results.add(new int[] {x-1,y+2});
        }
        if((notOutOfBounds(x+1,y+2)) && (getAtPosition(x+1, y+2) != -1)){
            results.add(new int[] {x+1,y+2});
        }
        if((notOutOfBounds(x-2,y+1)) && (getAtPosition(x-2, y+1) != -1)){
            results.add(new int[] {x-2,y+1});
        }
        if((notOutOfBounds(x+2,y+1)) && (getAtPosition(x+2, y+1) != -1)){
            results.add(new int[] {x+2,y+1});
        }
        return results;
    }

    private boolean notOutOfBounds(int x, int y){
        if ((x>-1) &&(x<width)){
            if ((y>-1) &&(y<height))
                return true;
        }
        return false;
    }

    private ArrayList<int[]> getBlankSpaceCoordinates(){
        ArrayList<int[]> results = new ArrayList<>();
        int[] coordinate = new int[2];
        for(int i = 0; i < width; i ++){
            for (int k = 0; k < height; k ++){
                if(board[i][k] == -1) {
                    coordinate[0] = i;
                    coordinate[1] = k;
                    results.add(coordinate);
                }
            }
        }
        return results;
    }

    private ArrayList<int[]> getSurroundingCoordinates(int x, int y){
        ArrayList<int[]> results = new ArrayList<>();
        for(int i = -1; i < 2; i ++){
            for (int k = -1; k < 2; k ++){
                if((notOutOfBounds(x+k, y+i)) && !((x+k == x) && (y+i == y)))
                    results.add(new int[] {x+k, y+i});
            }
        }
        return results;
    }

    public String toString(){
        String board = "";
        for(int i = 0; i < height*2-1; i ++){
            for (int k = 0; k < width*2-1; k ++){
                if(i%2 == 0){
                    if(k%2 == 0){
                        if (getAtPosition(k/2, i/2) != -1)
                            board += getAtPosition(k/2, i/2);
                        else
                            board += " ";
                    }else{
                        board += "|";
                    }
                }else{
                    if(k%2 == 0){
                        board += "-";
                    }
                    else{
                        board += " ";
                    }
                }
            }
            board += "\n";
        }
        return board;
    }

    public int getAtPosition(int x, int y){
        return board[x][y];
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public int getBlankSpaces(){
        return blankSpaces;
    }

    @Override
    public int hashCode(){
        int hash = 17;
        for(int[] x : board){
            hash = hash * Arrays.hashCode(x);
        }
        return hash;
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        else if((o == null) || !(o instanceof SpaceManagementState)){
            return false;
        }
        SpaceManagementState sms = (SpaceManagementState) o;

        for(int i = 0; i < width; i ++){
            for (int k = 0; k < height; k ++){
                if (sms.getBoard()[i][k] != board[i][k])
                    return false;
            }
        }
        return true;
    }
}
