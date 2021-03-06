package it.geek.resid.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utente {
	private String username;
	private String password;
	private String nome;
	private String cognome;
	private Date dataNascita;
	private Ruolo ruolo = new Ruolo();;
	private Date dataRegistrazione;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getDataNascitaFormatted() {
		String dateToFormattedString = "";
		if(dataNascita!=null){
			SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
			dateToFormattedString = sd.format(dataNascita);
		}
		return dateToFormattedString;
	}
	public Date getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}
	public Ruolo getRuolo() {
		return ruolo;
	}
	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}
	public String getDataRegistrazioneFormatted(){
		String dateToFormattedString = "";
		if(dataRegistrazione!=null){
			SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			dateToFormattedString = sd.format(dataRegistrazione);
		}
		return dateToFormattedString;
	}
	public Date getDataRegistrazione() {
		return dataRegistrazione;
	}
	public void setDataRegistrazione(Date dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utente other = (Utente) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Utente [username=" + username + ", password=" + password
				+ ", nome=" + nome + ", cognome=" + cognome + ", dataNascita="
				+ dataNascita + ", ruolo=" + ruolo + ", dataRegistrazione="
				+ dataRegistrazione + "]";
	}
}
