package org.example.sarif_viewer.parser.jsonKeys;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PhysicalLocationKeys {
    ArtifactLocationKeys artifactLocation;
    RegionKeys region;

    public ArtifactLocationKeys getArtifactLocation() {
        return artifactLocation;
    }
    public void setArtifactLocation(ArtifactLocationKeys artifactLocation) {
        this.artifactLocation = artifactLocation;
    }

    public RegionKeys getRegion() { return region;}
    public void setRegion(RegionKeys region) {this.region = region;}

    @JsonCreator
    public PhysicalLocationKeys(@JsonProperty("artifactLocation") ArtifactLocationKeys artifactLocation,
                                @JsonProperty("region") RegionKeys region) {
        this.artifactLocation = artifactLocation;
        this.region = region;
    }
}
