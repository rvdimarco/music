package it.geek.pht.form;


import it.geek.pht.pojo.Libro;
import it.geek.pht.pojo.Utente;

import java.util.HashSet;
import java.util.Set;

import org.apache.struts.action.ActionForm;

public class BibliotecaForm extends ActionForm {

    private Integer idUtente;
    private String username;
    private String password;
    private String nome;
    private String cognome;
    
    private Set<Libro> libros = new HashSet<Libro>(0);
    private Utente utente = new Utente();;
    
    private Integer idLibro;
    private String titolo;
    private Integer pagine;
    
    private String[] libriSelezionati;
    
    
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
	public Integer getIdLibro() {
		return idLibro;
	}
	public void setIdLibro(Integer idLibro) {
		this.idLibro = idLibro;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public Integer getPagine() {
		return pagine;
	}
	public void setPagine(Integer pagine) {
		this.pagine = pagine;
	}
	public Set<Libro> getLibros() {
		return libros;
	}
	public void setLibros(Set<Libro> libros) {
		this.libros = libros;
	}
	public Utente getUtente() {
		return utente;
	}
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	public String[] getLibriSelezionati() {
		return libriSelezionati;
	}
	public void setLibriSelezionati(String[] libriSelezionati) {
		this.libriSelezionati = libriSelezionati;
	}
	@Override
	public String toString() {
		return "BibliotecaForm [idUtente=" + idUtente + ", username="
				+ username + ", password=" + password + ", nome=" + nome
				+ ", cognome=" + cognome + ", libros=" + libros + ", utente="
				+ utente + ", idLibro=" + idLibro + ", titolo=" + titolo
				+ ", pagine=" + pagine + "]";
	}

	

	
}
