package com.qc.skillscy.commons.misc;

import com.qc.skillscy.commons.codes.ApplicationCodes;
import com.qc.skillscy.commons.codes.HTTPCodes;
import com.qc.skillscy.commons.exceptions.WebExceptionType;
import com.qc.skillscy.commons.exceptions.WebServiceException;
import com.qc.skillscy.commons.loggers.CommonLogger;

public class Validator {

    private Validator() {
    }

    private final static String QC_EMPLOYEE_ID_REGEX = "Q1[A-Z]{2}[1-9][0-9]{3}";
    private final static String QC_INTERN_ID_REGEX = "Q2[A-Z]{2}[1-9][0-9]{3}";
    private final static String QC_TRAINEE_ID_REGEX = "Q3[A-Z]{2}[1-9][0-9]{3}";
    private final static String QC_ID_REGEX = "Q[1-3][A-Z]{2}[1-9][0-9]{3}";

    public static String ignoreNullByString(String any) {
        // This methods will have string argument and returns the same if not null else will return an empty string
        if (any == null) {
            CommonLogger.warning(Validator.class, "[ignoreNullByString()] Given input is null. Returning Empty string...");
            return "";
        }
        CommonLogger.info(Validator.class, "[ignoreNullByString()] Given input is [".concat(any).concat("] is not null. Returning the same string..."));
        return any;
    }

    public static void notNull(Object value) throws WebServiceException {
        if (value == null)
            throw new WebServiceException(ApplicationCodes.VALIDATION_NULL_FOUND, HTTPCodes.BAD_REQUEST, WebExceptionType.VALIDATION);
    }

    public static void checkQcEmployeeID(String employeeID) throws WebServiceException {
        Validator.notNull(employeeID);
        if (!employeeID.matches(Validator.QC_EMPLOYEE_ID_REGEX))
            throw new WebServiceException(ApplicationCodes.INVALID_QC_EMPLOYEE_ID, HTTPCodes.PRECONDITION_FAILED, WebExceptionType.VALIDATION);
        CommonLogger.info(Validator.class, "Given Employee ID [".concat(employeeID).concat("] is valid!"));
    }

    public static void checkQcInternID(String internID) throws WebServiceException {
        Validator.notNull(internID);
        if (!internID.matches(Validator.QC_INTERN_ID_REGEX))
            throw new WebServiceException(ApplicationCodes.INVALID_QC_INTERN_ID, HTTPCodes.PRECONDITION_FAILED, WebExceptionType.VALIDATION);
        CommonLogger.info(Validator.class, "Given Intern ID [".concat(internID).concat("] is valid!"));
    }

    public static void checkQcTraineeID(String traineeID) throws WebServiceException {
        Validator.notNull(traineeID);
        if (!traineeID.matches(Validator.QC_TRAINEE_ID_REGEX))
            throw new WebServiceException(ApplicationCodes.INVALID_QC_TRAINEE_ID, HTTPCodes.PRECONDITION_FAILED, WebExceptionType.VALIDATION);
        CommonLogger.info(Validator.class, "Given Trainee ID [".concat(traineeID).concat("] is valid!"));
    }

    public static void checkQcID(String companyID) throws WebServiceException {
        Validator.notNull(companyID);
        if (!companyID.matches(Validator.QC_ID_REGEX))
            throw new WebServiceException(ApplicationCodes.INVALID_QC_ID, HTTPCodes.PRECONDITION_FAILED, WebExceptionType.VALIDATION);
        CommonLogger.info(Validator.class, "Given Company ID [".concat(companyID).concat("] is valid!"));
    }

}
