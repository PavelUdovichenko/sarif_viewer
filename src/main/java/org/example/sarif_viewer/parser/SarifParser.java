package org.example.sarif_viewer.parser;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Iterator;

public class SarifParser {
    private static final String filepath = "F:\\JetBrains\\IntelliJ IDEA 2021.2.2\\IdeaProjects\\sarif_viewer\\src\\main\\resources\\result.sarif";

    public static String getInfo(String x) {
        String info = "";

        try {
            //читаем внутринности файла
            FileReader sarifReader = new FileReader(filepath);
            JSONParser sarifParser = new JSONParser();
            JSONObject sarifObject = (JSONObject) sarifParser.parse(sarifReader);

            info = (String) sarifObject.get(x);

//            JSONArray runs = (JSONArray) sarifObject.get("runs");
//
//            for (Object run : runs) {
//                JSONObject runsinnerObject = (JSONObject) run;
//                JSONObject structureTool = (JSONObject) runsinnerObject.get("tool");
//                JSONObject structureDriver = (JSONObject) structureTool.get("driver");
//
//                JSONArray rules = (JSONArray) structureDriver.get("rules");
//
//                for (Object rule : rules) {
//                    JSONObject rulesinnerObject = (JSONObject) rule;
//                    long rulesId = (long) rulesinnerObject.get("ruleId");
//                }
//            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return info;
    }

//    кароче эту тему переписать на отдельные блоки каждый из которых возвращает то что нужно или спроси у андрюхи как
//    public static void SarifParse() {
//
//        try {
//            //читаем внутринности файла
//            FileReader sarifReader = new FileReader(filepath);
//
//            JSONParser sarifParser = new JSONParser();
//            JSONObject sarifObject = (JSONObject) sarifParser.parse(sarifReader);
//
//            //пример получения строк
//            String version = (String) sarifObject.get("version");
//            String $schema = (String) sarifObject.get("$schema");
//
//            //пример получения интов так как внешних интов нет все внутри массивов там это и рассмотрим
//            //пример получения массива(далее нужно будет углубляться)
//            JSONArray runs = (JSONArray) sarifObject.get("runs");
//
//            Iterator runsi = runs.iterator();
//
//            //пример получения отдельных элеменов из массива
//           while(runsi.hasNext()) {
//               JSONObject runsinnerObject = (JSONObject) runsi.next();
//               JSONObject structureTool = (JSONObject) runsinnerObject.get("tool");
//               JSONObject structureDriver = (JSONObject) structureTool.get("driver");
//               JSONArray rules = (JSONArray) structureDriver.get("rules");
//               Iterator rulesi = rules.iterator();
//               while(rulesi.hasNext()) {
//                   JSONObject rulesinnerObject = (JSONObject) rulesi.next();
//                   long rulesId = (long) rulesinnerObject.get("id");
//               }
//           }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }
}
