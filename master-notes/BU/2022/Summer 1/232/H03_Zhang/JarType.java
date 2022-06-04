/* File: JarType.java */

/**
 * Edit History:
 * 05/27/2022 Jin Yu, Zhang Initial version.
 * 05/27/2022 Jin Yu, Zhang Add documents.
 * 06/02/2022 Jin YU, Zhang Add sub and constructor. Add verify for all methods change numberOfUnits. Use BASE_VALUE.
 */

/**
 * Class for JarType.
 * 
 * @author Jin Yu, Zhang
 */
public class JarType {
    /** Lowest value for numberOfUnits in the jar. */
    static final int BASE_VALUE = 0;

    /** Total number of units inside the jar. */
    private int numberOfUnits = BASE_VALUE;

    /**
     * Default constructor with no units inside the jar.
     */
    public JarType() {
        numberOfUnits = BASE_VALUE;
        System.out.println("Rinsing jar.");
    }

    /**
     * Create a jar with input.
     * 
     * @param input {@code int} The number of units you want to pre-fill the jar.
     */
    public JarType(int input) {
        numberOfUnits = verify(input);
        System.out.println("Pre-filled jar.");
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
        numberOfUnits -= verify(input);
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
}
