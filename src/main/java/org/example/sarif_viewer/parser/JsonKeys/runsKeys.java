package org.example.sarif_viewer.parser.JsonKeys;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class runsKeys {
    toolKeys tool;
    ArrayList<resultsKeys> results;

    public toolKeys getTool() {
        return tool;
    }
    public void setTool(toolKeys tool) {
        this.tool = tool;
    }

    public ArrayList<resultsKeys> getResults() {
        return results;
    }
    public void setResults(ArrayList<resultsKeys> results) {
        this.results = results;
    }

    @JsonCreator
    public runsKeys(@JsonProperty("tool") toolKeys tool,
                    @JsonProperty("results") ArrayList<resultsKeys> results) {
        this.tool = tool;
        this.results = results;
    }
}
