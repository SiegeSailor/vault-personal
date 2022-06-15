import javax.swing.JOptionPane;
/* File: MyProg.java */

/**
 * Edit History:
 * 05/27/2022 Jin Yu, Zhang Initial version.
 * 06/02/2022 Jin Yu, Zhang Added jarThird with new constructor.
 *                          Add test code for add and sub for jarFirst.
 *                          Add assert.
 * 06/07/2022 Jin Yu, Zhang Add demonstrating methods.
 * 06/09/2022 Jin Yu, Zhang Wrap demonstrateJarType.
 *                          Add demonstratePanel.
 *                          Add default constructor to avoid warning message from javadoc.
 * 06/14/2022 Jin Yu, Zhang Handle MyException from JarType.sub.
 *                          Add code for enhanced equal.
 */

/**
 * A program to manipulate a few jars.
 * 
 * @author Jin Yu, Zhang
 */
public class MyProg {
    /** Default constructor. */
    public MyProg() {
    }

    /** Actions for demonstrating polymorphism showDetail. */
    public static void demonstratePolymorphism() {
        System.out.println("=== Start of Polymorphism ===");
        JarType jarType = new JarType();
        LabeledJar labeledJar = new LabeledJar();
        jarType.showDetail();
        labeledJar.showDetail();
        System.out.println("=== End of Polymorphism ===\n");
    }

    /** Actions for demonstrating method overriding. */
    public static void demonstrateOverride() {
        System.out.println("=== Start of Override ===");
        JarType jarType = new JarType();
        LabeledJar labeledJar = new LabeledJar();
        System.out.println(jarType);
        System.out.println(labeledJar);
        System.out.println("=== End of Override ===\n");
    }

    /** Actions for demonstrating LabeledJar. */
    public static void demonstrateLabeledJar() {
        System.out.println("=== Start of LabeledJar ===");
        LabeledJar jar = new LabeledJar();
        jar.add(30);
        jar.printLabel();
        System.out.println(jar.view());

        LabeledJar jarLabeled = new LabeledJar("Pills");
        jarLabeled.printLabel();
        jarLabeled.setLabel("Nails");
        jarLabeled.printLabel();

        LabeledJar jarPreFilled = new LabeledJar(50);
        System.out.println(jarPreFilled.view());
        LabeledJar jarLabeledPreFilled = new LabeledJar(20, "Pickles");
        System.out.println(jarLabeledPreFilled.view());
        jarLabeledPreFilled.printLabel();
        System.out.println("=== End of LabeledJar ===\n");
    }

    /** Actions for demonstrating JOptionPane. */
    public static void demonstratePanel() {
        System.out.println("=== Start of Panel ===");
        String inputPanel = JOptionPane
                .showInputDialog("Please enter the number of unit you want to add:");
        boolean isFormatCorrect = true;
        if (inputPanel == null) {
            isFormatCorrect = false;
            System.out.println("You pressed cancel. The value has been set to 0.");
        } else if (inputPanel.length() == 0) {
            isFormatCorrect = false;
            System.out.println("You didn't enter any value. The value has been set to 0.");
        }

        int numberOfAddingUnit = 0;
        try {
            numberOfAddingUnit = isFormatCorrect ? Integer.parseInt(inputPanel) : 0;
        } catch (NumberFormatException event) {
            System.out.println("Input wrong format. The value has been set to 0.");
            numberOfAddingUnit = 0;
        }

        JarType jar = new JarType(numberOfAddingUnit);
        JOptionPane.showMessageDialog(null, "The number of unit in the jar is: " + jar.view());
        System.out.println("=== End of Panel ===\n");
    }

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

        LabeledJar labeledJar = new LabeledJar();
        if (!jarThis.equal(labeledJar)) {
            System.out.println("JarType's equal is strict now.");
        }
        System.out.println("=== End of Equal ===\n");
    }

    /**
     * Actions for demonstrating JarType methods.
     */
    public static void demonstrateJarType() {
        System.out.println("=== Start of JarType ===");
        System.out.println("Let's prepare 6 jars.");
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
        jarFourth.add();
        jarFifth.add();
        jarSixth.add();

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

        try {
            jarFirst.sub(10);
            jarFirst.sub(-5);
            jarFirst.sub(12345);
        } catch (MyException event) {
            System.out.println(event.getMessage());
        }
        System.out.println("jarFirst now contains " + jarFirst.view() + " units.");
        System.out.println("=== End of JarType ===\n");
    }

    /**
     * A serial of actions to demonstrate how to manipulate jars.
     * 
     * @param args Implemented but not used.
     */
    public static void main(String[] args) {
        demonstrateJarType();

        demonstrateEqual();

        demonstrateArrayRandom();

        demonstratePanel();

        demonstrateLabeledJar();

        demonstrateOverride();

        demonstratePolymorphism();

        System.exit(0);
    }
}