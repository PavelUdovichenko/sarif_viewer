package org.example.sarif_viewer.parser.JsonKeys;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class physicalLocationKeys {
    artifactLocationKeys artifactLocation;
    regionKeys region;

    public artifactLocationKeys getArtifactLocation() {
        return artifactLocation;
    }
    public void setArtifactLocation(artifactLocationKeys artifactLocation) {
        this.artifactLocation = artifactLocation;
    }

    public regionKeys getRegion() { return region;}
    public void setRegion(regionKeys region) {this.region = region;}


    @JsonCreator
    public physicalLocationKeys(@JsonProperty("artifactLocation") artifactLocationKeys artifactLocation,
                                @JsonProperty("region") regionKeys region) {
        this.artifactLocation = artifactLocation;
        this.region = region;
    }


}
