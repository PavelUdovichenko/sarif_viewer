package org.example.sarif_viewer.parser.JsonKeys;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class mainKeys {
    String version;
    String $schema;
    ArrayList<runsKeys> runs;

    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }

    public String get$schema() {
        return $schema;
    }
    public void set$schema(String $schema) {
        this.$schema = $schema;
    }

    public ArrayList<runsKeys> getRuns() {
        return runs;
    }
    public void setRuns(ArrayList<runsKeys> runs) {
        this.runs = runs;
    }

    @JsonCreator
    public mainKeys(@JsonProperty("version") String version,
                    @JsonProperty("$schema") String $schema,
                    @JsonProperty("runs") ArrayList<runsKeys> runs) {
        this.version = version;
        this.$schema = $schema;
        this.runs = runs;
    }
}