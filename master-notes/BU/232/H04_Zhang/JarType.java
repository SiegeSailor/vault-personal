/* File: JarType.java */

/**
 * Edit History:
 * 05/27/2022 Jin Yu, Zhang Initial version.
 * 05/27/2022 Jin Yu, Zhang Add documents.
 * 06/02/2022 Jin YU, Zhang Add sub, constructor, and helping method.
 *                          Use BASE_VALUE.
 * 06/07/2022 Jin Yu, Zhang Add static members.
 * 06/07/2022 Jin Yu, Zhang Add constructor.
 * 06/07/2022 Jin Yu, Zhang Add add overloading.
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
        numberOfUnits = BASE_VALUE;
        System.out.println("Rinsing jar.");
        countJar++;
    }

    /**
     * Create a jar with input.
     * 
     * @param input {@code int} The number of units in int you want to pre-fill the
     *              jar.
     */
    public JarType(int input) {
        numberOfUnits = verify(input);
        System.out.println("Pre-filled jar with int input.");
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
     */
    private int verify(int input) {
        if (input < BASE_VALUE) {
            System.out.println("The input can't be negative. The value has been changed to 0.");
            return BASE_VALUE;
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
        numberOfUnits += verify(input);
    }

    /**
     * Subtract units in the jar.
     * 
     * @param input {@code int} The number of units you want to subtract.
     */
    public void sub(int input) {
        int inputVerified = verify(input);
        if (inputVerified > numberOfUnits) {
            System.out.println(
                    "The input is larger than the number of unit held in the jar. The action has been cancelled.");
        } else {
            numberOfUnits -= verify(input);
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
     * @param that {@code JarType} Another jar.
     * @return {@code boolean} True if the two are equal.
     */
    public boolean equal(JarType that) {
        return this.view() == that.view();
    }

    /**
     * See the total number of created jar throughout the whole program.
     * 
     * @return {@code int} The number of created jar throughout the whole program.
     */
    public static int getCountJar() {
        return countJar;
    }
}
