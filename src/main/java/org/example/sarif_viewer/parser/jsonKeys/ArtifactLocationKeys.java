package org.example.sarif_viewer.parser.jsonKeys;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ArtifactLocationKeys {
    String uri;

    public String getUri() {
        return uri;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }

    @JsonCreator
    public ArtifactLocationKeys(@JsonProperty("uri") String uri) {
        this.uri = uri;
    }
}
