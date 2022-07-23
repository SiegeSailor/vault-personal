package main;

import java.util.Iterator;
import java.util.Scanner;

import structure.Node;
import structure.Stack;
import structure.Coordinate;

public class Board {
    private static final int SIZE = 8;

    private Scanner keyboard;

    private Stack<Coordinate> stackOfChoice;

    private void empty() {
        this.stackOfChoice.clear();
        System.out.println("Choices have been cleared.");
    }

    private Coordinate input() {
        System.out.println("");

        if (!this.stackOfChoice.seeEmpty())
            System.out.println("You have made " + this.stackOfChoice.getCount() + " choices: "
                    + this.stackOfChoice + ".");

        System.out.println(
                "Please enter a coordinate such as 1,2:");
        System.out.println("DRAW - Display available slots.\nBACK - Remove the latest choice.\nCLEAR - Start over.\n");
        String input = this.keyboard.nextLine();
        switch (input) {
            case "DRAW":
                this.drawBoard();
                return this.input();
            case "BACK":
                Node<Coordinate> node = this.stackOfChoice.pop();
                System.out.println(node + " has been removed.");
                return this.input();
            case "CLEAR":
                this.empty();
                return this.input();
            default:
                String[] arrayOfInput = input.split(",");
                if (arrayOfInput.length != 2) {
                    System.out.println("Invalid format");
                    return this.input();
                }

                try {
                    return new Coordinate(Integer.parseInt(arrayOfInput[0]), Integer.parseInt(arrayOfInput[1]));
                } catch (Exception event) {
                    System.out.println("Invalid input.");
                    return this.input();
                }
        }
    }

    private boolean seeConflict(Coordinate coordinateOne, Coordinate coordinateTwo) {
        int rowOne = coordinateOne.getRow(), rowTwo = coordinateTwo.getRow(), columnOne = coordinateOne.getColumn(),
                columnTwo = coordinateTwo.getColumn();
        return (rowOne == rowTwo) || (columnOne == columnTwo)
                || (Math.abs(rowOne - rowTwo) == Math.abs(columnOne - columnTwo));
    }

    private Coordinate verify(Coordinate coordinate) {
        if (this.stackOfChoice.seeEmpty())
            return null;
        else {
            Iterator<Node<Coordinate>> iterator = this.stackOfChoice.iterator();
            while (iterator.hasNext()) {
                Node<Coordinate> node = iterator.next();
                if (node.equals(coordinate) || this.seeConflict(node.getData(), coordinate))
                    return node.getData();
            }

            return null;
        }
    }

    private void drawBoard() {
    }

    private void drawChoice() {
    }

    public Board() {
        this.keyboard = new Scanner(System.in);
        this.stackOfChoice = new Stack<Coordinate>(SIZE);
    }

    public void start() {
        while (this.stackOfChoice.getCount() < SIZE) {
            Coordinate coordinate = this.input();
            Coordinate conflict = this.verify(coordinate);
            if (conflict != null) {
                System.out.println(coordinate + " has a conflict with " + conflict + ".");
                continue;
            } else
                this.stackOfChoice.push(new Node<Coordinate>(coordinate));
        }

        drawChoice();
        System.out.println("Congratulation! You have placed " + SIZE
                + " queens on the board without conflict. Would you like to start over? (Y)");
        String input = this.keyboard.nextLine().toUpperCase();
        switch (input) {
            case "Y":
                this.empty();
                this.start();
                break;
            default:
                System.out.println(SIZE + " queens game finished.");
                break;
        }
    }
}
