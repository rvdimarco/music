package it.geek.resid.model;

public class Cittadino {

	private String codiceFiscale;
	private String nominativo;
	private String impiego;
	private String residenza;
	private Integer codCitta;
	
	public String getResidenza() {
		return residenza;
	}
	public void setResidenza(String residenza) {
		this.residenza = residenza;
	}
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getNominativo() {
		return nominativo;
	}
	public void setNominativo(String nominativo) {
		this.nominativo = nominativo;
	}
	public String getImpiego() {
		return impiego;
	}
	public void setImpiego(String impiego) {
		this.impiego = impiego;
	}
	@Override
	public String toString() {
		return "Cittadino [codiceFiscale=" + codiceFiscale + ", nominativo="
				+ nominativo + ", impiego=" + impiego + ", codCitta=" + codCitta + ", residenza="
				+ residenza + "]";
	}
	public Integer getCodCitta() {
		return codCitta;
	}
	public void setCodCitta(Integer codCitta) {
		this.codCitta = codCitta;
	}
	
}
