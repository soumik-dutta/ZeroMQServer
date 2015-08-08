import java.util.Random;
import org.zeromq.*;

//
//  Weather update server in Java
//  Binds PUB socket to tcp://*:5556
//  Publishes random weather updates
//

/*
	java -Djava.library.path=/usr/local/lib -classpath /usr/local/share/java/zmq.jar
	:../src/zmq.jar:ZeroServer.jar:/home/soumik/Projects/jars/jackson-all-1.9.9.jar:
	/home/soumik/Projects/jars/mongo-java-driver-2.13.2.jar Test tcp://127.0.0.1:5555 1 100
*/


public class PubSubZMQServer {

    public static void main (String[] args) throws Exception {
//    	System.loadLibrary("libzmq");
        //  Prepare our context and publisher
        ZMQ.Context context = ZMQ.context(1);

        ZMQ.Socket publisher = context.socket(ZMQ.PUB);
        publisher.bind("tcp://*:5556");
        publisher.bind("ipc://weather");

        //  Initialize random number generator
        Random srandom = new Random(System.currentTimeMillis());
        while (!Thread.currentThread ().isInterrupted ()) {
            //  Get values that will fool the boss
            int zipcode, temperature, relhumidity;
            zipcode = 10000 + srandom.nextInt(10000) ;
            temperature = srandom.nextInt(215) - 80 + 1;
            relhumidity = srandom.nextInt(50) + 10 + 1;

            //  Send message to all subscribers
            String update = String.format("%05d %d %d", zipcode, temperature, relhumidity);
            publisher.send(update, 0);
        }

        publisher.close ();
        context.term ();
    }
}
