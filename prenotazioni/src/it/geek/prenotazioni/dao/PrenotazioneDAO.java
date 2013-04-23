package it.geek.prenotazioni.dao;

import it.geek.prenotazioni.model.Prenotazione;

import java.util.List;

import org.apache.log4j.Logger;

public class PrenotazioneDAO {

	private static Logger log = Logger.getLogger(PrenotazioneDAO.class);
	
	public List<Prenotazione> findByMatricolaStudente(String matricola){
		List<Prenotazione> ritorno = null;
		
		//...
		
		return ritorno;
	}
	
	public void insert(Prenotazione p){
		
		//...
		
	}
	
	public void delete(String id){
		
		//...
		
	}
	
}
