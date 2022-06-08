/* File: MyProg.java */

/**
 * Edit History:
 * 05/27/2022 Jin Yu, Zhang Initial version.
 * 06/02/2022 Jin Yu, Zhang Added jarThird with new constructor.
 *                          Add test code for add and sub for jarFirst.
 *                          Add assert.
 * 06/07/2022 Jin Yu, Zhang Add demonstrating methods.
 */

/**
 * A program to manipulate a few jars.
 * 
 * @author Jin Yu, Zhang
 */
public class MyProg {
    /**
     * Actions for demonstrating adding JarType to an array and give random input.
     */
    public static void demonstrateArrayRandom() {
        System.out.println("=== Start of Array Random ===");
        JarType[] arrayOfJarType = new JarType[10];
        for (int i = 0; i < arrayOfJarType.length; i++) {
            JarType input = new JarType();
            input.add();
            arrayOfJarType[i] = input;
        }

        for (int i = 0; i < arrayOfJarType.length; i++)
            System.out.println("jar " + i + " has " + arrayOfJarType[i].view() + " units");
        System.out.println("=== End of Array Random ===\n");
    };

    /**
     * Actions for demonstrating equality for JarType.
     */
    public static void demonstrateEqual() {
        System.out.println("=== Start of Equal ===");
        JarType jarThis = new JarType(30);
        JarType jarThat = new JarType(30);
        if (jarThis.equal(jarThat)) {
            System.out.println("jarThis and jarThat are equal.");
        }
        if (jarThis != jarThat) {
            System.out.println("jarThis and jarThat are NOT at the same memory location.");
        }
        if (jarThis.equals(jarThis)) {
            System.out.println("jarThis and jarThis are equal");
        }
        System.out.println("=== End of Equal ===\n");
    }

    /**
     * A serial of actions to demonstrate how to manipulate jars.
     * 
     * @param args Implemented but not used.
     */
    public static void main(String[] args) {
        System.out.println("Let's prepare 3 jars.");
        System.out.println("Number of jars so far: " + JarType.getCountJar());
        JarType jarFirst = new JarType();
        JarType jarSecond = new JarType();
        JarType jarThird = new JarType(25);
        JarType jarFourth = new JarType('p');
        JarType jarFifth = new JarType('q');
        JarType jarSixth = new JarType('a');

        System.out.println("Number of jars created: " + JarType.getCountJar());
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
        jarFirst.add(100);

        jarFirst.sub(10);
        jarFirst.sub(-5);
        jarFirst.sub(12345);
        System.out.println("jarFirst now contains " + jarFirst.view() + " units.");

        demonstrateEqual();
        demonstrateArrayRandom();
    }
}