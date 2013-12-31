package helloservice.endpoint;

import javax.xml.ws.Endpoint;
import java.util.Date;
import java.util.concurrent.Executor;

/**
 * Created by Thomas on 29.12.13.
 */
public class Server {

    Endpoint endpoint;

    protected Server() throws Exception {
        System.out.println("Starting Server");
        Object implementor = new HelloImpl();
        String address = "http://0.0.0.0:9000/SoapContext/SoapPort";
        endpoint = Endpoint.publish(address, implementor);
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        System.out.println((new Date())+": Server started...");

        while (true) {
            Thread.sleep(60000L);
            System.out.println((new Date())+": Server is still up.");
        }
    }
}
