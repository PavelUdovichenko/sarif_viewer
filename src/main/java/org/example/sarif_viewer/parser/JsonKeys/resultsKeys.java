package org.example.sarif_viewer.parser.JsonKeys;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class resultsKeys {
    String level;
    messageKeys message;
    ArrayList<locationsKeys> locations;
    String ruleId;

    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }

    public messageKeys getMessage() {
        return message;
    }
    public void setMessage(messageKeys message) {
        this.message = message;
    }

    public ArrayList<locationsKeys> getLocations() {
        return locations;
    }
    public void setLocations(ArrayList<locationsKeys> locations) {
        this.locations = locations;
    }

    public String getRuleId() {
        return ruleId;
    }
    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    @JsonCreator
    public resultsKeys(@JsonProperty("level") String level,
                       @JsonProperty("message") messageKeys message,
                       @JsonProperty("locations") ArrayList<locationsKeys> locations,
                       @JsonProperty("ruleId") String ruleId) {
        this.level = level;
        this.message = message;
        this.locations = locations;
        this.ruleId = ruleId;
    }
}
