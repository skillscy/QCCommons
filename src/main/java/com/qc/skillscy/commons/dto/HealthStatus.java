package com.qc.skillscy.commons.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.qc.skillscy.commons.misc.QcSwagger;

public class HealthStatus {

    private String status;
    private String productOwner;

    public HealthStatus(String uri) {
        this.status = "UP";
        this.productOwner = QcSwagger.COMPANY;
    }

    @JsonProperty("Status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("ProductOwner")
    public String getProductOwner() {
        return productOwner;
    }
}
