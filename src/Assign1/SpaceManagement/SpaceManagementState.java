package Assign1.SpaceManagement;

import java.util.ArrayList;
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
        for(int i = 0; i < width; i ++){
            for(int k = 0; k < height; k ++){
                this.board[i][k] = sms.getAtPosition(i, k);
            }
        }
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
        return false;
    }

    public LinkedList<SpaceManagementNode> getPossibleMoves(){
        return null;
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
}
