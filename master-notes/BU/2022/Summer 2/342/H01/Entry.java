/* File: Entry.java */

import java.util.Arrays;
import java.util.Comparator;

public class Entry {
    static public void sort() {
        Integer[] arrayOfInteger = { 2, 5, 1, 6 };
        Arrays.sort(arrayOfInteger, new Comparator<Integer>() {
            @Override
            public int compare(Integer previous, Integer next) {
                if (previous < next)
                    return -1;
                return 0;
            }
        });
        for (Integer integer : arrayOfInteger) {
            System.out.print(integer + ", ");
        }
    }

    public static void main(String[] args) {
        sort();
    }
}
