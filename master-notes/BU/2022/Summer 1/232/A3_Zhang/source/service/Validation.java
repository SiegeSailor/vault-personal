package source.service;

import source.exception.*;

public class Validation {
    public static void objectMeaningful(String field, Object input) throws ValidationException.NullObject {
        if (input == null)
            throw new ValidationException.NullObject(field + " can't be null.");
    }

    public static void stringMeaningful(String field, String input)
            throws ValidationException.InvalidString, ValidationException.NullObject {
        objectMeaningful(field, input);
        if (input.length() == 0)
            throw new ValidationException.InvalidString(field + " shouldn't be an empty string.");
    };

    public static void doubleNonNegative(String field, double input) throws ValidationException.NegativeDouble {
        if (input < 0.0)
            throw new ValidationException.NegativeDouble(field + " should be larger than or equal to 0.0.");
    }

    public static void integerNonNegative(String field, int input) throws ValidationException.NegativeInteger {
        if (input < 0)
            throw new ValidationException.NegativeInteger(field + " should be larger than or equal to 0.");
    }
}
