package org.example.sarif_viewer.parser.jsonKeys;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class CodeFlowsKeys {
    ArrayList<ThreadFlowsKeys> threadFlows;

    public ArrayList<ThreadFlowsKeys> getThreadFlows() {
        return threadFlows;
    }
    public void setThreadFlows(ArrayList<ThreadFlowsKeys> threadFlows) {
        this.threadFlows = threadFlows;
    }

    @JsonCreator
    public CodeFlowsKeys(@JsonProperty("threadFlows") ArrayList<ThreadFlowsKeys> threadFlows) {
        this.threadFlows = threadFlows;
    }
}