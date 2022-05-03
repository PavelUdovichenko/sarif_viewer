package org.example.sarif_viewer.parser.JsonKeys;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class regionKeys {
    Integer startLine;
    Integer startColumn;
    Integer endLine;
    Integer endColumn;

    public Integer getStartLine() {return startLine;}
    public void setStartLine(Integer startLine){this.startLine = startLine;}

    public Integer getStartColumn() {return startColumn;}
    public void setStartColumn(Integer startColumn){this.startColumn = startColumn;}

    public Integer getEndLine() {return endLine;}
    public void setEndLine(Integer endLine){this.endLine = endLine;}

    public Integer getEndColumn() {return endColumn;}
    public void setEndColumn(Integer endColumn){this.endColumn = endColumn;}

    @JsonCreator
    public regionKeys(@JsonProperty("startLine") Integer startLine,
                      @JsonProperty("startColumn") Integer startColumn,
                      @JsonProperty("endLine") Integer endLine,
                      @JsonProperty("endColumn") Integer endColumn) {
        this.startLine = startLine;
        this.startColumn = startColumn;
        this.endColumn = endColumn;
        this.endLine = endLine;
    }
}