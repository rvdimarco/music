package it.geek.pht.pojo;

import java.io.Serializable;

public class Persona implements Serializable{

	private int idPersona;
	private String nome;
	private String email;
	  
	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
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
	  

	
}
