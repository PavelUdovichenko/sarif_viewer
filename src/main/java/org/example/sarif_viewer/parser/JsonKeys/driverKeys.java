package org.example.sarif_viewer.parser.JsonKeys;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class driverKeys {
    String name;
    String organization;
    ArrayList<rulesKeys> rules;

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

    public ArrayList<rulesKeys> getRules() {
        return rules;
    }
    public void setRules(ArrayList<rulesKeys> rules) {
        this.rules = rules;
    }

    @JsonCreator
    public driverKeys(@JsonProperty("name") String name,
                      @JsonProperty("organization") String organization,
                      @JsonProperty("rules") ArrayList<rulesKeys> rules) {
        this.name = name;
        this.organization = organization;
        this.rules = rules;
    }
}
