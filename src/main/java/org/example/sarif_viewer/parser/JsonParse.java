package org.example.sarif_viewer.parser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.sarif_viewer.fileChooser.FileOpen;
import org.example.sarif_viewer.parser.JsonKeys.mainKeys;

import java.io.FileReader;
import java.io.IOException;

public class JsonParse {
    public static mainKeys parseJson() {
        mainKeys object = null;
        try {
            FileReader sarifReader = new FileReader(FileOpen.pathFile);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            object = objectMapper.readValue(sarifReader, mainKeys.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return object;
    }
}
