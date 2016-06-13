package it.uniroma3.agiw.ThirdProject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import javax.xml.parsers.ParserConfigurationException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class App {
	
	private static String listaIngegneriUrl_path = "../ThirdProject/src/main/resources/listaQuery";
	private static String cartellaDestinazione_path = "C:\\javacomelava3";
	
    @SuppressWarnings("unchecked")
	public static void main( String[] args ) throws IOException, ParserConfigurationException {
    	
		BufferedWriter log_error; //log file degli errori
		
    	@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(listaIngegneriUrl_path));
    	String current_line;
    	int progressive_num = 0;
    	
    	while((current_line = br.readLine()) != null){
    		
    		String[] a = current_line.split("\t");  //a[0]= nominativo ----  a[2]=url
    		String directory_path = cartellaDestinazione_path + "\\" + a[0].replace(" ","_");
    		String url = a[2];
    		
    		try{
    	
    		File directory = new File(directory_path);
    		
    		if (!directory.exists())
    			directory.mkdir();
    	
	        EntitiesExtraction ee = new EntitiesExtraction();
	        JSONObject personResultJson = new JSONObject();
	        System.out.println(url);
	        JSONObject obj = (JSONObject) JSONValue.parse(ee.doExtraction(url).toString());
	        JSONArray entitiesObj = (JSONArray) obj.get("entities");
	        personResultJson.put("url", url);
	        
	        JSONObject nerJson = new JSONObject();
	        List<String> type_visti = new ArrayList<String>();
	        
	      /*JSONArray perValueArray = new JSONArray();
	    	JSONArray orgValueArray = new JSONArray();
	    	JSONArray locValueArray = new JSONArray();*/
	
	        for(Object ent1 : entitiesObj){
	        	JSONObject entityObj1 = (JSONObject) ent1;
    			JSONArray nerValueArray = new JSONArray();
	        	String type = (String) entityObj1.get("type");
	        	
	        	if(!type_visti.contains(type)){
	        		for(Object ent2 : entitiesObj){
	        			JSONObject entityObj2 = (JSONObject) ent2;
	        			if(entityObj2.get("type").equals(type)){
	        				nerValueArray.add(entityObj2.get("text"));
	        			}
	        		}
		        	nerJson.put(type, nerValueArray);
		        	type_visti.add(type);

	        	}
	        	
	        	
		              /*if(entityObj.get("type").equals("Person"))
		        			perValueArray.add(entityObj.get("text"));
		            	else if(entityObj.get("type").equals("Organization"))
		        			orgValueArray.add(entityObj.get("text"));
		            	else if(entityObj.get("type").equals("City")||entityObj.get("type").equals("Country"))
		        			locValueArray.add(entityObj.get("text"));*/
	
	    	}
	        /*nerJson.put("PER",perValueArray);
	    	nerJson.put("ORG",orgValueArray);
	    	nerJson.put("LOC",locValueArray);*/
	        
	        personResultJson.put("NER", nerJson);
	        System.out.println(personResultJson);

	        
	    	//inizio parte Regex

			RegexExtraction re = new RegexExtraction();
	        JSONObject regJson = new JSONObject();

	        Document doc = Jsoup.connect(url).get();
			String html	= doc.html();
			
			List<String> email_output = re.doEmailExtraction(html);
			List<String> telephone_output = re.doTelephoneExtraction(html);
			JSONArray emailValueArray = new JSONArray();
			JSONArray telephoneValueArray = new JSONArray();
			
			for(int i = 0; i<email_output.size(); i++){
				
				emailValueArray.add(email_output.get(i).toString());
			
			}
			
			for(int i = 0; i<telephone_output.size(); i++){
				
				telephoneValueArray.add(telephone_output.get(i).toString());
			
			}
			
	        regJson.put("email", emailValueArray);
	        regJson.put("telephone", telephoneValueArray);
	        
	        personResultJson.put("PATTERN", regJson);

	        System.out.println(personResultJson);

	        FileWriter file = new FileWriter(directory_path + "\\" + progressive_num + ".json");
	        file.write(personResultJson.toJSONString());
	        file.flush();
	        file.close();
	        progressive_num++;
	        
    		}catch(Exception e){
    			e.printStackTrace();
    			log_error = new BufferedWriter(new FileWriter(cartellaDestinazione_path + "\\errorLog.txt",true));
    			log_error.newLine();
    			log_error.append(current_line);
    			log_error.close();
    		}
    		
    		
        }
        
    }
   
}
