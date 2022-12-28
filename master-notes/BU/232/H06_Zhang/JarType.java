/* File: JarType.java */

/**
 * Edit History:
 * 05/27/2022 Jin Yu, Zhang Initial version.
 *                          Add documents.
 * 06/02/2022 Jin YU, Zhang Add sub, constructor, and helping method.
 *                          Use BASE_VALUE.
 * 06/07/2022 Jin Yu, Zhang Add static members.
 *                          Add constructor.
 *                          Add add overloading.
 * 06/09/2022 Jin Yu, Zhang Call other constructor within default constructor.
 * 06/14/2022 Jin Yu, Zhang Enhance equal method with class guarding and null validation.
 *                          Throw MyException in add and sub.
 * 06/16/2022 Jin Yu, Zhang Modify equal.
 */

/**
 * Class for JarType.
 * 
 * @author Jin Yu, Zhang
 */
public class JarType {
    /** Lowest value for numberOfUnits in the jar. */
    static final int BASE_VALUE = 0;

    /** Total number of created jar throughout the whole program */
    private static int countJar = 0;

    /** Total number of units inside the jar. */
    private int numberOfUnits = BASE_VALUE;

    /** Default constructor with no units inside the jar. */
    public JarType() {
        this(0);
    }

    /**
     * Create a jar with input.
     * 
     * @param input {@code int} The number of units in int you want to pre-fill the
     *              jar.
     */
    public JarType(int input) {
        try {
            numberOfUnits = verifyNumberOfUnit(input);
            System.out.println("Pre-filled jar with int " + input + " input.");
        } catch (MyException event) {
            System.out.println(event.getMessage() + " The unit has been set to 0.");
        }
        countJar++;
    }

    /**
     * Create a jar with "p" or "q".
     * 
     * @param input {@code char} Only "p" and "q" are valid.
     */
    public JarType(char input) {
        switch (input) {
            case 'p':
                numberOfUnits = 16;
                System.out.println("Pre-filled jar with char input, which is 16.");
                break;
            case 'q':
                numberOfUnits = 32;
                System.out.println("Pre-filled jar with char input, which is 32.");
                break;
            default:
                numberOfUnits = BASE_VALUE;
                System.out.println("Invalid character input. Set value to 0.");
                break;
        }
        countJar++;
    }

    /**
     * Create a jar with a character. For example, "a" refers to 1 and "A" refers to
     * "27" (if it is case sensitive).
     * 
     * @param input           {@code char} The number of units in character you want
     *                        to pre-fill the jar.
     * @param isCaseSensitive {@code boolean} True to set case sensitive mode. In
     *                        this case, "A" will also refer to "1" as "a" does.
     */
    public JarType(char input, boolean isCaseSensitive) {
        int inputTransferred = (int) input;
        int inputResult = BASE_VALUE;
        if (inputTransferred >= 65 && inputTransferred <= 90) {
            inputResult = inputTransferred - (isCaseSensitive ? 38 : 64);
            System.out.println("The input is " + inputResult + " from " + input + ".");
        } else if (inputTransferred >= 97 && inputTransferred <= 122) {
            inputResult = inputTransferred - 96;
            System.out.println("The input is " + inputResult + " from " + input + ".");
        } else {
            System.out.println("The input character is out of range a-z and A-Z. The value has been set to 0.");
        }
        numberOfUnits = inputResult;
        System.out.println("Pre-filled jar with char input.");
        countJar++;
    }

    /**
     * Verify if the input number is negative. If it is, change it to 0.
     * 
     * @param input {@code int} Number to verify.
     * @return BASE_VALUE (if it is negative) or original input value.
     * @throws MyException If the input is negative.
     */
    private int verifyNumberOfUnit(int input) throws MyException {
        if (input < BASE_VALUE) {
            throw new MyException("The input can't be negative.");
        }
        return input;
    }

    /** Add random unit between 1 to 100 into the jar. */
    public void add() {
        numberOfUnits += (int) (Math.random() * 100);
    }

    /**
     * Add unit into the jar.
     * 
     * @param input {@code int} The number of units you want to add into the jar.
     */
    public void add(int input) {
        try {
            numberOfUnits += verifyNumberOfUnit(input);
        } catch (MyException event) {
            System.out.println(event.getMessage() + " There is no unit added.");
        }
    }

    /**
     * Subtract units in the jar.
     * 
     * @param input {@code int} The number of units you want to subtract.
     * @throws MyException If the input is negative.
     */
    public void sub(int input) throws MyException {
        int inputVerified = verifyNumberOfUnit(input);
        if (inputVerified > numberOfUnits) {
            throw new MyException("The input is larger than the number of unit held in the jar.");
        } else {
            numberOfUnits -= inputVerified;
        }
    }

    /**
     * Empty all unit inside the jar.
     */
    public void empty() {
        numberOfUnits = BASE_VALUE;
    }

    /**
     * View the current number of units inside the jar.
     * 
     * @return {@code int} The number of units inside the jar.
     */
    public int view() {
        return numberOfUnits;
    }

    /**
     * See if two jars are equal.
     * 
     * @param that {@code Object} Another jar or any object.
     * @return {@code boolean} True if the two jars are equal.
     */
    public boolean equal(Object that) {
        if (that == null)
            return false;
        if (!(that instanceof JarType))
            return false;
        JarType thatJarType = (JarType) that;
        return this.view() == thatJarType.view();
    }

    /**
     * See the total number of created jar throughout the whole program.
     * 
     * @return {@code int} The number of created jar throughout the whole program.
     */
    public static int getCountJar() {
        return countJar;
    }

    /** Override method for toString */
    public String toString() {
        return "Jar contains " + view() + " units.";
    }

    /** Print value from toString. */
    public void showDetail() {
        System.out.println(this);
    }
}
