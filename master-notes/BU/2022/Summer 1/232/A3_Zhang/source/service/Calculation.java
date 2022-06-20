package source.service;

import java.util.*;

/* File: Calculation.java */

/**
 * Edit History:
 * 06/20/2022 Jin Yu, Zhang Initial version.
 */

/**
 * The collection of math helpers.
 * 
 * @author Jin Yu, Zhang
 */
public class Calculation {
    /** Default constructor. */
    public Calculation() {
    }

    /**
     * Get all factors for an integer.
     * 
     * @param input {@code int} Desired integer.
     * @return The array list of factors.
     */
    public static ArrayList<Integer> findMaxFactor(int input) {
        ArrayList<Integer> arrayListOfFactors = new ArrayList<Integer>();

        for (int index = 1; index <= Math.sqrt(input); index++) {
            if (input % index == 0) {
                int divided = input / index;
                if (divided == index) {
                    arrayListOfFactors.add(divided);
                } else {
                    arrayListOfFactors.add(index);
                    arrayListOfFactors.add(divided);
                }
            }
        }
        return arrayListOfFactors;
    }
}
