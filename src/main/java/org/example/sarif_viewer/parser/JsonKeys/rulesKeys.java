package org.example.sarif_viewer.parser.JsonKeys;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class rulesKeys {
    String id;
    String name;
    shortDescriptionKeys shortDescription;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public shortDescriptionKeys getShortDescription() {
        return shortDescription;
    }
    public void setShortDescription(shortDescriptionKeys shortDescription) {
        this.shortDescription = shortDescription;
    }

    @JsonCreator
    public rulesKeys(@JsonProperty("id") String id,
                     @JsonProperty("name") String name,
                     @JsonProperty("shortDescription") shortDescriptionKeys shortDescription) {
        this.id = id;
        this.name = name;
        this.shortDescription = shortDescription;
    }
}