package com.qc.skillscy.commons.misc;

public enum SwaggerAppInfo {
    MainWS("MAIN-WS", "QC MainWS", "This webservice exposes several APIs which are connected to the third-party applications for various purposes.");

    private String springAppName;
    private String appName;
    private String description;

    SwaggerAppInfo(String springAppName, String appName, String description) {
        this.springAppName = springAppName;
        this.appName = appName;
        this.description = description;
    }

    private String getSpringAppName() {
        return springAppName;
    }

    public String getAppName() {
        return appName;
    }

    public String getDescription() {
        return description;
    }
}
