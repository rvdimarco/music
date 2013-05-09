package it.geek.resid.sp.dao;

import it.geek.resid.sp.pojo.Utente;

import java.sql.Connection;

public interface UtenteDAO {

	public Utente findById(String id, Connection c);
	
}
