package it.geek.resid.service;

import java.util.List;

import it.geek.resid.model.Ruolo;
import it.geek.resid.model.Utente;

public interface UtenteServiceInterface {

	public Utente get(String k);
	public List<Utente> getAll();
	public void multiSave(String[] ids, Ruolo ruolo);
	public void create(Utente u);
}
