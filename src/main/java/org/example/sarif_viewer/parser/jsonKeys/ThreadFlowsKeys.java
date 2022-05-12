package org.example.sarif_viewer.parser.jsonKeys;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class ThreadFlowsKeys {
    ArrayList<LocationsKeys> locations;

    public ArrayList<LocationsKeys> getLocations() {
        return locations;
    }
    public void setLocations(ArrayList<LocationKeys> location) {
        this.locations = locations;
    }

    @JsonCreator
    public ThreadFlowsKeys(@JsonProperty("locations") ArrayList<LocationsKeys> locations) {
        this.locations = locations;
    }
}