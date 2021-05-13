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
    private final static String QC_ARTICLE_ID_REGEX = "[A-Z]{2}[0-9]{4}";
    private final static String QC_CLIENT_ID_REGEX = "C[0-9]{4}";
    private final static String QC_COMMENTS_ID_REGEX = "[0-9]{8}";
    private final static String QC_PROJECT_ID_REGEX = "[A-Z]{6}";
    private final static String QC_FILES_ID_REGEX = "F".concat(Validator.QC_ID_REGEX);

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

    public static boolean isNull(Object value) {
        return value == null;
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

    public static void checkQcArticleID(String articleID) throws WebServiceException {
        Validator.notNull(articleID);
        if (!articleID.matches(Validator.QC_ARTICLE_ID_REGEX))
            throw new WebServiceException(ApplicationCodes.INVALID_QC_ARTICLE_ID, HTTPCodes.PRECONDITION_FAILED, WebExceptionType.VALIDATION);
        CommonLogger.info(Validator.class, "Given Article ID [".concat(articleID).concat("] is valid!"));
    }

    public static void checkQcClientID(String clientID) throws WebServiceException {
        Validator.notNull(clientID);
        if (!clientID.matches(Validator.QC_CLIENT_ID_REGEX))
            throw new WebServiceException(ApplicationCodes.INVALID_QC_CLIENT_ID, HTTPCodes.PRECONDITION_FAILED, WebExceptionType.VALIDATION);
        CommonLogger.info(Validator.class, "Given Client ID [".concat(clientID).concat("] is valid!"));
    }

    public static void checkQcCommentsID(String commentsID) throws WebServiceException {
        Validator.notNull(commentsID);
        if (!commentsID.matches(Validator.QC_COMMENTS_ID_REGEX))
            throw new WebServiceException(ApplicationCodes.INVALID_QC_COMMENTS_ID, HTTPCodes.PRECONDITION_FAILED, WebExceptionType.VALIDATION);
        CommonLogger.info(Validator.class, "Given Comments ID [".concat(commentsID).concat("] is valid!"));
    }

    public static void checkQcFilesID(String filesID) throws WebServiceException {
        Validator.notNull(filesID);
        if (!filesID.matches(Validator.QC_FILES_ID_REGEX))
            throw new WebServiceException(ApplicationCodes.INVALID_QC_FILES_ID, HTTPCodes.PRECONDITION_FAILED, WebExceptionType.VALIDATION);
        CommonLogger.info(Validator.class, "Given Files ID [".concat(filesID).concat("] is valid!"));
    }

    public static void checkQcProjectID(String projectID) throws WebServiceException {
        Validator.notNull(projectID);
        if (!projectID.matches(Validator.QC_PROJECT_ID_REGEX))
            throw new WebServiceException(ApplicationCodes.INVALID_QC_PROJECT_ID, HTTPCodes.PRECONDITION_FAILED, WebExceptionType.VALIDATION);
        CommonLogger.info(Validator.class, "Given Project ID [".concat(projectID).concat("] is valid!"));
    }

}
