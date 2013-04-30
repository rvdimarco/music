package it.geek.ms.service;

import it.geek.ms.model.Entity;

import java.util.List;

public interface Service<E extends Entity, K> {
	
	public E get(K k);
	public List<E> get(E e);
	public List<E> getAll();
	public void delete(K k);
	public void create(E e);
	public void save(E e);
	
}
