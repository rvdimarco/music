package it.geek.ws;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface HelloInterface {
	
    public String sayHello(@WebParam(name="nome") String nome);
    
}
