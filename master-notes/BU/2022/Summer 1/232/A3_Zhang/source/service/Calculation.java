package source.service;

import java.util.*;

public class Calculation {
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
