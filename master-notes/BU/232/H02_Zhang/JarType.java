/* File: JarType.java */

/**
 * Edit History:
 * 05/27/2022 Jin Yu, Zhang Initial version.
 * 05/27/2022 Jin Yu, Zhang Add documents.
 */

/**
 * Class for JarType.
 * 
 * @author Jin Yu, Zhang
 */
public class JarType {
    /** Total number of units inside the jar. */
    private int numberOfUnits = 0;

    /**
     * Default constructor with no units inside the jar.
     */
    public JarType() {
        numberOfUnits = 0;
        System.out.println("Rinsing jar.");
    }

    /**
     * Add unit into the jar.
     * 
     * @param input {@code int} The number of units you want to add into the jar.
     */
    public void add(int input) {
        numberOfUnits += input;
    }

    /**
     * Empty all unit inside the jar.
     */
    public void empty() {
        numberOfUnits = 0;
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
