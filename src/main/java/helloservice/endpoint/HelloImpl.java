package helloservice.endpoint;

import helloservice.Hello;

import javax.jws.WebService;

/**
 * Created by Thomas on 29.12.13.
 */
@WebService(targetNamespace = "http://endpoint.helloservice/", serviceName = "HelloService", name = "HelloPortType", portName = "HelloPort")
public class HelloImpl implements Hello {

    private String message = "Hello, ";

    public String sayHello(String name) {
        System.out.println("thread=" + Thread.currentThread());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return message + name + ".";
    }
}
