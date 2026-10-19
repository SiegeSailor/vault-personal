import java.util.Scanner;

/* File: Driver.java */

/**
 * Edit History:
 * 06/14/2022 Jin Yu, Zhang Initial version.
 */

/**
 * The #2 programming assignment for BU METCS232A1 Programming with java.
 * Due by Thursday, June 16, 2022.
 * 
 * @author Jin Yu, Zhang
 */
public class Driver {
    /** Default constructor. */
    public Driver() {
    }

    /**
     * A helper to demonstrate class Statics with initial array of double numbers.
     * 
     * @param arrayOfInitialDouble {@code double[]} Initial array of double numbers.
     */
    public static void demonstrate(double[] arrayOfInitialDouble) {
        Statics statics = new Statics(arrayOfInitialDouble);

        statics.printInformation();

        System.out.println("End of demonstrating.\n");
    }

    /**
     * The driver to demonstrate assignment #2.
     * 
     * @param arguments {@code String[]} Implemented but not used.
     */
    public static void main(String[] arguments) {
        double[] arrayOfInitialDoubleSet1 = { 1.23, 2.34, 3.45, 4.56, 5.67 };
        demonstrate(arrayOfInitialDoubleSet1);
        double[] arrayOfInitialDoubleSet2 = { 100, 200, 300, 400, 500, 600, 700, 800, 900,
                1000 };
        demonstrate(arrayOfInitialDoubleSet2);
        double[] arrayOfInitialDoubleSet3 = { 10.123, 20.234, 30.345, 40.456, 50.567 };
        demonstrate(arrayOfInitialDoubleSet3);
        double[] arrayOfInitialDoubleSet4 = { 1, 1, 1, 1, 1 };
        demonstrate(arrayOfInitialDoubleSet4);
        double[] arrayOfInitialDoubleSet5 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        demonstrate(arrayOfInitialDoubleSet5);

        Statics statics = new Statics();

        Scanner keyboard = new Scanner(System.in);

        System.out.println("Please enter double number to store in the statics instance. Input STOP to end:");
        while (true) {
            String input = keyboard.nextLine();
            if (input.equals("STOP"))
                break;

            try {
                double value = Double.parseDouble(input);
                statics.insert(value);
                System.out.println(value + " inserted.");
            } catch (NumberFormatException event) {
                System.out.println("The entered number is not a double number, please try again.");
                continue;
            }
        }

        statics.printInformation();

        keyboard.close();
    }
}