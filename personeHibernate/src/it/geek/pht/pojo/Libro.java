package it.geek.pht.pojo;

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


	@Override
	public String toString() {
		return "Libro [idLibro=" + idLibro + ", idUtente=" + utente.getIdUtente() + ", titolo="
				+ titolo + ", pagine=" + pagine + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idLibro == null) ? 0 : idLibro.hashCode());
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
		Libro other = (Libro) obj;
		if (idLibro == null) {
			if (other.idLibro != null)
				return false;
		} else if (!idLibro.equals(other.idLibro))
			return false;
		return true;
	}
    
   
}
