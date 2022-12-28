import java.util.Scanner;

/*
 * Homework                                                                           *
 *     You should provide an interactive or command-line test application for the     *
 *     hash table. Make the hash table 31 entries, and make sure at least one         *
 *     collision occurs in your data input.                                           *
 *                                                                                    *
 * - Author:     Zhang, Jin Yu                                                        *
 * - Email:      siege@bu.edu                                                         *
 * - Date:       August 6, 2022                                                       *
 * - Class:      MET CS342                                                            *
 * - Instructor: Vic Berry                                                            *
 */

/**
 * Driver.
 * 
 * @author Zhang, Jin Yu
 */
public class Entry {
    /** Command to evoke stop. */
    private static final String COMMAND_STOP = "STOP";

    /** Command to evoke get. */
    private static final String COMMAND_GET = "GET";

    /** Command to evoke put. */
    private static final String COMMAND_PUT = "PUT";

    /** Command to evoke delete. */
    private static final String COMMAND_DELETE = "DELETE";

    /** Command to evoke contains. */
    private static final String COMMAND_CONTAINS = "CONTAINS";

    /** Command to evoke printHash. */
    private static final String COMMAND_PRINT_HASH = "PRINT_HASH";

    /**
     * Actions to demonstrate the homework assignment.
     * 
     * @param arguments {@code String[]} Implemented but not used.
     */
    public static void main(String[] arguments) {
        Scanner keyboard = new Scanner(System.in);
        // The size is hard-coded since it is a requirement.
        HashTable table = new HashTable(31);
        while (true) {
            System.out.println("Enter a command:");
            String input = keyboard.nextLine().toUpperCase();

            if (input.equals(COMMAND_STOP)) {
                System.out.println("System exists.");
                keyboard.close();
                return;
            }

            if (input.equals(COMMAND_GET)) {
                System.out.println("Enter a key:");
                String key = keyboard.nextLine();
                try {
                    int value = Integer.parseInt(key);
                    System.out.println(key + " has a value " + table.get(value) + ".");
                } catch (Exception event) {
                    System.out.println("Invalid input.");
                    continue;
                }
                continue;
            }

            if (input.equals(COMMAND_PUT)) {
                System.out.println("Enter a value:");
                String integer = keyboard.nextLine();
                try {
                    int value = Integer.parseInt(integer);
                    table.put(value);
                    System.out.println(value + " has been put into the table.");
                } catch (Exception event) {
                    System.out.println("Invalid input.");
                    continue;
                }
                continue;
            }

            if (input.equals(COMMAND_DELETE)) {
                System.out.println("Enter a value:");
                String integer = keyboard.nextLine();
                try {
                    int value = Integer.parseInt(integer);
                    boolean isFound = table.contains(value);
                    if (isFound) {
                        table.delete(value);
                        System.out.println("Value " + integer + " has been deleted.");
                    } else {
                        System.out.println("The value is not in the table.");
                    }
                } catch (Exception event) {
                    System.out.println("Invalid input.");
                    continue;
                }
                continue;
            }

            if (input.equals(COMMAND_CONTAINS)) {
                System.out.println("Enter a value:");
                String integer = keyboard.nextLine();
                try {
                    int value = Integer.parseInt(integer);
                    System.out.println(table.contains(value) ? "The value is in the table."
                            : "The value is not in the table.");
                } catch (Exception event) {
                    System.out.println("Invalid input.");
                    continue;
                }
                continue;
            }

            if (input.equals(COMMAND_PRINT_HASH)) {
                table.printHash();
                continue;
            }

            System.out.println("Invalid command.");
            continue;
        }
    }
}