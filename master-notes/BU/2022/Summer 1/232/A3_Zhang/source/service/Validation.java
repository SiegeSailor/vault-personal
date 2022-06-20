package source.service;

import source.exception.*;

/* File: Validation.java */

/**
 * Edit History:
 * 06/19/2022 Jin Yu, Zhang Initial version.
 */

/**
 * The collection of validation helpers.
 * 
 * @author Jin Yu, Zhang
 */
public class Validation {
    /**
     * Only non-null object is allowed.
     * 
     * @param field {@code String} Hint for exception.
     * @param input {@code Object} Object to validate.
     * @throws ValidationException.NullObject
     */
    public static void objectMeaningful(String field, Object input) throws ValidationException.NullObject {
        if (input == null)
            throw new ValidationException.NullObject(field + " can't be null.");
    }

    /**
     * Only strings that is not null has content is allowed.
     * 
     * @param field {@code String} Hint for exception.
     * @param input {@code String} String to validate
     * @throws ValidationException.InvalidString
     * @throws ValidationException.NullObject
     */
    public static void stringMeaningful(String field, String input)
            throws ValidationException.InvalidString, ValidationException.NullObject {
        objectMeaningful(field, input);
        if (input.length() == 0)
            throw new ValidationException.InvalidString(field + " shouldn't be an empty string.");
    };

    /**
     * Only positive double is allowed.
     * 
     * @param field {@code String} Hint for exception.
     * @param input {@code double} Double ot validate.
     * @throws ValidationException.NegativeDouble
     */
    public static void doubleNonNegative(String field, double input) throws ValidationException.NegativeDouble {
        if (input < 0.0)
            throw new ValidationException.NegativeDouble(field + " should be larger than or equal to 0.0.");
    }

    /**
     * Only positive integer is allowed.
     * 
     * @param field {@code String} Hint for exception.
     * @param input {@code int} Integer to validate.
     * @throws ValidationException.NegativeInteger
     */
    public static void integerNonNegative(String field, int input) throws ValidationException.NegativeInteger {
        if (input < 0)
            throw new ValidationException.NegativeInteger(field + " should be larger than or equal to 0.");
    }
}
