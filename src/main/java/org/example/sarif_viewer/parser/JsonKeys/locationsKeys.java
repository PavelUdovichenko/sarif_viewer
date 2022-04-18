package org.example.sarif_viewer.parser.JsonKeys;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class locationsKeys {
    physicalLocationKeys physicalLocation;

    public physicalLocationKeys getPhysicalLocation() {
        return physicalLocation;
    }
    public void setPhysicalLocation(physicalLocationKeys physicalLocation) {
        this.physicalLocation = physicalLocation;
    }

    @JsonCreator
    public locationsKeys(@JsonProperty("physicalLocation") physicalLocationKeys physicalLocation) {
        this.physicalLocation = physicalLocation;
    }
}