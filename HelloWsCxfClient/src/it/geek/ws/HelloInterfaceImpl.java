
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package it.geek.ws;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.5
 * 2013-06-06T13:10:52.915+02:00
 * Generated source version: 2.7.5
 * 
 */

@javax.jws.WebService(
                      serviceName = "Hello",
                      portName = "HelloPort",
                      targetNamespace = "http://ws.geek.it/",
                      wsdlLocation = "file:/C:/Users/Valerio/Documents/GitHub/music/HelloWsCxfClient/src/hello.wsdl",
                      endpointInterface = "it.geek.ws.HelloInterface")
                      
public class HelloInterfaceImpl implements HelloInterface {

    private static final Logger LOG = Logger.getLogger(HelloInterfaceImpl.class.getName());

    /* (non-Javadoc)
     * @see it.geek.ws.HelloInterface#sayHello(java.lang.String  nome )*
     */
    public java.lang.String sayHello(java.lang.String nome) { 
        LOG.info("Executing operation sayHello");
        System.out.println(nome);
        try {
            java.lang.String _return = "_return729325700";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
