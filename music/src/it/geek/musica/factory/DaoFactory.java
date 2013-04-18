package it.geek.musica.factory;

import it.geek.musica.dao.impl.UtenteDAO;
import it.geek.musica.dao.impl.AutoreDAO;
import it.geek.musica.dao.impl.CasaDiscograficaDAO;

public class DaoFactory {

	public static UtenteDAO getUtenteDAO(){
		return new UtenteDAO();
	}
	
	public static AutoreDAO getAutoreDAO(){
		return new AutoreDAO();
	}
	
	public static CasaDiscograficaDAO getCasaDiscograficaDAO(){
		return new CasaDiscograficaDAO();
	}
	
}
