package Assign1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Interactive Front End for Assignment 1 for Comp 4106
 * Written by Max DeMelo - 100937368
 * 2/19/2017
 */
public class FrontEnd {

    private BridgeTorch bt;
    private Scanner in;
    private boolean validResponse;

    private FrontEnd(){
        in = new Scanner(System.in);
        validResponse = false;
    }

    private String getStringInput() {
        String response;
        while(!in.hasNextLine()) {
            in.next();
        }
        response = in.nextLine();
        return response;
    }

    private void printBridgeAndTorch(String thing){
        System.out.println("BridgeAndTorch: " + thing);
    }

    private void startBridgeAndTorch(){
        ArrayList input = new ArrayList();
        printBridgeAndTorch("Please input a list of how long it would take people to cross a bridge:");
        String response = getStringInput();
        for (String s : response.split("\\s")) {
            input.add(Integer.parseInt(s));
        }
        bt = new BridgeTorch(input);

        printBridgeAndTorch("Please choose one of the following options:\n1.Breadth First\n2.Depth First\n3.A*");
        validResponse = false;
        BTStrats strat = null;
        while (!validResponse) {
            response = getStringInput();
            if (response.equals("1")) {
                validResponse = true;
            } else if (response.equals("2")) {
                strat = new BTDepthFirst();
                validResponse = true;
            } else if (response.equals("3")){
                validResponse = true;
            }
        }

        printBridgeAndTorch("Solving...");
        printBridgeAndTorch("LoB = Left of bridge (people who haven't crossed yet) and vice versa\n");
        LinkedList<BridgeTorchNode> result = strat.solve(bt);
        if(result == null){
            printBridgeAndTorch("Failed returning to menu");
        }else{
            for(BridgeTorchNode node : result){
                if (node.getPerson1() != -1){
                    if(node.getPerson2() != -1){
                        printBridgeAndTorch("Move " + node.getPerson1() + " and " + node.getPerson2());
                    }else{
                        printBridgeAndTorch("Move " + node.getPerson1());
                    }
                }
                printBridgeAndTorch("State: " + node.getState().toString() + "\n");
            }
            printBridgeAndTorch("Total Cost: " + result.getLast().getCost());
        }
    }

    public static void main(String[] args){
        String response;
        boolean quit = false, validResponse = false;
        FrontEnd fe = new FrontEnd();

        while(!quit) {
            System.out.println("Please choose one of the following options:\n1.Bridge and Torch\n2.Space Management");
            validResponse = false;
            while (!validResponse) {
                response = fe.getStringInput();
                if (response.equals("1")) {
                    fe.startBridgeAndTorch();
                    validResponse = true;
                } else if (response.equals("2")) {
                    validResponse = true;
                }
            }

            System.out.println("Start again?");
            validResponse = false;
            while (!validResponse) {
                response = fe.getStringInput();
                if (response.toLowerCase().equals("yes")) {
                    validResponse = true;
                } else if (response.toLowerCase().equals("no")) {
                    quit = true;
                    validResponse = true;
                }
            }
        }
    }
}
