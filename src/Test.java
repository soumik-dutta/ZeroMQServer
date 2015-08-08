import jacksonJson.JsonClass;
import jacksonJson.JsonDecode;
import jacksonJson.User;

import java.util.Map;
import java.util.StringTokenizer;

import mongoConnect.MongoSelect;
/**
 * @author soumik
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonDecode jsonDecode=new JsonDecode();
		String request="{\"classType\":\"Auth\",\"name\":\"soumik\",\"password\":\"root\"}";
		
		 StringTokenizer tokenizer=new StringTokenizer(request, "\"");
         int i=0;
         String typeOfRequest = null;
         while(tokenizer.hasMoreTokens()){
         	tokenizer.nextToken();
         	if(i++==2){
         		typeOfRequest=tokenizer.nextToken();
         		break;
         	}
         }
         System.out.println(typeOfRequest);
         
         //converting string to JSON
         Object jsonClass=new JsonClass().getJsonClass(typeOfRequest);  	   		
 		//JsonClass json=(jsonClass.getClass())jsonDecode.decodeJSON(request,jsonClass);
         
         Object json=jsonDecode.decodeJSON(request, jsonClass);
         System.out.println(json.getClass());
         //System.out.println(((Object) json).getUsername());
        
        
        /*System.out.println(username);
        MongoSelect mongoSelect=new MongoSelect();
        Boolean status=mongoSelect.authenticate(username, password);
        System.out.println(status);*/
	}

}
