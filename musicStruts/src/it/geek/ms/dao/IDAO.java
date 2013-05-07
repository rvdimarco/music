package it.geek.ms.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IDAO<E,K>{

	public E findById(K id, Connection c) throws SQLException;
	public List<E> findAll(Connection c) throws SQLException;
	public List<E> findByExample(E e, Connection c) throws SQLException;
	public boolean delete(K id, Connection c) throws SQLException;
	public boolean insert(E e, Connection c) throws SQLException;
	public boolean update(E e, Connection c) throws SQLException;
	
}
