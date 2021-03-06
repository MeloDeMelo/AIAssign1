package Assign1;

import Assign1.Bridge_and_Torch.*;
import Assign1.Bridge_and_Torch.BTAStar.possibleBTHeuristics;
import Assign1.SpaceManagement.*;
import Assign1.SpaceManagement.SMAStar.possibleSMSHeuristics;

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
    private SpaceManagementState sms;
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
    private void printSpaceManagement(String thing){
        System.out.println("SpaceManagement: " + thing);
    }
    private void printNote(String thing){
        System.out.println("Note: " + thing);
    }

    private possibleBTHeuristics getBTHeuristic() {
        int response;
        boolean done = false;
        possibleBTHeuristics heuristic = null;
        while (!done) {
            printBridgeAndTorch("Which Heuristic would you like to use?");
            int i = 0;
            for (possibleBTHeuristics p : possibleBTHeuristics.values()) {
                System.out.println("\t" + i + ": " + p);
                i ++;
            }
            while(!in.hasNextInt()) {
                in.next();
            }
            response = in.nextInt();
            if ((response <= possibleBTHeuristics.values().length + 1) && (response > -1)) {
                heuristic = possibleBTHeuristics.values()[response];
                done = true;
            }
            else {
                printBridgeAndTorch("Invalid error!");
            }
        }
        return heuristic;
    }

    private void startBridgeAndTorch(){
        ArrayList<Integer> input = new ArrayList<>();
        printBridgeAndTorch("Please input a list of how long it would take people to cross a bridge");
        printNote("For breadth first search no more than 6 inputs");
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
                strat = new BTBreadthFirst();
                validResponse = true;
            } else if (response.equals("2")) {
                strat = new BTDepthFirst();
                validResponse = true;
            } else if (response.equals("3")){
                strat = new BTAStar(getBTHeuristic());
                validResponse = true;
            }
        }

        printBridgeAndTorch("Solving...");
        printNote("LoB = Left of bridge (people who haven't crossed yet) and vice versa\n");
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

    private possibleSMSHeuristics getSMSHeuristic() {
        int response;
        boolean done = false;
        possibleSMSHeuristics heuristic = null;
        while (!done) {
            printSpaceManagement("Which Heuristic would you like to use?");
            int i = 0;
            for (possibleSMSHeuristics p : possibleSMSHeuristics.values()) {
                System.out.println("\t" + i + ": " + p);
                i ++;
            }
            while(!in.hasNextInt()) {
                in.next();
            }
            response = in.nextInt();
            if ((response <= possibleSMSHeuristics.values().length + 1) && (response > -1)) {
                heuristic = possibleSMSHeuristics.values()[response];
                done = true;
            }
            else {
                printSpaceManagement("Invalid error!");
            }
        }
        return heuristic;
    }

    private void startSpaceManagement(){
        ArrayList<Integer> input = new ArrayList<>();
        printSpaceManagement("Please enter the width, height and number of blank squares");
        String response = getStringInput();
        for (String s : response.split("\\s")) {
            input.add(Integer.parseInt(s));
        }
        sms = new SpaceManagementState(input.get(0), input.get(1), input.get(2));

        validResponse = false;
        while (!validResponse) {
            printSpaceManagement("Is this board okay?(\"yes\", \"no\")\n" + sms);
            response = getStringInput();
            if (response.toLowerCase().equals("yes")) {
                validResponse = true;
            } else if (response.toLowerCase().equals("no")) {
                sms.reRandomize();
            }
        }

        printSpaceManagement("Please choose one of the following options:\n1.Breadth First\n2.Depth First\n3.A*");
        validResponse = false;
        SMStrats strat = null;
        while (!validResponse) {
            response = getStringInput();
            if (response.equals("1")) {
                strat = new SMBreadthFirst();
                validResponse = true;
            } else if (response.equals("2")) {
                strat = new SMDepthFirst();
                validResponse = true;
            } else if (response.equals("3")){
                strat = new SMAStar(getSMSHeuristic());
                validResponse = true;
            }
        }

        printSpaceManagement("Solving...");
        LinkedList<SpaceManagementNode> result = strat.solve(sms);
        if(result == null){
            printSpaceManagement("Failed returning to menu");
        }else{
            for(SpaceManagementNode node : result){
                printSpaceManagement("Move: (" + node.getStartingPosition()[0] + ", " + node.getStartingPosition()[1] +
                ") to (" + node.getEndPosition()[0] + ", " + node.getEndPosition()[1] + ")");
                printSpaceManagement("State: \n" + node.getState().toString() + "\n");
            }
            printSpaceManagement("Total Cost: " + result.getLast().getDepth());
        }
    }

    public static void main(String[] args){
        String response;
        boolean quit = false, validResponse;
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
                    fe.startSpaceManagement();
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
