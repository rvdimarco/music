package it.geek.pht.service;

import it.geek.pht.util.Constants;

public class ServiceFactory implements Constants{

	public static Service getService(String customService){
		Service service = null;
		
		switch (customService) {
		case PERSONA:
			service = new PersonaService();
			break;
			
		case UTENTE:
			service = new UtenteService();
			break;
			
		case LIBRO:
			service = new LibroService();
			break;

		default:
			break;
		};
		
		return service;
	}
}
