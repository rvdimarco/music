package it.geek.musica.model;

import java.util.Arrays;

import it.geek.musica.util.Constants;

public class Ruolo extends Entity {

	public int codice;
	protected String descrizione;
	
	private static int[]ruoli = {Constants.RUOLO_AMMINISTRATORE,
								 Constants.RUOLO_STANDARD,
								 Constants.RUOLO_OSPITE};
	
	public int getCodice() {
		return codice;
	}
	public void setCodice(int codice) {
		if(!Arrays.asList(Ruolo.ruoli).contains(codice)){
			this.codice = codice;
		}else{
			throw new IllegalArgumentException("codice ruolo non consentito [ "+codice+" ]");
		}
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codice;
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
		Ruolo other = (Ruolo) obj;
		if (codice != other.codice)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Ruolo [codice=" + codice + ", descrizione=" + descrizione + "]";
	}

}
