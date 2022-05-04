package org.example.sarif_viewer.parser.jsonKeys;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class RunsKeys {
    ToolKeys tool;
    ArrayList<ResultsKeys> results;

    public ToolKeys getTool() {
        return tool;
    }
    public void setTool(ToolKeys tool) {
        this.tool = tool;
    }

    public ArrayList<ResultsKeys> getResults() {
        return results;
    }
    public void setResults(ArrayList<ResultsKeys> results) {
        this.results = results;
    }

    @JsonCreator
    public RunsKeys(@JsonProperty("tool") ToolKeys tool,
                    @JsonProperty("results") ArrayList<ResultsKeys> results) {
        this.tool = tool;
        this.results = results;
    }
}
