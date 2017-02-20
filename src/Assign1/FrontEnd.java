package Assign1;

import java.util.ArrayList;
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

    private void startBridgeAndTorch(){
        ArrayList input = new ArrayList();
        System.out.println("Please input a list of how long it would take people to cross a bridge:");
        String response = getStringInput();
        for (String s : response.split("\\s")) {
            input.add(Integer.parseInt(s));
        }
        bt = new BridgeTorch(input);

        System.out.println("Please choose one of the following options:\n1.Breadth First\n2.Depth First\n3.A*");
        validResponse = false;
        while (!validResponse) {
            response = getStringInput();
            if (response.equals("1")) {
                validResponse = true;
            } else if (response.equals("2")) {
                validResponse = true;
            } else if (response.equals("3")){
                validResponse = true;
            }
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
