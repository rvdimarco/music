package it.prova.hibernate.dao;

public class MyDaoFactory {
	
	public static UtenteDAO getUtenteDAO(){
		return new UtenteDAO();
	}
	
	public static LibroDAO getLibroDAO(){
		return new LibroDAO();
	}

}
