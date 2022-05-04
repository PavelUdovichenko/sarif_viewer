package org.example.sarif_viewer.parser.jsonKeys;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RulesKeys {
    String id;
    String name;
    ShortDescriptionKeys shortDescription;

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

    public ShortDescriptionKeys getShortDescription() {
        return shortDescription;
    }
    public void setShortDescription(ShortDescriptionKeys shortDescription) {
        this.shortDescription = shortDescription;
    }

    @JsonCreator
    public RulesKeys(@JsonProperty("id") String id,
                     @JsonProperty("name") String name,
                     @JsonProperty("shortDescription") ShortDescriptionKeys shortDescription) {
        this.id = id;
        this.name = name;
        this.shortDescription = shortDescription;
    }
}