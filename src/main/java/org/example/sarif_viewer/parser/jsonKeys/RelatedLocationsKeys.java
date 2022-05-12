package org.example.sarif_viewer.parser.jsonKeys;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RelatedLocationsKeys {
    String id;
    PhysicalLocationKeys physicalLocation;
    MessageKeys message;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

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
    public RelatedLocationsKeys(@JsonProperty("id") String id,
                                @JsonProperty("physicalLocation") PhysicalLocationKeys physicalLocation,
                                @JsonProperty("message") MessageKeys message) {
        this.id = id;
        this.physicalLocation = physicalLocation;
        this.message = message;
    }
}