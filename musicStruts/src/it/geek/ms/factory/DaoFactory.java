package it.geek.ms.factory;

import it.geek.ms.dao.impl.RuoloDAO;
import it.geek.ms.dao.impl.UtenteDAO;
import it.geek.ms.dao.impl.AutoreDAO;
import it.geek.ms.dao.impl.CasaDiscograficaDAO;

public class DaoFactory {

	public static UtenteDAO getUtenteDAO(){
		return new UtenteDAO();
	}
	
	public static RuoloDAO getRuoloDAO(){
		return new RuoloDAO();
	}
	
	public static AutoreDAO getAutoreDAO(){
		return new AutoreDAO();
	}
	
	public static CasaDiscograficaDAO getCasaDiscograficaDAO(){
		return new CasaDiscograficaDAO();
	}
	
}
