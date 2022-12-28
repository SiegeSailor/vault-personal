import java.util.Arrays;

/* File: Statics.java */

/**
 * Edit History:
 * 06/14/2022 Jin Yu, Zhang Initial version.
 */

/**
 * Class for Statics.
 * 
 * @author Jin Yu, Zhang
 */
public class Statics {
    private double[] arrayOfDouble = new double[0];

    /** Increase the size of arrayOfDouble by 1. */
    private void extend() {
        arrayOfDouble = Arrays.copyOf(arrayOfDouble, arrayOfDouble.length + 1);
    }

    /** Default constructor. */
    public Statics() {
    }

    /**
     * Constructor with initial input.
     * 
     * @param arrayOfInitialDouble {@code double[]} Initial array of double numbers.
     */
    public Statics(double[] arrayOfInitialDouble) {
        arrayOfDouble = arrayOfInitialDouble.clone();
    }

    /**
     * Increase the size of arrayOfDouble by 1 and insert the input to the last
     * position. (Solution for a)
     * 
     * @param input {@code double} Input double number.
     */
    public void insert(double input) {
        extend();
        arrayOfDouble[arrayOfDouble.length - 1] = input;
    }

    /**
     * Get the sum of arrayOfDouble.
     * 
     * @return {@code double} Sum of arrayOfDouble.
     */
    public double getSum() {
        double sum = 0.0;
        for (double member : arrayOfDouble) {
            sum += member;
        }
        return sum;
    }

    /**
     * Get the average of arrayOfDouble. (Solution for b)
     * 
     * @return {@code double} Average of arrayOfDouble.
     */
    public double getAverage() {
        return getSum() / arrayOfDouble.length;
    }

    /**
     * Get the standard deviation of arrayOfDouble. (Solution for c)
     * 
     * @return {@code double} Standard deviation of arrayOfDouble.
     */
    public double getStandardDeviation() {
        double average = getAverage();
        double sum = 0.0;
        for (double member : arrayOfDouble) {
            sum += Math.pow(member - average, 2);
        }
        return Math.sqrt(sum / ((arrayOfDouble.length) - 1));
    }

    /** Print all member of arrayOfDouble. (Solution for d) */
    public void printMember() {
        System.out.println("The current members inside are:");
        for (double member : arrayOfDouble) {
            System.out.print(member + ", ");
        }
    }

    /** Print the average and the standard deviation. (Solution for e) */
    public void printStatics() {
        System.out.println(this);
    }

    /**
     * Print all information of arrayOfDouble. Such as Average, Standard Deviation,
     * Sum, Length.
     */
    public void printInformation() {
        printMember();
        System.out.println("\n" + this + " Sum: " + getSum() + " Length: " + arrayOfDouble.length);
    }

    /** Override method toString */
    public String toString() {
        return "Average: " + getAverage() + " Standard Deviation: " + getStandardDeviation();
    }

}
