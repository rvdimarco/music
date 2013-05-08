package it.geek.resid.sp.form;

import it.geek.resid.sp.pojo.Ruolo;
import it.geek.resid.sp.util.Collezioni;

import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.util.LabelValueBean;

public class UtenteForm extends ActionForm {

	private String method;
	
	private String username;
	private String password;
	private String confirmPassword;
	private String nome;
	private String cognome;
	private String dataNascita;
	private String dataRegistrazione;
	private Ruolo ruolo = new Ruolo();
	private List<LabelValueBean> listaRuoli;
	
	public List<LabelValueBean> getListaRuoli() {
		listaRuoli = Collezioni.getOptions("ruoli");
		return listaRuoli;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

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

	public String getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getDataRegistrazione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazione(String dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
