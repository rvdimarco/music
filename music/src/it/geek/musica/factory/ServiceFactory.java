package it.geek.musica.factory;

import it.geek.musica.service.impl.AutoreService;
import it.geek.musica.service.impl.CasaDiscograficaService;
import it.geek.musica.service.impl.UtenteService;

public class ServiceFactory {

	public static UtenteService getUtenteService(){
		return new UtenteService();
	}
	
	public static AutoreService getAutoreService(){
		return new AutoreService();
	}
	
	public static CasaDiscograficaService getCasaDiscograficaService(){
		return new CasaDiscograficaService();
	}
	
}
