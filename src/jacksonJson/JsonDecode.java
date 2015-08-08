package jacksonJson;

import java.io.IOException;


import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

/**
 * <p></p>
 * @author soumik
 *
 */
public class JsonDecode {
	/**
	 * <p>This will decode the JSON-String to JSON-Object</p>
	 * @return serialized object of the JSON
	 * @param  jsonString,Serialized Object class
	 * @author soumik 
	 */
   public <T extends Object> T decodeJSON(String jsonString,T t){
	   ObjectMapper mapper=new ObjectMapper();
	   /*
	    * map json to Class T
	    */
	   try{
//		   t = mapper.readValue(jsonString, className.class);
		   t=(T) mapper.readValue(jsonString,t.getClass());
	         System.out.println(t);
	         mapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
	         jsonString = mapper.writeValueAsString(t);
	         System.out.println(jsonString);
	   }
	   catch(JsonParseException e){
		   e.printStackTrace();
	   } catch (JsonMappingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	   return t;
   }
}
