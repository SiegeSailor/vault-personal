package main;

import java.util.Iterator;
import java.util.Scanner;

import structure.Node;
import structure.Stack;
import structure.Coordinate;

/**
 * Main class. This is used to solve an 8 Queens Problem. However, this can also
 * used for 10 queens or n queens by changes static variable {@code SIZE}.
 * It is also possible to be applied to 8 Kings Problem or 18 Bishop Problem by
 * changing method {@code seeConflict} and static variable {@code SYMBOL_CHESS}.
 * 
 * The mentioned factors for solving other problems above were not made as
 * parameters of constructor because this programming assignment is only for 8
 * Queens Problem.
 * 
 * @author Zhang, Jin Yu
 */
public class Board {
    private static final int SIZE = 8;

    private static final char SYMBOL_CHESS = 'Q';

    private static final char SYMBOL_OCCUPIED = 'X';

    private static final char SYMBOL_EMPTY = '-';

    private static final String COMMAND_DRAW = "DRAW";

    private static final String COMMAND_BACK = "BACK";

    private static final String COMMAND_CLEAR = "CLEAR";

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
                "Please enter a coordinate such as 1,2 or use helping commands:");
        System.out.println(
                COMMAND_DRAW + "  - Display available slots.\n" +
                        COMMAND_BACK + "  - Remove the latest choice.\n" +
                        COMMAND_CLEAR + " - Start over.\n");
        String input = this.keyboard.nextLine().toUpperCase();
        switch (input) {
            case COMMAND_DRAW:
                this.drawBoard();
                return this.input();
            case COMMAND_BACK:
                Node<Coordinate> node = this.stackOfChoice.pop();
                System.out.println(node + " has been removed.");
                return this.input();
            case COMMAND_CLEAR:
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
                if (this.seeConflict(node.getData(), coordinate))
                    return node.getData();
            }

            return null;
        }
    }

    private void drawBoard() {
        int numberOfEmptySlot = 0;
        String message = "Row / Column\n";
        for (int indexOfRow = 0; indexOfRow < SIZE + 1; indexOfRow++) {
            for (int indexOfColumn = 0; indexOfColumn < SIZE + 1; indexOfColumn++) {
                if (indexOfRow == 0 && indexOfColumn == 0) {
                    message += "  ";
                    continue;
                }

                if (indexOfRow == 0 && indexOfColumn > 0) {
                    message += indexOfColumn - 1 + " ";
                    continue;
                }

                if (indexOfColumn == 0 && indexOfRow > 0) {
                    message += indexOfRow - 1 + " ";
                    continue;
                }

                Coordinate coordinate = new Coordinate(indexOfRow - 1, indexOfColumn - 1);
                Coordinate conflict = this.verify(coordinate);
                if (conflict == null) {
                    numberOfEmptySlot++;
                    message += SYMBOL_EMPTY + " ";
                } else if (conflict.equals(coordinate))
                    message += SYMBOL_CHESS + " ";
                else
                    message += SYMBOL_OCCUPIED + " ";
            }
            if (indexOfRow != SIZE)
                message += "\n";
        }
        System.out.println(message);
        if (numberOfEmptySlot < (SIZE - this.stackOfChoice.getCount()))
            System.out.println(
                    "There is no enough slots. You may want to " + COMMAND_BACK + " or " + COMMAND_CLEAR + ".");
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

        this.drawBoard();
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
