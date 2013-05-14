package it.geek.resid.dao;

import it.geek.resid.model.Cittadino;

import java.sql.Connection;
import java.util.List;

public interface CittadinoDaoInterface {

	public List<Cittadino> findAll(Connection c);
	
	public Cittadino findById(String id, Connection c);
	
	public boolean update(Cittadino ci, Connection c);
	
}
