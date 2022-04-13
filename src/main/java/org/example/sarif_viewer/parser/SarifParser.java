package org.example.sarif_viewer.parser;

/*import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ObjectMapper;*/
import org.example.sarif_viewer.fileChooser.FileOpen;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;


import java.io.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SarifParser {
   /* public static String getInfo(String x) {
        ObjectMapper objectMapper = new ObjectMapper();

        FileReader sarifReader = null;
        String info = null;
        try {

            sarifReader = new FileReader(FileOpen.pathFile);
            String json = String.valueOf(sarifReader);
            JsonNode jsonNode = objectMapper.readTree(json);
            info = jsonNode.get(x).asText();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return info;
    }*/



   public static String getInfo(String x) {
        String info = null;
        try {
            //читаем внутринности файла
            FileReader sarifReader = new FileReader(FileOpen.pathFile);
            JSONParser sarifParser = new JSONParser();
            JSONObject sarifObject = (JSONObject) sarifParser.parse(sarifReader);
            ContainerFactory containerFactory = new ContainerFactory() {
                @Override
                public Map createObjectContainer() {
                    return new LinkedHashMap<>();
                }

                @Override
                public List creatArrayContainer() {
                    return new LinkedList<>();
                }
            };
            info = (String) sarifObject.get(x);
            System.out.println(info);
            //JSONArray runs = (JSONArray) sarifObject.get("runs");
            //Iterator run = runs.iterator();
            //JSONObject tool = (JSONObject) run.next();

//            System.out.println(runs.get(0));

            // Выводим в цикле данные массива
//            while (r.hasNext()) {
//                JSONObject r1 = (JSONObject) r.next();
//                System.out.println("===>: " + r1.get("tool"));
//            }
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
    public static JSONArray getArray(String x) {

        JSONArray jarray = null;
        try {
            //читаем внутринности файла
            FileReader sarifReader = new FileReader(FileOpen.pathFile);
            JSONParser sarifParser = new JSONParser();
            JSONObject sarifObject = (JSONObject) sarifParser.parse(sarifReader);

            jarray = (JSONArray) sarifObject.get(x);
            //Iterator run = runs.iterator();
            //JSONObject tool = (JSONObject) run.next();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jarray;
    }

    /*public static JSONObject getStucture(JSONArray x) {
        JSONObject structure = null;
        try {
            //читаем внутринности файла
            FileReader sarifReader = new FileReader(FileOpen.pathFile);
            JSONParser sarifParser = new JSONParser();
            JSONObject sarifObject = (JSONObject) sarifParser.parse(sarifReader);

            structure = (JSONObject) sarifObject.get(x);
            //Iterator run = runs.iterator();
            //JSONObject tool = (JSONObject) run.next();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return structure;
    }*/

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
