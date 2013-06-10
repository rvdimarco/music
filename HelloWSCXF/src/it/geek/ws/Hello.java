package it.geek.ws;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(endpointInterface = "it.geek.ws.HelloInterface",
serviceName = "Hello")
public class Hello implements HelloInterface {

	@Override
	public String sayHello(@WebParam(name = "nome") String nome) {
		// TODO Auto-generated method stub
		return "Benvenuto "+nome+"... hai fatto un buon uso di apache CXF!";
	}

}
