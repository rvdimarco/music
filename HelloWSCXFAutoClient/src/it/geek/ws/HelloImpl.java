
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
 * 2013-06-11T10:26:29.974+02:00
 * Generated source version: 2.7.5
 * 
 */

@javax.jws.WebService(
                      serviceName = "HelloService",
                      portName = "HelloPort",
                      targetNamespace = "http://ws.geek.it/",
                      wsdlLocation = "file:/C:/Users/Valerio/Documents/GitHub/music/HelloWSCXFAutoClient/WebContent/wsdl/hello.wsdl",
                      endpointInterface = "it.geek.ws.Hello")
                      
public class HelloImpl implements Hello {

    private static final Logger LOG = Logger.getLogger(HelloImpl.class.getName());

    /* (non-Javadoc)
     * @see it.geek.ws.Hello#sayHello(java.lang.String  arg0 )*
     */
    public java.lang.String sayHello(java.lang.String arg0) { 
        LOG.info("Executing operation sayHello");
        System.out.println(arg0);
        try {
            java.lang.String _return = "_return-817720363";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}