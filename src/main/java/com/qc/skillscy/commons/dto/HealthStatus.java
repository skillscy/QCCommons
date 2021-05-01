package com.qc.skillscy.commons.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.qc.skillscy.commons.misc.QcSwagger;

import java.util.HashMap;
import java.util.Map;

public class HealthStatus {

    private String applicationURL;
    private String status;
    private Map<String, String> url;
    private String productOwner;

    public HealthStatus(String uri) {
        this.applicationURL = uri;
        this.status = "UP";
        this.productOwner = QcSwagger.COMPANY;

        // List of URLs
        this.url = new HashMap<>();
        this.url.put("SwaggerURL", uri.concat("/swagger-ui.html"));
    }

    @JsonProperty("ApplicationURL")
    public String getApplicationURL() {
        return applicationURL;
    }

    @JsonProperty("Status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("URL")
    public Map<String, String> getURL() {
        return url;
    }

    @JsonProperty("ProductOwner")
    public String getProductOwner() {
        return productOwner;
    }
}
