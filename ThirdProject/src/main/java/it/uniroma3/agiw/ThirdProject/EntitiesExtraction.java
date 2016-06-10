package it.uniroma3.agiw.ThirdProject;

import java.util.HashMap;
import java.util.Map;

import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.Entities;

public class EntitiesExtraction {
	
	public EntitiesExtraction(){
	}
	
	 public Entities doExtraction(String url) {
		    AlchemyLanguage service = new AlchemyLanguage();
		    service.setApiKey("***");

		    Map<String, Object> params = new HashMap<String, Object>();
		    params.put(AlchemyLanguage.URL,url);
		    
		    Entities entities = new Entities();
		    
		    try{
		    entities = service.getEntities(params).execute();
		    }catch(Exception e){
		    	System.out.println(e.getMessage());
		    	}
		    //System.out.println("Entities: " + entities);
			return entities;
		    
		  }

}
