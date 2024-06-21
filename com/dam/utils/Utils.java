package com.dam.utils;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.dam.map.Map;

import org.w3c.dom.*;
import java.util.HashMap;
import java.util.logging.Logger;
import java.io.*;

public class Utils {
    
    Logger logger = Logger.getLogger(getClass().getName());

    public String readJSONTextFile(String path, String inTxtFile) throws Exception{        
        return new String(Files.readAllBytes(Paths.get(path + inTxtFile)));
    }

    public JSONArray jsonMainArray(String content){
        JSONObject jsonMainObj = new JSONObject(content);
        return jsonMainObj.getJSONArray("dataResponseObject");    
    }   

    public String base64toString(String base64){
        byte[] decoded = Base64.getDecoder().decode(base64); 
        return new String(decoded, StandardCharsets.UTF_8);
    }

    public void saveGmlFile(String decoded, String path) throws IOException{
        Files.write( Paths.get(path), decoded.getBytes());
    }

    public org.w3c.dom.Document readGmlFile(String filePath) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {            
            builder = factory.newDocumentBuilder();
            InputSource source = new InputSource(filePath);
            source.setEncoding(StandardCharsets.UTF_8.displayName());
            return builder.parse(source);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }    

    public JSONObject parseGMLtoJSON(org.w3c.dom.Document doc)throws Exception {
        doc.getDocumentElement().normalize();
        org.w3c.dom.NodeList docNodList = doc.getChildNodes();
        JSONObject nwJSONObject = new JSONObject();
        JSONObject nwDatasetObject = new JSONObject();
        JSONObject nwPreambleObject = new JSONObject();
        JSONObject nwPartObject = new JSONObject(); 
        JSONObject nwGeomObject = new JSONObject();       

		for (int i = 0; i < docNodList.getLength(); i++) {
			Node docNode = docNodList.item(i);
			HashMap<String, String> docNodAtts = getDocNodeAtts(docNode);

			if (docNodAtts.size() != 0) {
				NodeList fistNodeList = docNodList.item(i).getChildNodes();

				for (int j = 0; j < fistNodeList.getLength(); j++) {
					Node memNode = fistNodeList.item(j);
                    
					ArrayList<Map> memNodAtts = getMemNodeAtts(memNode);

					if (!memNodAtts.isEmpty()) {		
                        String key = "";
                        String value = "";			
                        
						for (int k=0; k< memNodAtts.size(); k++){
							key = memNodAtts.get(k).getKey();
							value = memNodAtts.get(k).getValue();

                                logger.info(key + ": " + value);
                                
                                if (key.contains("datasetFileIdentifier")){
                                    nwDatasetObject.put("datasetFileIdentifier", value);
                                }                                                                    
                                if (key.contains("datasetTitle")){
                                    nwDatasetObject.put("datasetTitle", value); 
                                }                                                                   
                                if (key.contains("datasetReferenceDate")){
                                    nwDatasetObject.put("datasetReferenceDate", value);                                
                                }                                   
                                if (key.contains("datasetLanguage")){
                                    nwDatasetObject.put("datasetLanguage", value);   
                                }                                                                 
                                if (key.contains("datasetAbstract")){
                                    nwDatasetObject.put("datasetAbstract", value);
                                }                                    
                                if (key.contains("datasetTopicCategory")){
                                    nwDatasetObject.put("datasetTopicCategory", value);
                                }                                    
                                if (key.contains("datasetPurpose")){
                                    nwDatasetObject.put("datasetPurpose", value);
                                }                                    
                                if (key.contains("updateNumber")){
                                    nwDatasetObject.put("updateNumber", value);
                                }                                    
                                if (key.contains("id")){
                                    nwPreambleObject.put("navwarnpreambleId", value);
                                }                                    
                                if (key.contains("agencyResponsibleForProduction")){
                                    nwPreambleObject.put("agencyResponsibleForProduction", value);
                                }                                    
                                if (key.contains("countryName")){
                                    nwPreambleObject.put("countryName", value);
                                }                                    
                                if (key.contains("nameOfSeries")){
                                    nwPreambleObject.put("nameOfSeries", value);
                                }                                    
                                if (key.contains("warningIdentifier")){
                                    nwPreambleObject.put("warningIdentifier", value);
                                }                                    
                                if (key.contains("warningNumber")){
                                    nwPreambleObject.put("warningNumber", value);
                                }                                    
                                if (key.contains("warningType")){
                                    nwPreambleObject.put("warningType", value);
                                }                                    
                                if (key.contains("year")){
                                    nwPreambleObject.put("year", value);
                                }                                    
                                if (key.contains("language")){
                                    nwPreambleObject.put("generalAreaLanguage", value);
                                }                                    
                                if (key.contains("text")){
                                    nwPreambleObject.put("generalAreaName", value);
                                }                                    
                                if (key.contains("language")){
                                    nwPreambleObject.put("locationNameLanguage", value);
                                }                                    
                                if (key.contains("text")){
                                    nwPreambleObject.put("locationName", value);
                                }                                    
                                if (key.contains("chartNumber")){
                                    nwPreambleObject.put("chartNumber", value);
                                }                                    
                                if (key.contains("editionDate")){
                                    nwPreambleObject.put("editionDate", value);
                                }                                    
                                if (key.contains("language")){
                                    nwPreambleObject.put("language", value);
                                }                                    
                                if (key.contains("publicationAffected")){
                                    nwPreambleObject.put("publicationAffected", value);
                                }
                                if (key.contains("language")){
                                    nwPreambleObject.put("titleLanguage", value);
                                }                                    
                                if (key.contains("text")){
                                    nwPreambleObject.put("title", value);
                                }                                    
                                if (key.contains("publicationTime")){
                                    nwPreambleObject.put("publicationDate", value + "Z");
                                }                                    
                                if (key.contains("navwarnTypeGeneral")){
                                    nwPreambleObject.put("navwarnTypeGeneral", value);
                                }                                    
                                if (key.contains("intService")){
                                    nwPreambleObject.put("intService", value);
                                }
                                nwPreambleObject.put("cancellationDate", "");
                                if (key.contains("header")){
                                    nwPartObject.put("header", value);
                                }                                    
                                if (key.contains("Id")){
                                    nwPartObject.put("navwarnpartId", value);
                                }                                    
                                if (key.contains("date")){
                                    nwPartObject.put("dateStart", value);
                                }
                                nwPartObject.put("informationLanguage", "eng");                                    
                                if (key.contains("text")){
                                    nwPartObject.put("information", value);
                                }                                    
                                if (key.contains("navwarnTypeDetails")){
                                    nwPartObject.put("navwarnTypeDetails", value);
                                }                                    
                                if (key.contains("Id")){
                                    nwGeomObject.put("geometryId", value);
                                }                                    
                                if (key.equals("S100:pointProperty")){
                                    nwGeomObject.put("geometryType", "Point");
                                }                                    
                                if (key.equals("S100:curveProperty")){
                                    nwGeomObject.put("geometryType", "Line");
                                }                                    
                                if (key.equals("S100:surfaceProperty")){
                                    nwGeomObject.put("geometryType", "Polygon");  
                                }
                                nwGeomObject.put("srsName", "urn:ogc:def:crs:EPSG::4326");
                                nwGeomObject.put("srsDimension", "2"); 
                                if (key.equals("gml:posList") || key.equals("gml:pos")){
                                    nwGeomObject.put("coordinates", value);
                                }                              
                        }
                    }
                }
            }
        }
    
        nwJSONObject.put("S124:Dataset", nwDatasetObject);        
        nwJSONObject.put("S124:NAVWARNPreamble", nwPreambleObject); 
        nwJSONObject.put("S124:NAVWARNPart", nwPartObject); 
        nwJSONObject.put("S124:geometry", nwGeomObject); 
        return nwJSONObject;
    }	

    private HashMap<String, String> getDocNodeAtts(Node node) {
		HashMap<String, String> nodeAttributes = new HashMap<>();

		try {
			if (node.getNodeType() == 1) {
				nodeAttributes.put("datasettype", node.getNodeName());
				nodeAttributes.put("id", node.getAttributes().getNamedItem("gml:id").getTextContent());
			}
		} catch (Exception e) {
			logger.info("Id not available for this node");
		}

		return nodeAttributes;
	}

    private ArrayList<Map> getMemNodeAtts(Node node) {		
        ArrayList<Map> nodeAttributes1 = new ArrayList<>();
		NodeList subNodeList = node.getChildNodes();

		try {
            Map map = new Map();
            map.setKey("id");
            map.setValue(subNodeList.item(1).getAttributes().getNamedItem("gml:id").getTextContent());
            nodeAttributes1.add(map);
		} catch (Exception e) {
			logger.info("id not available for this node");

		}
		nodeToHashmap(subNodeList, nodeAttributes1);
		return nodeAttributes1;
	}

	private void nodeToHashmap(NodeList nodeList, ArrayList<Map> nodeAttributes) {
        
		for (int i = 0; i < nodeList.getLength(); i++) {            
			if (nodeList.item(i).getNodeType() == 1) {                
                Map map = new Map();
                map.setKey(nodeList.item(i).getNodeName());                    
                if (nodeList.item(i).getTextContent().contains("    ")){
                    map.setValue("");
                } else{
                    map.setValue(nodeList.item(i).getTextContent());
                }
                nodeAttributes.add(map);
			}

			if (nodeList.item(i).hasChildNodes()) {
				NodeList subNodeList = nodeList.item(i).getChildNodes();
				nodeToHashmap(subNodeList, nodeAttributes);
			}
		}
	}

    public void saveJSONFile(JSONObject jsonObject, String filePath){        
        try {            
            FileWriter file = new FileWriter(filePath);
            file.write(jsonObject.toString(1));
            file.close();
        } catch (Exception e) {
            logger.info("Failed to create JSON");
        }
    }
}

