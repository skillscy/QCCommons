package com.qc.skillscy.commons.dto;

import com.qc.skillscy.commons.misc.QcCommonConstants;
import com.qc.skillscy.commons.misc.Validator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LookupDocument {

    private String latestID;
    private List<String> availableProjectIDs;

    public LookupDocument() {
    }

    public LookupDocument(Map<String, Object> databaseValue) {
        this.latestID = (String) databaseValue.get(QcCommonConstants.LOOKUP_DOC_LATEST_ID);

        List<String> availableProjectIDs = null;
        Object o = databaseValue.get("available_ids");
        if (Validator.isNotNull(o)) {
            if (o instanceof List<?>) {
                availableProjectIDs = ((List<Object>) o).stream().map(String::valueOf).collect(Collectors.toList());
            }
        }

        this.availableProjectIDs = availableProjectIDs;
    }

    public String getLatestID() {
        return latestID;
    }

    public void setLatestID(String latestID) {
        this.latestID = latestID;
    }

    public List<String> getAvailableProjectIDs() {
        return availableProjectIDs;
    }

    public void setAvailableProjectIDs(List<String> availableProjectIDs) {
        this.availableProjectIDs = availableProjectIDs;
    }
}
