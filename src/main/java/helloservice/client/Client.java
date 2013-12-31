package helloservice.client;

import helloservice.Hello;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
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
        long startMillis = System.currentTimeMillis();
        url = new URL("http://3570k:9000/SoapContext/SoapPort?wsdl");
        qname = new QName("http://endpoint.helloservice/", "HelloService");
        service = Service.create(url, qname);
        client = service.getPort(Hello.class);
        long durationMillis = System.currentTimeMillis() - startMillis;
        System.out.printf("%5dms: ws-client created.\n", durationMillis);
    }

    public String go() {
        String result = (Math.random() < 0.95) ? client.sayHello("Thomas") : client.sayBye("Thomas");
        return result;
    }

    public static void main(String args[]) throws Exception {

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread( new Runnable() {
                @Override
                public void run() {
                    Client client = null;
                    try {
                        client = new Client();
                        for (int i = 10; i > 0; i--) {
                            long startMillis = System.currentTimeMillis();
                            String result = client.go();
                            long durationMillis = System.currentTimeMillis() - startMillis;
                            System.out.printf("%02d %5dms: %s\n", i, durationMillis, result);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
           t.start();
        }
    }

}
