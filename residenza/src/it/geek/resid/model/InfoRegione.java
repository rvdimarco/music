package it.geek.resid.model;

public class InfoRegione {

	private String regione;
	private Integer numAbitanti;
	private Integer numResidenti;
	
	public String getRegione() {
		return regione;
	}
	public void setRegione(String regione) {
		this.regione = regione;
	}
	public Integer getNumAbitanti() {
		return numAbitanti;
	}
	public void setNumAbitanti(Integer numAbitanti) {
		this.numAbitanti = numAbitanti;
	}
	public Integer getNumResidenti() {
		return numResidenti;
	}
	public void setNumResidenti(Integer numResidenti) {
		this.numResidenti = numResidenti;
	}
	@Override
	public String toString() {
		return "InfoRegione [regione=" + regione + ", numAbitanti="
				+ numAbitanti + ", numResidenti=" + numResidenti + "]";
	}
	
	
	
}
