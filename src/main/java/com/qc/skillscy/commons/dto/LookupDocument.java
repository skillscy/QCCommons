package com.qc.skillscy.commons.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LookupDocument {

    private String latestID;

    @JsonProperty("latest_id")
    public String getLatestID() {
        return latestID;
    }

    @JsonProperty("latest_id")
    public void setLatestID(String latestID) {
        this.latestID = latestID;
    }
}
