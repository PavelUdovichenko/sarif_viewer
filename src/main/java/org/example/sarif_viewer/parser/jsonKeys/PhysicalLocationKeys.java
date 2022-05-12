package org.example.sarif_viewer.parser.jsonKeys;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PhysicalLocationKeys {
    RegionKeys region;
    ArtifactLocationKeys artifactLocation;

    public RegionKeys getRegion() { return region;}
    public void setRegion(RegionKeys region) {this.region = region;}

    public ArtifactLocationKeys getArtifactLocation() {
        return artifactLocation;
    }
    public void setArtifactLocation(ArtifactLocationKeys artifactLocation) {
        this.artifactLocation = artifactLocation;
    }

    @JsonCreator
    public PhysicalLocationKeys(@JsonProperty("region") RegionKeys region,
                                @JsonProperty("artifactLocation") ArtifactLocationKeys artifactLocation) {
        this.region = region;
        this.artifactLocation = artifactLocation;
    }
}
