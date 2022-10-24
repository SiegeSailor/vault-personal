public class ExpandedEuclidean {
    static int[] recursion(int a, int b) {
        if (b == 0)
            return new int[] { a, 1, 0 };

        int[] arrayOfResult = recursion(b, a % b);
        int result = arrayOfResult[0];
        int x = arrayOfResult[2];
        int y = arrayOfResult[1] - (a / b) * arrayOfResult[2];

        System.out.println(x + " * " + a + " + " + y + " * " + b);
        return new int[] { result, x, y };
    }

    public static void main(String[] arrayOfArgument) {
        int a = 614;
        int b = 513;
        int arrayOfResult[] = recursion(a, b);
        System.out.println("\nGCD(" + a + ", " + b + ") = " + arrayOfResult[1] + " * " + a + " + "
                + arrayOfResult[2] + " * " + b + " = " + arrayOfResult[0]);
    }
}