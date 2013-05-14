package it.geek.resid.dao;

import it.geek.resid.model.InfoRegione;

import java.sql.Connection;
import java.util.List;

public interface InfoRegioneDaoInterface {

	public List<InfoRegione> findAll(Connection c);
	
}
