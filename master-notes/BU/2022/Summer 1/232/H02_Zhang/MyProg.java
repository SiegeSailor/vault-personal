/* File: MyProg.java */

/**
 * Edit History:
 * 05/27/2022 Jin Yu, Zhang Initial version.
 */

/**
 * A program to manipulate a few jars.
 * 
 * @author Jin Yu, Zhang
 */
public class MyProg {
    /**
     * A serial of actions to demonstrate how to manipulate jars.
     * 
     * @param args Implemented but not used.
     */
    public static void main(String[] args) {
        System.out.println("Let's prepare 2 jars.");
        JarType jarFirst = new JarType();
        JarType jarSecond = new JarType();
        System.out.println("");

        System.out.println("Adding...");
        jarFirst.add(10);
        jarSecond.add(20);

        System.out.println("The first jar contains " + jarFirst.view() + " units.");
        System.out.println("The second jar contains " + jarSecond.view() + " units.");

        System.out.println("\nLet's empty the first jar.");
        jarFirst.empty();
        System.out.println("Now the first jar contains " +
                jarFirst.view() + " unit");

    }
}