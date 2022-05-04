package org.example.sarif_viewer.parser.jsonKeys;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationsKeys {
    PhysicalLocationKeys physicalLocation;

    public PhysicalLocationKeys getPhysicalLocation() {
        return physicalLocation;
    }
    public void setPhysicalLocation(PhysicalLocationKeys physicalLocation) {
        this.physicalLocation = physicalLocation;
    }

    @JsonCreator
    public LocationsKeys(@JsonProperty("physicalLocation") PhysicalLocationKeys physicalLocation) {
        this.physicalLocation = physicalLocation;
    }
}