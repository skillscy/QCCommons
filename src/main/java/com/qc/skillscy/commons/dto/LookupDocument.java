package com.qc.skillscy.commons.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LookupDocument {

    @JsonProperty("latest_id")
    private String latestID;

    public String getLatestID() {
        return latestID;
    }

    public void setLatestID(String latestID) {
        this.latestID = latestID;
    }
}
