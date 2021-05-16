package com.qc.skillscy.commons.dto;

import java.util.HashMap;
import java.util.Map;

public class PeopleLookupDocument {

    private String latestIDOfEmployee;
    private String latestIDOfIntern;
    private String latestIDOfTrainee;

    public PeopleLookupDocument(Map<String, Object> databaseValue) {
        this.latestIDOfEmployee = (String) databaseValue.getOrDefault("latest_id_1", null);
        this.latestIDOfIntern = (String) databaseValue.getOrDefault("latest_id_2", null);
        this.latestIDOfTrainee = (String) databaseValue.getOrDefault("latest_id_3", null);
    }

    public String getLatestIDOfEmployee() {
        return latestIDOfEmployee;
    }

    public void setLatestIDOfEmployee(String latestIDOfEmployee) {
        this.latestIDOfEmployee = latestIDOfEmployee;
    }

    public String getLatestIDOfIntern() {
        return latestIDOfIntern;
    }

    public void setLatestIDOfIntern(String latestIDOfIntern) {
        this.latestIDOfIntern = latestIDOfIntern;
    }

    public String getLatestIDOfTrainee() {
        return latestIDOfTrainee;
    }

    public void setLatestIDOfTrainee(String latestIDOfTrainee) {
        this.latestIDOfTrainee = latestIDOfTrainee;
    }

    public Map<String, Object> dbValue() {
        Map<String, Object> mapObject = new HashMap<>();
        mapObject.put("latest_id_1", this.latestIDOfEmployee);
        mapObject.put("latest_id_2", this.latestIDOfIntern);
        mapObject.put("latest_id_3", this.latestIDOfTrainee);

        return mapObject;
    }
}
