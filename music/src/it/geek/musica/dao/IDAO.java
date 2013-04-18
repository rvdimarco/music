package it.geek.musica.dao;

import java.sql.Connection;
import java.util.List;

public interface IDAO<E,K>{

	public E findById(K id, Connection c);
	public List<E> findAll(Connection c);
	public List<E> findByExample(E e, Connection c);
	public boolean delete(K id, Connection c);
	public boolean insert(E e, Connection c);
	public boolean update(E e, Connection c);
	
}
