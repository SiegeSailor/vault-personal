import java.util.Random;

public class NaorReingoldPseudorandomNumberGenerator {
    public static void main(String args[]) {
        int p = 7, l = 3, g = 2, n = 4, x;
        int[] a = { 1, 2, 2, 1 };
        int[] bin = new int[4];
        Random random = new Random();
        System.out.println("The Random numbers are: ");
        for (int i = 0; i < 10; i++) {
            x = random.nextInt(17);
            for (int j = 3; j >= 0; j--) {
                bin[j] = x % 2;
                x /= 2;
            }
            int mul = 1;
            for (int k = 0; k < 4; k++)
                mul *= Math.pow(a[k], bin[k]);
            System.out.println(Math.pow(g, mul));
        }
    }
}

// // Java Program to Implement Naor-Reingold
// // Pseudo Random Function
// import java.util.*;

// public class NaorReingoldPseudorandomNumberGenerator {
// public static void randomNumbers() {
// // Creating arrays and defining variables
// int p = 7, l = 2, g = 3, n = 6, x;

// int a[] = { 1, 2, 2, 1 };

// int arr[] = new int[4];

// Random random = new Random();

// int num = 10;
// System.out.println("The Random numbers are: ");

// // Generating Random Numbers using
// // Naor-Reingold Pseudo Random Function approach
// for (int i = 0; i < num; i++) {
// x = random.nextInt(num) % 16;

// for (int j = 3; j >= 0; j--) {
// arr[j] = x % 2;
// x /= 2;
// }
// int mult = 1;

// for (int k = 0; k < 4; k++) {
// mult *= Math.pow(a[k], arr[k]);
// }
// System.out.print(Math.pow(g, mult) + ", ");
// }
// }

// public static void main(String args[]) {
// randomNumbers();
// }
// }