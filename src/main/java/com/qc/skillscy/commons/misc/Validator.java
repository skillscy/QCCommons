package com.qc.skillscy.commons.misc;

import com.qc.skillscy.commons.codes.ApplicationCodes;
import com.qc.skillscy.commons.codes.HTTPCodes;
import com.qc.skillscy.commons.exceptions.WebExceptionType;
import com.qc.skillscy.commons.exceptions.WebServiceException;

public class Validator {

    private Validator() {
    }

    private final static String QC_USERNAME_REGEX = "[a-z]{4}[a-z0-9]{2}";

    public static String ignoreNullByString(String any) {
        // This methods will have string argument and returns the same if not null else will return an empty string
        if (any == null)
            return "";
        return any;
    }

    public static void notNull(Object value) throws WebServiceException {
        if (value == null)
            throw new WebServiceException(ApplicationCodes.VALIDATION_NULL_FOUND, HTTPCodes.BAD_REQUEST, WebExceptionType.VALIDATION);
    }

    public static void checkQcUsername(String username) throws WebServiceException {
        Validator.notNull(username);
        if (!username.matches(Validator.QC_USERNAME_REGEX))
            throw new WebServiceException(ApplicationCodes.INVALID_QC_USERNAME, HTTPCodes.PRECONDITION_FAILED, WebExceptionType.VALIDATION);
    }

}
