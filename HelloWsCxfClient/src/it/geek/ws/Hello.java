package it.geek.ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.5
 * 2013-06-06T13:10:52.946+02:00
 * Generated source version: 2.7.5
 * 
 */
@WebServiceClient(name = "Hello", 
                  wsdlLocation = "file:/C:/Users/Valerio/Documents/GitHub/music/HelloWsCxfClient/src/hello.wsdl",
                  targetNamespace = "http://ws.geek.it/") 
public class Hello extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://ws.geek.it/", "Hello");
    public final static QName HelloPort = new QName("http://ws.geek.it/", "HelloPort");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/Users/Valerio/Documents/GitHub/music/HelloWsCxfClient/src/hello.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(Hello.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/C:/Users/Valerio/Documents/GitHub/music/HelloWsCxfClient/src/hello.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public Hello(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public Hello(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Hello() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public Hello(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public Hello(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public Hello(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns HelloInterface
     */
    @WebEndpoint(name = "HelloPort")
    public HelloInterface getHelloPort() {
        return super.getPort(HelloPort, HelloInterface.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns HelloInterface
     */
    @WebEndpoint(name = "HelloPort")
    public HelloInterface getHelloPort(WebServiceFeature... features) {
        return super.getPort(HelloPort, HelloInterface.class, features);
    }

}
