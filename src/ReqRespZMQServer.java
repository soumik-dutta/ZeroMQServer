


//
//  Hello World server in Java
//  Binds REP socket to tcp://*:5555
//  Expects "Hello" from client, replies with "World"
//

import jacksonJson.JsonClass;
import jacksonJson.JsonDecode;
import jacksonJson.User;

import java.util.StringTokenizer;

import org.zeromq.ZMQ;

public class ReqRespZMQServer {

    public static void main(String[] args) throws Exception {
        ZMQ.Context context = ZMQ.context(1);
        JsonDecode jsonDecode=new JsonDecode();
        //  Socket to talk to clients
        ZMQ.Socket responder = context.socket(ZMQ.REP);
        responder.bind("tcp://*:5555");

        while (!Thread.currentThread().isInterrupted()) {
        	Boolean status=false;
            // Wait for next request from the client
           String request = responder.recvStr();
            System.out.println(request);
            
            /*
             * To extract what type to class to be initialize
             * */
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
            
            
            //converting string to JSON
            JsonClass jsonClass=new JsonClass().getJsonClass(typeOfRequest);  	   		
    		//JsonClass json=(jsonClass.getClass())jsonDecode.decodeJSON(request,jsonClass);
            JsonClass json=jsonDecode.decodeJSON(request, jsonClass);
            String username=json.getName();
            String password=json.getPassword();
            /*
            //mongoDB connection
            MongoSelect mongoSelect=new MongoSelect();
            Boolean status=mongoSelect.authenticate(username, password);*/
            
            // Do some 'work'
            Thread.sleep(1000);

            // Send reply back to client
            String reply ;
            if(status)
            	reply= "Login Successfully";
            else
            	reply="Username Password Invalid";
            responder.send(reply.getBytes(), 0);
        }
        responder.close();
        context.term();
    }
}

