package misc;

import codes.ApplicationCodes;
import codes.HTTPCodes;
import exceptions.WebExceptionType;
import exceptions.WebServiceException;

public class Validator {

    private Validator() {}

    public static void notNull(Object value) throws WebServiceException {
        if (value == null)
            throw new WebServiceException(ApplicationCodes.VALIDATION_NULL_FOUND, HTTPCodes.BAD_REQUEST, WebExceptionType.VALIDATION);
    }

}
