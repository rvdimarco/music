package it.geek.ms.form;

import it.geek.ms.model.Autore;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class GestCasaDiscograficaForm extends ActionForm {

	private String nome;
	private String operazione;
	private String idAutore;
	private List<Autore> autoriList;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getOperazione() {
		return operazione;
	}
	public void setOperazione(String operazione) {
		this.operazione = operazione;
	}
	public String getIdAutore() {
		return idAutore;
	}
	public void setIdAutore(String idAutore) {
		this.idAutore = idAutore;
	}
	
	@Override
	public String toString() {
		return "GestCasaDiscograficaForm [nome=" + nome + ", operazione="
				+ operazione + ", idAutore=" + idAutore + "]";
	}
	public List<Autore> getAutoriList() {
		return autoriList;
	}
	public void setAutoriList(List<Autore> autoriList) {
		this.autoriList = autoriList;
	}

}
