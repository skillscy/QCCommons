package com.qc.skillscy.commons.codes;

import java.text.MessageFormat;

public enum ApplicationCodes {

    NO_BODY_FOUND(1051, "Null response body found"),
    VALIDATION_NULL_FOUND(1052, "Null object found in a non-null place"),
    INTERNAL_SERVER_ERROR(1053, "Some internal error occurred"),
    FALLBACK_DETECTED(1054, "Circuit break happened. Fallback called"),
    INVALID_QC_EMPLOYEE_ID(1055, "Invalid QC Employee ID"),
    INVALID_QC_INTERN_ID(1056, "Invalid QC Employee ID"),
    INVALID_QC_TRAINEE_ID(1057, "Invalid QC Trainee ID"),
    INVALID_QC_ID(1058, "Invalid QC Employee or Trainee ID"),
    ERROR_JACKSON_CONVERSION(1059, "Some internal error occurred when parsing the argument with jackson methods"),
    FIREBASE_GOOGLE_CREDENTIALS_STREAM_ERROR(1060, "Error occurred during parsing Google Credentials strings"),
    INVALID_QC_ARTICLE_ID(1061, "Invalid QC Article ID"),
    INVALID_QC_COMMENTS_ID(1062, "Invalid QC Comments ID"),
    INVALID_QC_CLIENT_ID(1063, "Invalid QC Clients ID"),
    INVALID_QC_FILES_ID(1064, "Invalid QC Files ID"),
    INVALID_QC_PROJECT_ID(1065, "Invalid QC Project ID"),
    FIREBASE_DOCUMENT_EXCEPTION(1066, "Interruption or Execution exception occurred while getting a document from ApiFuture"),
    FIREBASE_DOCUMENT_NOT_EXIST(1067, "Requested Firebase document not available in Firestore"),
    FIREBASE_DOCUMENT_NOT_RETRIEVED(1068, "Available Firestore document is not retrieved"),
    DOCUMENT_LOOKUP_LATEST_ID_NOT_FOUND(1069, "Latest ID parameter missing the Lookup document"),
    DOCUMENT_LOOKUP_AVAILABLE_PROJECT_IDS_NOT_FOUND(1070, "Available project IDs parameter missing the Lookup document"),
    DOCUMENT_LOOKUP_PEOPLE_LATEST_IDs_NOT_FOUND(1071, "Available People's project IDs parameter missing the Lookup document"),
    INVALID_DATE_FORMAT_PARSE_TIMESTAMP(1072, "Invalid String Date Format found when parsing to Timestamp"),
    INVALID_DATE_TIME_FORMAT_PARSE_TIMESTAMP(1073, "Invalid String DateTime Format found when parsing to Timestamp"),

    /* MainWS */
    INVALID_OR_NO_VIDEO_CHANNEL(1100, "The requested channel is invalid or it doesn't contain any video"),

    /* CandidateWS */
    INVALID_EMPLOYMENT_TYPE(1250, "EmploymentTypes should be one among [employee, intern, trainee]");

    private int appCode;
    private String appCodeDescription;

    ApplicationCodes(int appCode, String appCodeDescription) {
        this.appCode = appCode;
        this.appCodeDescription = appCodeDescription;
    }

    public int getAppCode() {
        return appCode;
    }

    public String getAppCodeDescription() {
        return appCodeDescription;
    }

    public String logMessage() {
        final String messageFormat = "{0} - {1}";
        return MessageFormat.format(messageFormat, this.getAppCode(), this.getAppCodeDescription());
    }
}
