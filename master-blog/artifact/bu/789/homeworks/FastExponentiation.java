public class FastExponentiation {
    static final char ASCII_CODE_CONGRUENT_SYMBOL = 8773;

    static int recursion(int base, int exponent, int product, int modular, boolean isOut) {
        if (exponent == 0)
            return product;

        if (isOut)
            System.out.format("%10s%10s%10s%n", base, exponent, product);
        return exponent % 2 == 0
                ? recursion((base * base) % modular, exponent / 2, product, modular, isOut)
                : recursion(base, exponent - 1, (base * product) % modular, modular, isOut);
    }

    static int function(int base, int exponent, int modular, boolean isOut) {
        if (isOut)
            System.out.format("%10s%10s%10s%n", "x", "e", "Y");

        return recursion(base, exponent, 1, modular, isOut);
    }

    public static void main(String[] arrayOfArgument) {
        int base = 3, exponent = 15, modular = 17;
        System.out.println(base + "^" + exponent + " % " + modular + " " + (char) ASCII_CODE_CONGRUENT_SYMBOL + " "
                + function(base, exponent, modular, true));
    }
}
