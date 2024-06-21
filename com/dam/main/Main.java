package com.dam.main;

import org.json.JSONArray;
import org.json.JSONObject;
import com.dam.config.Config;
import com.dam.utils.Utils;

public class Main {   
    
    public static void main(String[] args) throws Exception {

        Config config = new Config();
        String path = config.getFolder();
        String fileName = config.getFileName();
        String filePrefix = config.getFilePrefix();

        Utils utils = new Utils();
        String fileContent = utils.readJSONTextFile(path,fileName);
        JSONArray jsonMainArr = utils.jsonMainArray(fileContent);

        JSONObject jsonChildObj;
        String jsonSubArray = "";
        String decoded = "";
        org.w3c.dom.Document gmlDoc;
        JSONObject jsonObject;
        
        for(int i=0; i<jsonMainArr.length()-1;i++){

            jsonChildObj = jsonMainArr.getJSONObject(i);
            jsonSubArray = jsonChildObj.getString("data");            
            decoded = utils.base64toString(jsonSubArray);            
            utils.saveGmlFile(decoded, path + filePrefix + i + ".gml");            
            gmlDoc = utils.readGmlFile(path + filePrefix + i + ".gml"); 

            if (gmlDoc != null){
                jsonObject = utils.parseGMLtoJSON(gmlDoc);
                utils.saveJSONFile(jsonObject, path + "sample_" + i + ".json");
            }
        }        
    }
}
