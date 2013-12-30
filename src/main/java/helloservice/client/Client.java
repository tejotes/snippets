package helloservice.client;

import helloservice.Hello;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

/**
 * Created by Thomas on 29.12.13.
 */
public class Client {

    URL url = null;
    QName qname = null;
    Service service = null;
    Hello client = null;

    public Client() throws Exception {
        url = new URL("http://3570k:9000/SoapContext/SoapPort?wsdl");
        qname = new QName("http://endpoint.helloservice/", "HelloService");
        service = Service.create(url, qname);
        client = service.getPort(Hello.class);
    }

    public String go() {
        return client.sayHello("Thomas");
    }


    public static void main(String args[]) throws Exception {

        Client client = new Client();

        for (int i = 0; i < 10; i++) {
            long startMillis = System.currentTimeMillis();
            String result = client.go();
            long durationMillis = System.currentTimeMillis() - startMillis;
            System.out.printf("%05d %5dms: %s\n", i, durationMillis, result);
        }
    }

}
