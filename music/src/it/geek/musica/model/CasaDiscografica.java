package it.geek.musica.model;

import java.util.ArrayList;
import java.util.List;

public class CasaDiscografica extends Entity {

	private String nome;
	private String sede;
	private List<Autore> autori = new ArrayList<Autore>();
	
	public void addAutore(Autore a){
		autori.add(a);
	}
	public void removeAutore(Autore a){
		autori.remove(a);
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSede() {
		return sede;
	}
	public void setSede(String sede) {
		this.sede = sede;
	}
	
	public List<Autore> getAutori() {
		return autori;
	}
	public void setAutori(List<Autore> autori) {
		this.autori = autori;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sede == null) ? 0 : sede.hashCode());
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
		CasaDiscografica other = (CasaDiscografica) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sede == null) {
			if (other.sede != null)
				return false;
		} else if (!sede.equals(other.sede))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CasaDiscografica [nome=" + nome + ", sede=" + sede
				+ ", autori=" + autori + "]";
	}

}
