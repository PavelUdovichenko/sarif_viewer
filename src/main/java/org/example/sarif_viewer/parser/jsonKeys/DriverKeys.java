package org.example.sarif_viewer.parser.jsonKeys;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class DriverKeys {
    String name;
    String organization;
    ArrayList<RulesKeys> rules;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getOrganization() {
        return organization;
    }
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public ArrayList<RulesKeys> getRules() {
        return rules;
    }
    public void setRules(ArrayList<RulesKeys> rules) {
        this.rules = rules;
    }

    @JsonCreator
    public DriverKeys(@JsonProperty("name") String name,
                      @JsonProperty("organization") String organization,
                      @JsonProperty("rules") ArrayList<RulesKeys> rules) {
        this.name = name;
        this.organization = organization;
        this.rules = rules;
    }
}
