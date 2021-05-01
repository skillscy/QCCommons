package com.qc.skillscy.commons.codes;

public enum ApplicationCodes {

    NO_BODY_FOUND(1051, "Null response body found"),
    VALIDATION_NULL_FOUND(1052, "Null object found in a non-null place"),
    INTERNAL_SERVER_ERROR(1053, "Some internal error occurred"),
    FALLBACK_DETECTED(1054, "Circuit break happened. Fallback called"),

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
}