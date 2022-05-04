package org.example.sarif_viewer.parser.jsonKeys;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageKeys {
    String text;

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    @JsonCreator
    public MessageKeys(@JsonProperty("text") String text) {
        this.text = text;
    }
}
