/* File: LabeledJar.java */

/**
 * Edit History:
 * 06/09/2022 Jin Yu, Zhang Initial version.
 */

/**
 * Class for LabeledJar.
 * 
 * @author Jin Yu, Zhang
 */
public class LabeledJar extends JarType {
    private String label = "";

    /** Default constructor with no units and a label inside the jar. */
    public LabeledJar() {
        this("Generic");
    }

    /**
     * Create a jar with no unit and a input label.
     * 
     * @param input {@code String} New label. The length must be larger than 0.
     */
    public LabeledJar(String input) {
        super();
        setLabel(input);
    }

    /**
     * Create a jar with units and a default label.
     * 
     * @param input {@code int} Pre-filled number of unit.
     */
    public LabeledJar(int input) {
        super(input);
        setLabel("Generic");
    }

    /**
     * Create a jar with units and a input label.
     * 
     * @param inputUnit  {@code int} Pre-filled number of unit.
     * @param inputLabel {@code String} New label. The length must be larger than 0.
     */
    public LabeledJar(int inputUnit, String inputLabel) {
        super(inputUnit);
        setLabel(inputLabel);
    }

    /**
     * Setter for label.
     * 
     * @param input {@code String} New label. The length must be larger than 0.
     */
    public void setLabel(String input) {
        if (input.length() == 0) {
            System.out.println("The input is empty. The label remains the same.");
        } else {
            label = input;
        }
    }

    /** Print data member label. */
    public void printLabel() {
        System.out.println("Label: " + label);
    }

    /** Override method for toString */
    public String toString() {
        return "Jar contains " + view() + " units and has a label " + label + ".";
    }

    /** Print value from toString. */
    public void showDetail() {
        System.out.println(this);
    }
}
