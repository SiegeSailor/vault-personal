package source.exception;

/* File: ValidationException.java */

/**
 * Edit History:
 * 06/18/2022 Jin Yu, Zhang Initial version.
 * 06/20/2022 Jin Yu, Zhang Add exceptions and documentations.
 */

/**
 * The collection of customized exceptions.
 * 
 * @author Jin Yu, Zhang
 */
public class ValidationException {
    /** Default constructor. */
    public ValidationException() {
    }

    /** Exception for non-existing prerequisite. */
    public static class NotExist extends Exception {
        /**
         * Exception with customized message that indicates where the validation has
         * failed.
         * 
         * @param message {@code String} Desired message.
         */
        public NotExist(String message) {
            super(message);
        }
    }

    /** Exception for out-of-scope index. */
    public static class OutScope extends Exception {
        /**
         * Exception with customized message that indicates where the validation has
         * failed.
         * 
         * @param message {@code String} Desired message.
         */
        public OutScope(String message) {
            super(message);
        }
    }

    /** Exception for operations at a full array. */
    public static class FullArray extends Exception {
        /**
         * Exception with customized message that indicates where the validation has
         * failed.
         * 
         * @param message {@code String} Desired message.
         */
        public FullArray(String message) {
            super(message);
        }
    }

    /** Exception for operations that require array slots. */
    public static class NotEnough extends Exception {
        /**
         * Exception with customized message that indicates where the validation has
         * failed.
         * 
         * @param message {@code String} Desired message.
         */
        public NotEnough(String message) {
            super(message);
        }
    }

    /** Exception for operations that require array slots. */
    public static class NoMatched extends Exception {
        /**
         * Exception with customized message that indicates where the validation has
         * failed.
         * 
         * @param message {@code String} Desired message.
         */
        public NoMatched(String message) {
            super(message);
        }
    }

    /** Exception for operations under illegal status. */
    public static class IllegalStatus extends Exception {
        /**
         * Exception with customized message that indicates where the validation has
         * failed.
         * 
         * @param message {@code String} Desired message.
         */
        public IllegalStatus(String message) {
            super(message);
        }
    }

    /** Exception for invalid string. */
    public static class InvalidString extends Exception {
        /**
         * Exception with customized message that indicates where the validation has
         * failed.
         * 
         * @param message {@code String} Desired message.
         */
        public InvalidString(String message) {
            super(message);
        }
    }

    /** Exception for null object. */
    public static class NullObject extends Exception {
        /**
         * Exception with customized message that indicates where the validation has
         * failed.
         * 
         * @param message {@code String} Desired message.
         */
        public NullObject(String message) {
            super(message);
        }
    }

    /** Exception for negative double. */
    public static class NegativeDouble extends Exception {
        /**
         * Exception with customized message that indicates where the validation has
         * failed.
         * 
         * @param message {@code String} Desired message.
         */
        public NegativeDouble(String message) {
            super(message);
        }
    }

    /** Exception for negative integer. */
    public static class NegativeInteger extends Exception {
        /**
         * Exception with customized message that indicates where the validation has
         * failed.
         * 
         * @param message {@code String} Desired message.
         */
        public NegativeInteger(String message) {
            super(message);
        }
    }

    /** Exception for invalid date. */
    public static class InvalidDate extends Exception {
        /**
         * Exception with customized message that indicates where the validation has
         * failed.
         * 
         * @param message {@code String} Desired message.
         */
        public InvalidDate(String message) {
            super(message);
        }
    }
}
