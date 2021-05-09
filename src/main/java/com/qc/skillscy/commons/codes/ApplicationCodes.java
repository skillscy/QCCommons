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

    /* '/youtube' */
    INVALID_OR_NO_VIDEO_CHANNEL(1100, "The requested channel is invalid or it doesn't contain any video");

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
