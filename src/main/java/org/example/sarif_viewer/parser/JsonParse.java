package org.example.sarif_viewer.parser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.sarif_viewer.fileChooser.FileOpen;
import org.example.sarif_viewer.parser.JsonKeys.mainKeys;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JsonParse {

    public static mainKeys parseJson() {
        mainKeys object = null;
        try {
            FileReader sarifReader = new FileReader(FileOpen.pathFile);
            JSONParser sarifParser = new JSONParser();
            JSONObject sarifObject = (JSONObject) sarifParser.parse(sarifReader);

            String json = String.valueOf(sarifObject);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            object = objectMapper.readValue(json, mainKeys.class);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return object;
    }
}
