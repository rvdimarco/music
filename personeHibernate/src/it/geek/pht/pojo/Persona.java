package it.geek.pht.pojo;

import java.io.Serializable;

public class Persona implements Serializable{

	private Integer idPersona;
	private String nome;
	private String email;
	  
	public Integer getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Persona [idPersona=" + idPersona + ", nome=" + nome
				+ ", email=" + email + "]";
	}
	  
}
