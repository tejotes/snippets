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

    public Client(String hostname) throws Exception {
        long startMillis = System.currentTimeMillis();
        url = new URL(String.format("http://%s:9000/SoapContext/SoapPort?wsdl",hostname));
        qname = new QName("http://endpoint.helloservice/", "HelloService");
        service = Service.create(url, qname);
        client = service.getPort(Hello.class);
        long durationMillis = System.currentTimeMillis() - startMillis;
        System.out.printf("%5dms: ws-client created.\n", durationMillis);
    }

    public Client() throws Exception {
        this("localhost");
    }

    public String go() {
        String result = (Math.random() < 0.1) ? client.sayHello("Thomas") : client.sayBye("Thomas");
        return result;
    }

    public static void main(String args[]) throws Exception {

        final String hostname = (args.length > 0) ? args[0] : "localhost";

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread( new Runnable() {
                @Override
                public void run() {
                    Client client = null;
                    try {
                        client = new Client(hostname);
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
