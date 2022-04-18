package org.example.sarif_viewer.parser.JsonKeys;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class artifactLocationKeys {
    String uri;

    public String getUri() {
        return uri;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }

    @JsonCreator
    public artifactLocationKeys(@JsonProperty("uri") String uri) {
        this.uri = uri;
    }
}
