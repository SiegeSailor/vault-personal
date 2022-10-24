public class Euclidean {
    public static int recursion(int a, int b) {
        if (a % b == 0)
            return b;

        System.out.println(a + " = " + (a / b) + " * " + b + " + " + (a % b));
        return recursion(b, a % b);
    }

    public static void main(String[] arrayOfArgument) {
        int a = 614, b = 513, result;

        result = recursion(a, b);
        System.out.println("\nGCD(" + a + " , " + b + ") = " + result);
    }
}