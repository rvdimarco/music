package it.geek.resid.sp.test;

import it.geek.resid.sp.pojo.Utente;
import it.geek.resid.sp.service.ServiceFactory;

public class SpringTest {

	public static void main(String[] args) {

		String username = "condor";
		Utente u = ServiceFactory.getUtenteService().get(username);
		System.out.println();
		System.out.println(u);
		
	}

}
