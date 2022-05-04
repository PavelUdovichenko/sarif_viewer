package org.example.sarif_viewer.parser.jsonKeys;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class ResultsKeys {
    String level;
    MessageKeys message;
    ArrayList<LocationsKeys> locations;
    String ruleId;

    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }

    public MessageKeys getMessage() {
        return message;
    }
    public void setMessage(MessageKeys message) {
        this.message = message;
    }

    public ArrayList<LocationsKeys> getLocations() {
        return locations;
    }
    public void setLocations(ArrayList<LocationsKeys> locations) {
        this.locations = locations;
    }

    public String getRuleId() {
        return ruleId;
    }
    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    @JsonCreator
    public ResultsKeys(@JsonProperty("level") String level,
                       @JsonProperty("message") MessageKeys message,
                       @JsonProperty("locations") ArrayList<LocationsKeys> locations,
                       @JsonProperty("ruleId") String ruleId) {
        this.level = level;
        this.message = message;
        this.locations = locations;
        this.ruleId = ruleId;
    }
}
