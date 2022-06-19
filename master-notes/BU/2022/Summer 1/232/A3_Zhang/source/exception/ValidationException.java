package source.exception;
/* File: ValidationException.java */

/**
 * Edit History:
 * 06/18/2022 Jin Yu, Zhang Initial version.
 */

/**
 * The collection of customized exceptions.
 * 
 * @author Jin Yu, Zhang
 */
public class ValidationException {
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
}
