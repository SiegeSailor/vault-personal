public class SeePrime {
    static boolean function(int input) {
        for (int i = 2; i < input; i++)
            if (input % i == 0)
                return false;

        return true;
    }

    public static void main(String[] arrayOfArgument) {
        int input = 4;
        System.out.println(input + " is " + (function(input) ? "" : "not ") + "prime");
    }
}
