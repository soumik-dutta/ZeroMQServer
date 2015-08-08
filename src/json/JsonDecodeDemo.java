package json;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonDecodeDemo 
{
	 Map json;
   public Map decodeJSON(String string)
   {
      JSONParser parser=new JSONParser();
//      String s = "[0,{\"1\":{\"2\":{\"3\":{\"4\":[5,{\"6\":7}]}}}}]";
//      String s="{\"username\":\"soumik\",\"password\":\"root\"}";
      ContainerFactory containerFactory = new ContainerFactory(){
        public List creatArrayContainer() {
          return new LinkedList();
        }

        public Map createObjectContainer() {
          return new LinkedHashMap();
        }
                            
      };

      try{
    	   json = (Map)parser.parse(string, containerFactory);
    	   
    	  }
    	  catch(ParseException pe){
    	    System.out.println(pe);
    	  }
	return json;
      
   }
}