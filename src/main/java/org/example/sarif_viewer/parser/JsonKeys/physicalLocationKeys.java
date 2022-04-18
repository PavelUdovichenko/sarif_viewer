package org.example.sarif_viewer.parser.JsonKeys;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class physicalLocationKeys {
    artifactLocationKeys artifactLocation;

    public artifactLocationKeys getArtifactLocation() {
        return artifactLocation;
    }
    public void setArtifactLocation(artifactLocationKeys artifactLocation) {
        this.artifactLocation = artifactLocation;
    }

    @JsonCreator
    public physicalLocationKeys(@JsonProperty("artifactLocation") artifactLocationKeys artifactLocation) {
        this.artifactLocation = artifactLocation;
    }
}
