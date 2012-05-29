

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package input;

import java.io.FileInputStream;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;

/**
 *
 * @author web038
 */
public class MyWebService {

    public static void callWebService() throws Exception {

        
        URL url = new URL("http://playground.big.tuwien.ac.at:8080/highscore/PublishHighScoreService?wsdl");
        QName serviceName = new QName("urn:PublishHighScoreService", "PublishHighScoreService");
        QName portName = new QName("urn:PublishHighScorePort", "PublishHighScorePort");
        Service service = Service.create(url, serviceName);
        Dispatch<SOAPMessage> dispatch = service.createDispatch(portName, SOAPMessage.class,
                Service.Mode.MESSAGE);

        SOAPMessage request = MessageFactory.newInstance().createMessage();

        SOAPMessage response = dispatch.invoke(request);
        response.writeTo(System.out);
    }
}
