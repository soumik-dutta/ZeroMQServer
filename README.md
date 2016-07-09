
# Communicating nodeJS with Java [Cross platform communication using ZMQ]

>First download zeroMQ from [here][link1] and follow the [steps][link2] given this will install the zeroMQ native files in your system.


  - Insatllation of ZMQ in ubuntu System
  - Implementation of Request-response model.
  - Server - java
  - Client - Node.js


#### Installing JZMQ in Ubuntu System
>First download or clone the git repository with this [link][link3] and install the library >given in the documentation.

```sh
$ ./autogen.sh
$ ./configure
$ make
$ make install  
```

### Implementing Request Response model
```sh
java -Djava.library.path=/usr/local/lib -classpath /usr/local/share/java/zmq.jar:../src/zmq.jar:ZeroServer.jar:/home/soumik/Projects/jars/jackson-all-1.9.9.jar:/home/soumik/Projects/jars/mongo-java-driver-2.13.2.jar ReqRespZMQServer tcp://127.0.0.1:5555 1 100
```

### Server-java
First we have to export the java project in .jar format and named as  __ZeroServer.jar__ as there in the command inside that jar there is a .class file called __ReqRespZMQServer.class__ which is executed here.Now server is up and Running.


#### Client-Node
Client is easy to run just start app.js the project is available [ZeroMQ-client][link4]

#### For JSON converting Jackson is used




[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

   [link1]: <http://zeromq.org/area:download>
   [link2]:<http://zeromq.org/area:download#toc6>
   [link3]:<http://zeromq.org/bindings:java>
   [link4]:<https://github.com/soumik-dutta/ZeroMQClient>
   

