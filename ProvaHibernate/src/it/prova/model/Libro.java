package it.prova.model;

public class Libro   {
	
	   // Fields    

    private Integer idLibro;
    private Utente utente;
    private String titolo;
    private Integer pagine;

    // Constructors

    /** default constructor */
    public Libro() {
    }

    
	public Integer getIdLibro() {
		return idLibro;
	}


	public void setIdLibro(Integer idLibro) {
		this.idLibro = idLibro;
	}


	public Utente getUtente() {
		return utente;
	}


	public void setUtente(Utente utente) {
		this.utente = utente;
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
    
   
}
