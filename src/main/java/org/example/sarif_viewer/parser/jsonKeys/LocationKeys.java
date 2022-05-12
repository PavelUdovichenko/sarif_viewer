package org.example.sarif_viewer.parser.jsonKeys;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationKeys {
    PhysicalLocationKeys physicalLocation;
    MessageKeys message;

    public PhysicalLocationKeys getPhysicalLocation() {
        return physicalLocation;
    }
    public void setPhysicalLocation(PhysicalLocationKeys physicalLocation) {
        this.physicalLocation = physicalLocation;
    }

    public MessageKeys getMessage() {
        return message;
    }
    public void setMessage(MessageKeys message) {
        this.message = message;
    }

    @JsonCreator
    public LocationKeys(@JsonProperty("physicalLocation") PhysicalLocationKeys physicalLocation,
                         @JsonProperty("message") MessageKeys message) {
        this.physicalLocation = physicalLocation;
        this.message = message;
    }
}
