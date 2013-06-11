package it.geek.ws;

import javax.jws.WebService;

@WebService(targetNamespace = "http://ws.geek.it/", portName = "HelloPort", serviceName = "HelloService")
public class Hello{

	public String sayHello(String nome) {
		// TODO Auto-generated method stub
		return "Benvenuto "+nome+"... hai fatto un buon uso di apache CXF! (con generazone automatica)";
	}

}
