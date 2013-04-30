package it.geek.ms.factory;

import it.geek.ms.service.impl.AutoreService;
import it.geek.ms.service.impl.CasaDiscograficaService;
import it.geek.ms.service.impl.RuoloService;
import it.geek.ms.service.impl.UtenteService;

public class ServiceFactory {

	public static UtenteService getUtenteService(){
		return new UtenteService();
	}
	
	public static RuoloService getRuoloService(){
		return new RuoloService();
	}
	
	public static AutoreService getAutoreService(){
		return new AutoreService();
	}
	
	public static CasaDiscograficaService getCasaDiscograficaService(){
		return new CasaDiscograficaService();
	}
	
}
