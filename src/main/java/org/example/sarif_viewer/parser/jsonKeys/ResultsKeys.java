package org.example.sarif_viewer.parser.jsonKeys;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class ResultsKeys {
    String ruleId;
    MessageKeys message;
    String level;
    ArrayList<LocationsKeys> locations;
    ArrayList<RelatedLocationsKeys> relatedLocations;
    ArrayList<CodeFlowsKeys> codeFlows;


    public String getRuleId() {
        return ruleId;
    }
    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public MessageKeys getMessage() {
        return message;
    }
    public void setMessage(MessageKeys message) {
        this.message = message;
    }

    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }

    public ArrayList<LocationsKeys> getLocations() {
        return locations;
    }
    public void setLocations(ArrayList<LocationsKeys> locations) {
        this.locations = locations;
    }

    public ArrayList<RelatedLocationsKeys> getRelatedLocations() {
        return relatedLocations;
    }
    public void setRelatedLocations(ArrayList<RelatedLocationsKeys> relatedLocations) {
        this.relatedLocations = relatedLocations;
    }

    public ArrayList<CodeFlowsKeys> getCodeFlows() {
        return codeFlows;
    }
    public void setCodeFlows(ArrayList<CodeFlowsKeys> codeFlows) {
        this.codeFlows = codeFlows;
    }

    @JsonCreator
    public ResultsKeys(@JsonProperty("ruleId") String ruleId,
                       @JsonProperty("message") MessageKeys message,
                       @JsonProperty("level") String level,
                       @JsonProperty("locations") ArrayList<LocationsKeys> locations,
                       @JsonProperty("relatedLocations") ArrayList<RelatedLocationsKeys> relatedLocations,
                       @JsonProperty("codeFlows") ArrayList<CodeFlowsKeys> codeFlows) {
        this.ruleId = ruleId;
        this.message = message;
        this.level = level;
        this.locations = locations;
        this.relatedLocations = relatedLocations;
        this.codeFlows = codeFlows;
    }
}
