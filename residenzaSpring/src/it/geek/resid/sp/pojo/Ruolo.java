package it.geek.resid.sp.pojo;

public class Ruolo {

	private Integer codice;
	private String descrizione;
	private boolean attivo;
	
	public Integer getCodice() {
		return codice;
	}
	public void setCodice(Integer codice) {
		this.codice = codice;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public boolean isAttivo() {
		return attivo;
	}
	public void setAttivo(boolean attivo) {
		this.attivo = attivo;
	}
	@Override
	public String toString() {
		return "Ruolo [codice=" + codice + ", descrizione=" + descrizione
				+ ", attivo=" + attivo + "]";
	}
	
}
