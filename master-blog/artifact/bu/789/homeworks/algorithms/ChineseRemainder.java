public class ChineseRemainder {
    static int function(int[] arrayOfBase, int[] arrayOfModulus, int length, int modular) {
        int x = 0;

        for (int i = 0; i < length; i++) {

            // modulus = \frac{p}{modular_1}, \frac{p}{modular_2}, ...
            int modulus = modular / arrayOfModulus[i], y = 0;

            for (int j = 0; j < arrayOfModulus[i]; j++) {
                if ((modulus * j) % arrayOfModulus[i] == 1) {
                    y = j;
                    // Finding the values for y_1, y_2, ..., y_n
                    break;
                }
            }

            // x = base_1*modulus_1*y_1 + base_2*modulus_2*y_2 + ... + base_n*modulus_n*y_n
            x = x + arrayOfBase[i] * modulus * y;
        }

        return x % modular;
    }

    public static void main(String[] arrayOfArgument) {
        int[] arrayOfBase = new int[] { 2, 1, 2 };
        int[] arrayOfModulus = new int[] { 11, 19, 37 };

        int modular = 1;
        for (int modulus : arrayOfModulus)
            modular *= modulus;

        int result = function(arrayOfBase, arrayOfModulus, arrayOfBase.length, modular);
        System.out.println(result);
    }
}
