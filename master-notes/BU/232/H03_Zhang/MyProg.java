/* File: MyProg.java */

/**
 * Edit History:
 * 05/27/2022 Jin Yu, Zhang Initial version.
 * 06/02/2022 Jin Yu, Zhang Added jarThird with new constructor. Add test code for add and sub for jarFirst. Add assert.
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
        System.out.println("Let's prepare 3 jars.");
        JarType jarFirst = new JarType();
        JarType jarSecond = new JarType();
        JarType jarThird = new JarType(25);

        System.out.println("Adding...");
        /**
         * Removed the next line due to `MyProg.java:27: error: numberOfUnits has
         * private access in JarType`
         */
        // jarFirst.numberOfUnits = 100000;
        jarFirst.add(10);
        jarSecond.add(20);
        jarThird.add(50);

        System.out.println("The first jar contains " + jarFirst.view() + " units.");
        System.out.println("The second jar contains " + jarSecond.view() + " units.");
        System.out.println("The third jar contains " + jarThird.view() + " units.");

        System.out.println("\nLet's empty the first jar.");
        jarFirst.empty();
        System.out.println("Now the first jar contains " +
                jarFirst.view() + " unit.");

        /** java --enableassertions <filename>.java */
        assert jarFirst.view() == 0 : "jarFirst view() doesn't produce correct number";
        System.out.println("Program runs correctly so far.");

        System.out.println("Before add jarFirst contains " + jarFirst.view() + " units.");
        jarFirst.add(-5000);
        System.out.println("After add jarFirst contains " + jarFirst.view() + " units.");

        jarFirst.sub(10);
        jarFirst.sub(-5);
        jarFirst.sub(12345);
        System.out.println("jarFirst now contains " + jarFirst.view() + " units.");
    }
}