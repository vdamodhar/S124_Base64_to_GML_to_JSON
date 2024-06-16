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
import org.w3c.dom.*;
import java.util.HashMap;
import java.util.concurrent.*;
import java.io.*;

public class util {
    int existedKey = 0;
    public void readTXT() throws Exception{
        String path = "D:\\NC_S100\\Sample-datasets\\S124\\";
        String inTxtFile = "S124_SECOM.txt";
        String content = new String(Files.readAllBytes(Paths.get(path + inTxtFile)));
        JSONObject obj = new JSONObject(content);
        JSONArray arr = obj.getJSONArray("dataResponseObject");       

        for(int i=0; i<arr.length()-1;i++){
            JSONObject obj2 = arr.getJSONObject(i);
            String arr2 = obj2.getString("data");            
            String decoded = base64toString(arr2);            
            saveFile(decoded, path + "sample_" + i + ".gml");
            TimeUnit.SECONDS.sleep(5);
            org.w3c.dom.Document GMLdoc = openGML(path + "sample_" + i + ".gml"); 
            if (GMLdoc != null){
                JSONObject jsonObject = parseGMLtoJSON(GMLdoc);
                saveJSONFile(jsonObject, path + "sample_" + i + ".json");
            }
        } 
    }

    private String base64toString(String base64){
        byte[] decoded = Base64.getDecoder().decode(base64); 
        String strDecoder = new String(decoded, StandardCharsets.UTF_8);
        return strDecoder;
    }

    private void saveFile(String decoded, String path) throws IOException{
        Files.write( Paths.get(path), decoded.getBytes());
    }

    private org.w3c.dom.Document openGML(String filePath) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {            
            builder = factory.newDocumentBuilder();
            InputSource source = new InputSource(filePath);
            source.setEncoding(StandardCharsets.UTF_8.displayName());
            org.w3c.dom.Document doc = builder.parse(source);
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }    

    private JSONObject parseGMLtoJSON(org.w3c.dom.Document doc)throws Exception {
        doc.getDocumentElement().normalize();
        org.w3c.dom.NodeList docNodList = doc.getChildNodes();
        JSONObject nwJSONObject = new JSONObject();
        JSONObject nwDatasetObject = new JSONObject();
        JSONObject nwPreambleObject = new JSONObject();
        JSONObject nwPartObject = new JSONObject();       

		for (int i = 0; i < docNodList.getLength(); i++) {
			Node docNode = docNodList.item(i);
			HashMap<String, String> docNodAtts = getDocNodeAtts(docNode);

			if (docNodAtts.size() != 0) {
				NodeList fistNodeList = docNodList.item(i).getChildNodes();

				for (int j = 0; j < fistNodeList.getLength(); j++) {
					Node memNode = fistNodeList.item(j);
                    
					ArrayList<S124Map> memNodAtts = getMemNodeAtts(memNode);

					if (memNodAtts.size() != 0) {					
                        
						for (int k=0; k< memNodAtts.size(); k++){
							String key = memNodAtts.get(k).getKey();
							String value = memNodAtts.get(k).getValue();

                                System.out.println(key + ": " + value);
                                
                                if (key.contains("datasetFileIdentifier")){
                                    nwDatasetObject.put("datasetFileIdentifier", value);
                                }                                                                    
                                if (key.contains("datasetTitle")){
                                    nwDatasetObject.put("datasetTitle", value); 
                                }                                                                   
                                if (key.contains("datasetReferenceDate")){
                                    nwDatasetObject.put("datasetReferenceDate", value);                                
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
                                    nwPreambleObject.put("id", value);
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
                                    nwPreambleObject.put("publicationDate", value);
                                }                                    
                                if (key.contains("navwarnTypeGeneral")){
                                    nwPreambleObject.put("navwarnTypeGeneral", value);
                                }                                    
                                if (key.contains("intService")){
                                    nwPreambleObject.put("IntService", value);
                                }
                                nwPreambleObject.put("cancellationDate", "");
                                if (key.contains("header")){
                                    nwPartObject.put("header", value);
                                }                                    
                                if (key.contains("Id")){
                                    nwPartObject.put("id", value);
                                }                                    
                                if (key.contains("date")){
                                    nwPartObject.put("dateStart", value);
                                }                                    
                                if (key.contains("text")){
                                    nwPartObject.put("information", value);
                                }                                    
                                if (key.contains("navwarnTypeDetails")){
                                    nwPartObject.put("navwarnTypeDetails", value);
                                }                                    
                                if (key.contains("Id")){
                                    nwPartObject.put("geometryId", value);
                                }                                    
                                if (key == "S100:pointProperty"){
                                    nwPartObject.put("geometryType", "Point");
                                }                                    
                                if (key == "S100:curveProperty"){
                                    nwPartObject.put("geometryType", "Line");
                                }                                    
                                if (key == "S100:surfaceProperty"){
                                    nwPartObject.put("geometryType", "Polygon");  
                                }
                                nwPartObject.put("srsName", "urn:ogc:def:crs:EPSG::4326");
                                nwPartObject.put("srsDimension", "2"); 
                                if (key == "gml:posList" || key == "gml:pos"){
                                    nwPartObject.put("coordinates", value);
                                }                              
                        }
                    }
                }
            }
        }
    
        nwJSONObject.put("S124:Dataset", nwDatasetObject);        
        nwJSONObject.put("S124:NAVWARNPreamble", nwPreambleObject); 
        nwJSONObject.put("S124:NAVWARNPart", nwPartObject); 
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
			System.out.println("id not available for this node");
		}

		return nodeAttributes;
	}

    private ArrayList<S124Map> getMemNodeAtts(Node node) {		
        ArrayList<S124Map> nodeAttributes1 = new ArrayList<>();
		NodeList subNodeList = node.getChildNodes();

		try {
            S124Map map = new S124Map();
            map.setKey("id");
            map.setValue(subNodeList.item(1).getAttributes().getNamedItem("gml:id").getTextContent());
            nodeAttributes1.add(map);
		} catch (Exception e) {
			System.out.println("id not available for this node");

		}
		nodeToHashmap(subNodeList, nodeAttributes1);
		return nodeAttributes1;
	}

	private void nodeToHashmap(NodeList nodeList, ArrayList<S124Map> nodeAttributes) {
        
		for (int i = 0; i < nodeList.getLength(); i++) {            
			if (nodeList.item(i).getNodeType() == 1) {
                System.out.println(nodeList.item(i).getTextContent());
                S124Map map = new S124Map();
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

    private void saveJSONFile(JSONObject jsonObject, String filePath){
        try {
            FileWriter file = new FileWriter(filePath);
            file.write(jsonObject.toString(1));
            file.close();
        } catch (Exception e) {
            System.out.println("Failed to create JSON");
        }

    }
}

