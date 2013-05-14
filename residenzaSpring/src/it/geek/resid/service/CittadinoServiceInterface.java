package it.geek.resid.service;

import it.geek.resid.model.Cittadino;

import java.util.List;

public interface CittadinoServiceInterface {

	public List<Cittadino> getAll();
	
	public void save(Cittadino c);
	
}
