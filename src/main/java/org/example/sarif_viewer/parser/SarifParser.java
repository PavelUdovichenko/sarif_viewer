package org.example.sarif_viewer.parser;



import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class SarifParser {
    private static final String filepath = "result.sarif";
    public static String getVersion(){
        String version = null;
        try {
            //читаем внутринности файла
            FileReader sarifReader = new FileReader(filepath);

            JSONParser sarifParser = new JSONParser();
            JSONObject sarifObject = (JSONObject) sarifParser.parse(sarifReader);

            //пример получения строк
            version = (String) sarifObject.get("version");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return version;
    }
    //кароче эту тему переписать на отдельные блоки каждый из которых возвращает то что нужно или спроси у андрюхи как
        public static void SarifParse() {

        try {
            //читаем внутринности файла
            FileReader sarifReader = new FileReader(filepath);

            JSONParser sarifParser = new JSONParser();
            JSONObject sarifObject = (JSONObject) sarifParser.parse(sarifReader);

            //пример получения строк
            String version = (String) sarifObject.get("version");

            String $schema = (String) sarifObject.get("$schema");


            //пример получения интов так как внешних интов нет все внутри массивов там это и рассмотрим
            //пример получения массива(далее нужно будет углубляться)
            JSONArray runs = (JSONArray) sarifObject.get("runs");

            Iterator runsi = runs.iterator();
            //пример получения отдельных элеменов из массива
           while(runsi.hasNext()) {
               JSONObject runsinnerObject = (JSONObject) runsi.next();
               JSONObject structureTool = (JSONObject) runsinnerObject.get("tool");
               JSONObject structureDriver = (JSONObject) structureTool.get("driver");
               JSONArray rules = (JSONArray) structureDriver.get("rules");
               Iterator rulesi = rules.iterator();
               while(rulesi.hasNext()) {
                   JSONObject rulesinnerObject = (JSONObject) rulesi.next();
                   long rulesId = (long) rulesinnerObject.get("id");
               }


           }

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
