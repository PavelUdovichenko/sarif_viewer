package org.example.sarif_viewer.parser.jsonKeys;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ToolKeys {
    DriverKeys driver;

    public DriverKeys getDriver() {
        return driver;
    }
    public void setDriver(DriverKeys driver) {
        this.driver = driver;
    }

    @JsonCreator
    public ToolKeys(@JsonProperty("driver") DriverKeys driver) {
        this.driver = driver;
    }
}
