import java.util.HashSet;

public class PrimitiveRootSearch {
    static int[] function(int[][] table, int modular) {
        int[] arrayOfResult = new int[table.length];
        for (int index = 0; index < arrayOfResult.length; index++)
            arrayOfResult[index] = -1;

        for (int index = 0; index < table.length + 1; index++)
            System.out.printf("%5s", index != 0 ? "b^" + index : "b");
        System.out.print("\n");

        for (int indexRow = 0; indexRow < table.length; indexRow++) {
            HashSet<Integer> set = new HashSet<>();
            boolean isPrimitiveRoot = true;

            System.out.printf("%5d", indexRow + 1);
            for (int indexColumn = 0; indexColumn < table[0].length; indexColumn++) {
                int base = indexRow + 1, exponent = indexColumn + 1;
                int exponential = FastExponentiation.function(base, exponent, modular, false);
                table[indexRow][indexColumn] = exponential;
                System.out.printf("%5d", table[indexRow][indexColumn]);

                if (set.contains(exponential))
                    isPrimitiveRoot = false;
                set.add(exponential);
            }
            System.out.printf("%10s", isPrimitiveRoot ? "Yes" : "");
            System.out.print("\n");

            if (isPrimitiveRoot)
                arrayOfResult[indexRow] = indexRow + 1;
        }

        return arrayOfResult;
    }

    public static void main(String arrayOfArgument[]) {
        int prime = 131;
        int phi = prime - 1;

        System.out.println("m = " + prime + ". phi(m) is " + phi);

        int[][] table = new int[phi][phi];
        int[] arrayOfResult = function(table, prime);
        System.out.println("Primitive roots are:");
        for (int result : arrayOfResult)
            if (result != -1)
                System.out.println(result + ". " + "There are " + phi + " distinct values in " +
                        result + "^1 " + "to " + result + "^" + phi + ". " +
                        "The order equals to phi(m)");
        System.out.print("\n");
    }
}
