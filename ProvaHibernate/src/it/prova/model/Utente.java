package it.prova.model;

import java.util.HashSet;
import java.util.Set;

public class Utente{
	
	   // Fields    

    private Integer idUtente;
    private String username;
    private String password;
    private String nome;
    private String cognome;
    private Set<Libro> libros = new HashSet<Libro>(0);

    // Constructors

    /** default constructor */
    public Utente() {
    }


	public Integer getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Integer idUtente) {
		this.idUtente = idUtente;
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


	public Set<Libro> getLibros() {
		return libros;
	}


	public void setLibros(Set<Libro> libros) {
		this.libros = libros;
	}

    @Override
    public String toString() {
    	return "nome: "+getNome()+" cognome: "+getCognome()+" username: "+username;
    }
   
}
