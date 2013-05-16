package it.geek.resid.dao;

import java.sql.Connection;
import java.util.List;

import it.geek.resid.model.Utente;

public interface UtenteDaoInterface {

	public Utente findById(String id);
	public Utente findById(String id, Connection c);
	
	public List<Utente> findAll(Connection c);
	public List<Utente> findAll();
	
	public boolean insert(Utente u, Connection c);
	public boolean insert(Utente u);
	
	public boolean update(Utente u, Connection c);
	public boolean update(Utente u);
	
}
