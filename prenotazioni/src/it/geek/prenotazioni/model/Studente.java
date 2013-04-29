package it.geek.prenotazioni.model;

import java.util.ArrayList;
import java.util.List;

public class Studente {

	private String matricola;
	private String nome;
	private String cognome;
	private List<Corso> prenotazioni = new ArrayList<>();
	
	public void addPrenotazione(Corso corso){
		prenotazioni.add(corso);
	}
	
	public List<Corso> getPrenotazioni(){
		return prenotazioni;
	}
	
	public String getMatricola() {
		return matricola;
	}
	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

}
