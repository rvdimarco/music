package it.geek.musica.service;

import it.geek.musica.model.Entity;

import java.util.List;

public interface Service<E extends Entity, K> {
	
	public E get(K k);
	public List<E> get(E e);
	public List<E> getAll();
	public void delete(K k);
	public void save(E e);
	
}
