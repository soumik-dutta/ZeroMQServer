package controller;

import jacksonJson.JsonDecode;
import jacksonJson.User;

import java.util.StringTokenizer;

import javax.xml.ws.soap.AddressingFeature.Responses;

import mongoConnect.MongoSelect;

/**
 * <p>This is  request response model controller which is responsible 
 * for controlling the flow to which model to initialize</p>
 * @author soumik
 *
 */
public class ReqResController {
	private String request;

	public void setRequest(String request) {
		this.request = request;
	}

 
    /**
     * <p>This is the main controller that is responsible to do all the logical proccessing</p>
     * @return responseString <p> ResponseString that is to be delivered to the client</p>
     */
	public String mainController(){
		String getRequestedClassString=getRequestedClass(request);
		String responseString=null;
		switch(getRequestedClassString){
			case "Auth":{
				//creating the user objeect
				User user=new User();
				//creating the serializing object from the input JSON
				User json=new JsonDecode().decodeJSON(request, user);
				
				String username=json.getName();
	            String password=json.getPassword();
	            
	            MongoSelect mongoSelect=new MongoSelect();
	            Boolean status=mongoSelect.authenticate(username, password);
	            
	            if(status){
	            	responseString="Authentication Successful";
	            }else{
	            	responseString="Something went wrong";
	            }
	            
				return responseString;
			}
			default:{
				return responseString;
			}
		}		
	}
	
	
	
	/**
	 * <p> The method is responsible for taking the request json string and returning the 
	 * string which will identify the class name </p>
	 * @param request <p>request is the json string which was send from the client</p>
	 * @return requestedClass <p>the extracted string by which Class that is to be loaded is identified.</p>
	 */
	private String getRequestedClass(String request){
		String requestedClass=null;
		 StringTokenizer tokenizer=new StringTokenizer(request, "\"");
         int i=0;
   
         while(tokenizer.hasMoreTokens()){
         	tokenizer.nextToken();
         	if(i++==2){
         		requestedClass=tokenizer.nextToken();
         		break;
         	}
         }
		return requestedClass;
	}
}
