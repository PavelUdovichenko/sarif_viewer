package org.example.sarif_viewer.parser.JsonKeys;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class messageKeys {
    String text;

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    @JsonCreator
    public messageKeys(@JsonProperty("text") String text) {
        this.text = text;
    }
}
