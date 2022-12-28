import java.util.Scanner;

/* File: EmployeeTest.java */

/**
 * Edit History:
 * 06/02/2022 Jin Yu, Zhang Initial version.
 * 06/04/2022 Jin Yu, Zhang Put Employee into the driver.
 */

/**
 * The #1 programming assignment for BU METCS232A1 Programming with java.
 * Due by Thursday, June 9, 2022.
 * 
 * @author Jin Yu, Zhang
 */
public class EmployeeTest {
    /**
     * A helper to create an employee.
     * 
     * @param keyboard {@code Scanner} Input scanner.
     * @param identity {@code String} A name used to call an employee before it is
     *                 created.
     * @return A Employee object with fully filled information.
     */
    public static Employee createEmployee(Scanner keyboard, String identity) {
        System.out.println("Please enter first name of employee " + identity + ":");
        String nameFirst = keyboard.nextLine();
        System.out.println("Please enter last name of employee " + identity + ":");
        String nameLast = keyboard.nextLine();
        System.out.println("Please enter monthly salary of employee " + identity + ":");
        double salaryMonth;
        try {
            salaryMonth = Double.parseDouble(keyboard.nextLine());
        } catch (NumberFormatException event) {
            System.out.println("Input wrong format for monthly salary. The value has been set to 0.0.");
            salaryMonth = 0.0;
        }
        Employee employee = new Employee(nameFirst, nameLast, salaryMonth);

        return employee;
    }

    /**
     * The driver to read and print information for two employees.
     * 
     * @param args {@code String[]} Implemented but not used.
     */
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Employee[] arrayOfEmployee = { createEmployee(keyboard, "#1"), createEmployee(keyboard, "#2") };
        for (Employee employee : arrayOfEmployee) {
            employee.printInformation();
        }
        System.out.println("\nGive a 10% raise for each employee.\n");
        for (Employee employee : arrayOfEmployee) {
            employee.increaseSalaryMonth(1.1);
            employee.printInformation();
        }

        keyboard.close();
    }
}
