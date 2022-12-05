import java.util.LinkedList;

public class BabyStepGiantStep {
    static final char ASCII_CODE_CONGRUENT_SYMBOL = 8773;

    static int function(int base, int congruent, int modular) {
        System.out.println("a^x " + (char) ASCII_CODE_CONGRUENT_SYMBOL + " g % p");
        // Number of steps
        int m = (int) Math.ceil(Math.sqrt((double) modular));
        System.out.println("m = ceil(sqrt(" + modular + ")) = " + m + "\n");

        int[] arrayOfResult = new int[m];
        System.out.println("Baby Step: (Solve for a^mj % p for each step)");
        System.out.format("%3s%35s%n", "i", "a^mj % p");
        for (int j = 0; j < m; j++) {
            int exponent = m * j, result = FastExponentiation.function(base, exponent, modular, false);
            System.out.format("%3s%35s%n", j, base + "^(m * j = " + (exponent) + ") % " + modular + " = " + result);
            arrayOfResult[j] = result;
        }

        LinkedList<int[]> list = new LinkedList<>();
        System.out.println("Giant Step: (Solve for g * a^-i % p for each step)");
        System.out.format("%3s%35s%n", "i", "g * a^-i % p");
        for (int i = 0; i < m; i++) {
            int exponent = modular - (i + 1),
                    result = congruent * FastExponentiation.function(base, modular - (i + 1), modular, false) % modular;
            System.out.format("%3s%35s%n", i,
                    congruent + " * (" + base + "^" + exponent + (char) ASCII_CODE_CONGRUENT_SYMBOL + base + "^-" + i
                            + " % " + modular + ") % " + modular + " = " + result);
            for (int j = 0; j < arrayOfResult.length; j++) {
                if (result == arrayOfResult[j]) {
                    list.add(new int[] { j, i, result });
                    break;
                }
            }
        }

        int x = 0;
        System.out.println("Collisions:");
        for (int[] collision : list) {
            int j = collision[0], i = collision[1], result = collision[2];
            System.out.println("(" + j + ", " + result + "), " + "(" + i + ", " + result + ")");
            System.out.println("m * j + i % p");
            x = m * j + i % modular;
            System.out.println("(" + m + " * " + j + " + " + i + ") % " + modular + " = " + x);
        }

        return x;
    }

    public static void main(String arrayOfArgument[]) {
        int base = 2, congruent = 7, modular = 53;
        int result = function(base, congruent, modular);
        System.out.println("\n" + base + "^x " + (char) ASCII_CODE_CONGRUENT_SYMBOL + " " + congruent + " % " + modular
                + ". x = " + result);
    }
}
