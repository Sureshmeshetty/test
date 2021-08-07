package common;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonReader {
    public static String[][] readJsonData(String jsonFile) {
        String[][] data=null;

        try{
            JSONParser jsonParser = new JSONParser();
            FileReader reader = new FileReader(System.getProperty("user.dir")+jsonFile);
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            JSONArray userCredsJsonArray = (JSONArray) jsonObject.get("ProductName");
            int count = userCredsJsonArray.size();
            JSONObject user = (JSONObject)userCredsJsonArray.get(0);
            int noOfFields = user.keySet().size();
            data= new String[count][noOfFields];
            for(int i=0;i<count;i++){
                user = (JSONObject) userCredsJsonArray.get(i);
                int j=0;
                for(Object key:user.keySet()){
                    System.out.println("Key Name: "+key);
                    data[i][j++] = (String) user.get(key);
                }
            }
        }
        catch (IOException e){
            System.out.println("File not Found: "+jsonFile);
        }
        catch (ParseException e){
            System.out.println("Unable to parser JsonFile: "+e.getMessage());
        }
        return data;
    }
}
