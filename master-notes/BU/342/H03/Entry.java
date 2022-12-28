import java.util.Scanner;

/*
 * Homeworks                                                                          *
 *     Question 1 and 2 are all here.                                                 *
 *                                                                                    *
 * - Author:     Zhang, Jin Yu                                                        *
 * - Email:      siege@bu.edu                                                         *
 * - Date:       July 30, 2022                                                        *
 * - Class:      MET CS342                                                            *
 * - Instructor: Vic Berry                                                            *
 */

/**
 * Driver.
 * 
 * @author Zhang, Jin Yu
 */
public class Entry {
    private static final char ASTERISK = '*';

    private static final int ASCII_0 = 48;

    private Scanner keyboard;

    /**
     * Method to ask the user to enter a valid integer number.
     * 
     * @return {@code int} Integer.
     */
    private int inputNumber() {
        System.out.println("Please enter a valid integer number:");
        try {
            String input = this.keyboard.nextLine();
            int value = Integer.parseInt(input);
            System.out.println("Your value: " + value);
            return value;
        } catch (Exception event) {
            System.out.println("Invalid input.");
            return inputNumber();
        }
    }

    /**
     * Produce a pattern of n lines of asterisks. The first line contains one
     * asterisk, the next line contains 2, and so on, up to the nth line, which
     * contains n asterisks, line n+1 contains n asterisks. The next line has n-1
     * asterisks, and so on until line number 2n which has 1 asterisk.
     * 
     * n = 3:
     * *
     * **
     * ***
     * ***
     * **
     * *
     * 
     * @param current {@code int} Recursive number of asterisks.
     * @param max     {@code int} Max number of asterisks and lines.
     */
    private void printRecursive(int current, int max) {
        if (current > max)
            return;

        System.out.print(new String("" + ASTERISK).repeat(current) + "\n");
        printRecursive(current + 1, max);
        System.out.print(new String("" + ASTERISK).repeat(current) + "\n");
    }

    /**
     * Convert a character string of digits to an integer. Example convert(“1234”);
     * returns 1234. Hint to convert a numeric character to a number subtract ASCII
     * ‘0’ from it.
     * 
     * @param input {@code String} Numeric characters.
     * @return {@code int} Recursive total.
     */
    private int convertRecursive(String input) {
        if (input.length() == 0)
            return 0;

        return ((input.charAt(0) - ASCII_0) * (int) Math.pow(10, input.length() - 1))
                + this.convertRecursive(input.substring(1));
    }

    /** Construct the object for demonstrating homework and initialize a scanner. */
    public Entry() {
        this.keyboard = new Scanner(System.in);
    }

    /**
     * Produce a pattern of n lines of asterisks. The first line contains one
     * asterisk, the next line contains 2, and so on, up to the nth line, which
     * contains n asterisks, line n+1 contains n asterisks. The next line has n-1
     * asterisks, and so on until line number 2n which has 1 asterisk.
     * 
     * n = 3:
     * *
     * **
     * ***
     * ***
     * **
     * *
     * 
     */
    public void printRecursive() {
        this.printRecursive(1, this.inputNumber());
    }

    /**
     * Convert a character string of digits to an integer. Example convert(“1234”);
     * returns 1234. Hint to convert a numeric character to a number subtract ASCII
     * ‘0’ from it.
     */
    public void convertRecursive() {
        String input = String.valueOf(this.inputNumber());
        Integer value = this.convertRecursive(input);
        System.out.println("Convert " + input + " (" + input.getClass().getName() + ") to " + value + " ("
                + value.getClass().getName() + ").");
    }

    /**
     * Actions to demonstrate the programming assignment.
     * 
     * @param arguments {@code String[]} Implemented but not used.
     */
    public static void main(String[] arguments) {
        Entry entry = new Entry();

        // Question 1
        entry.printRecursive();

        // Question 2
        entry.convertRecursive();
    }
}
