package org.example.sarif_viewer.parser.jsonKeys;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class MainKeys {
    String version;
    String $schema;
    ArrayList<RunsKeys> runs;

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

    public ArrayList<RunsKeys> getRuns() {
        return runs;
    }
    public void setRuns(ArrayList<RunsKeys> runs) {
        this.runs = runs;
    }

    @JsonCreator
    public MainKeys(@JsonProperty("version") String version,
                    @JsonProperty("$schema") String $schema,
                    @JsonProperty("runs") ArrayList<RunsKeys> runs) {
        this.version = version;
        this.$schema = $schema;
        this.runs = runs;
    }
}