package it.geek.ms.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import it.geek.ms.model.Autore;
import it.geek.ms.service.Service;
import it.geek.ms.dao.IDAO;
import it.geek.ms.factory.DaoFactory;
import it.geek.ms.util.MyJNDIConnection;

public class AutoreService implements Service<Autore, String> {

	private Logger logger = Logger.getLogger(AutoreService.class);
	
	@Override
	public Autore get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Autore> get(Autore a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Autore> getAll() {
		logger.info("AutoreService::getAll");
		
		List<Autore> utenti = null;
		Connection conn = null;
		
		try {
			
			conn = MyJNDIConnection.getConnection();
			IDAO dao = DaoFactory.getUtenteDAO();
			utenti = (List<Autore>)dao.findAll(conn);
			
		} catch (Exception e) {
			logger.error("errore inaspettato: "+e);
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("impossibile chiudere la connessione: "+e);
			}
		}
		
		return utenti;	
	}

	@Override
	public void delete(String k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Autore e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create(Autore e) {
		// TODO Auto-generated method stub
		
	}

}
