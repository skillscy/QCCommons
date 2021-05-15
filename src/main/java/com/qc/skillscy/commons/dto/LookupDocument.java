package com.qc.skillscy.commons.dto;

import java.util.Map;

public class LookupDocument {

    private String latestID;

    public LookupDocument() {
    }

    public LookupDocument(Map<String, Object> databaseValue) {
        this.latestID = (String) databaseValue.get("latest_id");
    }

    public String getLatestID() {
        return latestID;
    }

    public void setLatestID(String latestID) {
        this.latestID = latestID;
    }
}
