/* File: MyException.java */

/**
 * Edit History:
 * 06/14/2022 Jin Yu, Zhang Initial version.
 */

/**
 * An exception.
 * 
 * @author Jin Yu, Zhang
 */
public class MyException extends Exception {
    /** Default constructor. */
    public MyException() {
        super("Exception without message.");
    }

    /**
     * Exception with customized message.
     * 
     * @param message {@code String} Desired message.
     */
    public MyException(String message) {
        super(message);
    }
}
