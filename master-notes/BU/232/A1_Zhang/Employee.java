/* File: Employee.java */

/**
 * Edit History:
 * 06/02/2022 Jin Yu, Zhang Initial version.
 */

/**
 * Class for Employee.
 * 
 * @author Jin Yu, Zhang
 */
public class Employee {
    private String nameFirst = "";

    private String nameLast = "";

    private double salaryMonth = 0.0;

    /** Default Constructor */
    public Employee() {
    };

    /**
     * Constructor with parameters.
     * 
     * @param inputNameFirst   {@code String} Pre-filled first name.
     * @param inputNameLast    {@code String} Pre-filled last name.
     * @param inputSalaryMonth {@code double} Pre-filled monthly salary.
     */
    public Employee(String inputNameFirst, String inputNameLast, double inputSalaryMonth) {
        setNameFirst(inputNameFirst);
        setNameLast(inputNameLast);
        setSalaryMonth(inputSalaryMonth);
    };

    /**
     * Getter for private data member nameFirst.
     * 
     * @return {@code String} nameFirst
     */
    public String getNameFirst() {
        return nameFirst;
    };

    /**
     * Getter for private data member nameLast.
     * 
     * @return {@code String} nameLast
     */
    public String getNameLast() {
        return nameLast;
    };

    /**
     * Getter for private data member salaryMonth.
     * 
     * @return {@code double} salaryMonth
     */
    public double getSalaryMonth() {
        return salaryMonth;
    };

    /**
     * Get salary with expected number of month.
     * 
     * @param numberOfMonth {@code int} The expected number of month.
     * @return Salary after specified number of months.
     */
    public double getSalary(int numberOfMonth) {
        return numberOfMonth * salaryMonth;
    }

    /**
     * Print all information in the class {@code Employee}.
     */
    public void printInformation() {
        System.out.println(
                "First name: " + nameFirst + " / Last name: " + nameLast + " / Yearly salary: " + getSalary(12));
    }

    /**
     * Setter for private data member nameFirst.
     * 
     * @param input {@code String} First name.
     */
    public void setNameFirst(String input) {
        if (input.length() == 0) {
            System.out.println("New first name can not be empty. The value remains the same.");
            return;
        }
        nameFirst = input;
    };

    /**
     * Setter for private data member nameLast.
     * 
     * @param input {@code String} Last name.
     */
    public void setNameLast(String input) {
        if (input.length() == 0) {
            System.out.println("New last name can not be empty. The value remains the same.");
            return;
        }
        nameLast = input;
    };

    /**
     * Setter for private data member salaryMonth.
     * 
     * @param input {@code double} Monthly salary.
     */
    public void setSalaryMonth(double input) {
        if (input < 0.0) {
            System.out.println("New monthly salary can not be negative. The value remains the same.");
            return;
        }
        salaryMonth = input;
    };

    /**
     * Give monthly salary a raise. Must be higher than 1.0.
     * 
     * @param input {@code double} Raise rate.
     */
    public void increaseSalaryMonth(double input) {
        if (input < 1.0) {
            System.out.println("Raise lower than 1.0. The value remains the same.");
            return;
        }
        salaryMonth *= input;
    }
}
