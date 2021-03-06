package it.geek.ws.server;

import javax.xml.ws.Endpoint;

import it.geek.ws.Hello;

public class Server {

	
    protected Server() throws Exception {
        // START SNIPPET: publish
        System.out.println("Starting Server");
        Hello implementor = new Hello();
        String address = "http://localhost:9000/hello";
        Endpoint.publish(address, implementor);
        // END SNIPPET: publish
    }

    public static void main(String args[]) throws Exception {
        new Server();
        System.out.println("Server ready...");

        Thread.sleep(5 * 60 * 1000);
        System.out.println("Server exiting");
        System.exit(0);
        
    }
    
    
}
