package com.qc.skillscy.commons.misc;

public enum SwaggerAppInfo {
    MainWS("MAIN-WS", "MainWS", "This webservice exposes several APIs which are connected to the third-party applications for various purposes."),
    DeliveryWS("DELIVERY-WS", "DeliveryWS", "This webservice exposes several APIs for project related activities."),
    CandidateWS("CANDIDATE-WS", "CandidateWS", "This webservice exposes several APIs for employee on-boarding activities."),
    WorkwiseWS("WORKWISE-WS", "WorkwiseWS", "This webservice exposes several APIs for managing reward points of employee performance and other discount coupon codes."),
    BlogWS("BLOG-WS", "BlogWS", "This webservice exposes several APIs for articles writing related activities."),
    AuthWS("AUTH-WS", "AuthWS", "This webservice exposes several APIs for site, mobile app authentication activities."),
    ClientWS("CLIENT-WS", "ClientWS", "This webservice exposes several APIs for client on-boarding activities."),
    DocumentWS("DOCUMENT-WS", "DocumentWS", "This webservice exposes several APIs for creating or downloading various certificate from QC."),
    FeedbackWS("FEEDBACK-WS", "FeedbackWS", "This webservice exposes several APIs for getting feedback from employees and election related activities.");

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
