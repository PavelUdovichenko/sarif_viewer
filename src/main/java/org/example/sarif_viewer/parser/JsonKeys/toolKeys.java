package org.example.sarif_viewer.parser.JsonKeys;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class toolKeys {
    driverKeys driver;

    public driverKeys getDriver() {
        return driver;
    }
    public void setDriver(driverKeys driver) {
        this.driver = driver;
    }

    @JsonCreator
    public toolKeys(@JsonProperty("driver") driverKeys driver) {
        this.driver = driver;
    }
}
