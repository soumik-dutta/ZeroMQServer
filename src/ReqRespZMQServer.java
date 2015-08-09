


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

import controller.ReqResController;

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
            
            ReqResController controller=new ReqResController();
    		controller.setRequest(request);
    		String responseString=controller.mainController();
    		System.out.println(responseString);
    		
            responder.send(responseString.getBytes(), 0);
        }
        responder.close();
        context.term();
    }
}

