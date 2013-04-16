package it.geek.musica.dao;

import java.util.List;

public interface IDAO<E,K>{

	public E findById(K id);
	public List<E> findAll();
	public boolean delete(K id);
	public boolean insert(E e);
	public boolean update(E e);
	
}
