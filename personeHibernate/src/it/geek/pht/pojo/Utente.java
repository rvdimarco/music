package it.geek.pht.pojo;

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


    public void addLibro(Libro l){
    	libros.add(l);
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
		StringBuilder sb = new StringBuilder();
		for(Libro l : libros){
			sb.append(l.toString());
		}
		return "Utente [idUtente=" + idUtente + ", username=" + username
				+ ", password=" + password + ", nome=" + nome + ", cognome="
				+ cognome + ", libros=" + sb.toString() + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idUtente == null) ? 0 : idUtente.hashCode());
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
		if (idUtente == null) {
			if (other.idUtente != null)
				return false;
		} else if (!idUtente.equals(other.idUtente))
			return false;
		return true;
	}


   
}
