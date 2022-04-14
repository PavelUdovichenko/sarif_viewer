package org.example.sarif_viewer.parser;

import org.example.sarif_viewer.fileChooser.FileOpen;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Objects;

public class SarifParser {
    public static String getInfo(String x) {
        String info = "";
        try {
            String[] keys = x.split("\\.");

            //читаем внутринности файла
            FileReader sarifReader = new FileReader(FileOpen.pathFile);
            JSONParser sarifParser = new JSONParser();
            JSONObject sarifObject = (JSONObject) sarifParser.parse(sarifReader);

            JSONArray mainTree = (JSONArray) sarifObject.get("runs");
            JSONObject childrens = (JSONObject) sarifParser.parse(String.valueOf(mainTree.get(0)));

//            System.out.println(Arrays.toString(keys));

            info = parseJSON(sarifParser, childrens, keys, info);

//            System.out.println(info);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return info;
    }

    public static String parseJSON(JSONParser sarifParser, JSONObject childrens, String[] keys, String str) throws ParseException {
        for (int i = 0; i < keys.length; i++) {
            if (Objects.equals(keys[i], "rules") || Objects.equals(keys[i], "results") || Objects.equals(keys[i], "locations") || Objects.equals(keys[i], "artifacts")) {
                JSONArray arrayTree = (JSONArray) childrens.get(keys[i]);
                childrens = (JSONObject) sarifParser.parse(String.valueOf(arrayTree.get(0)));
            } else {
                if (i == keys.length - 1) {
                    if (childrens.containsKey(keys[i]))
                        if (childrens.containsKey("uri")) {
                            String[] uri = childrens.get(keys[i]).toString().split("/");
                            str = uri[uri.length-1];
                        } else
                            str = (String) childrens.get(keys[i]);
                    else
                        str = "-";
                } else {
                    str = String.valueOf(updTree(sarifParser, childrens, keys[i]));
                    childrens = updTree(sarifParser, childrens, keys[i]);
                }
            }
        }
        return str;
    }

    public static JSONObject updTree(JSONParser sarifParser, JSONObject child, String s) throws ParseException {
        return (JSONObject) sarifParser.parse(String.valueOf(child.get(s)));
    }
}