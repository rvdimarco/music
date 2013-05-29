package it.geek.pht.service;

import java.util.List;

public interface Service<E, K> {

	public List<E> getAll();
	public List<E> getByExample(E e);
	public List<E> getOrphans();
	public E get(K k);
	public void save(E e);
	public void delete(E e);
}
