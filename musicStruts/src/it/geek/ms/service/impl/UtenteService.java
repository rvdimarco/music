package it.geek.ms.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import it.geek.ms.dao.IDAO;
import it.geek.ms.exception.BusinessException;
import it.geek.ms.factory.DaoFactory;
import it.geek.ms.model.Utente;
import it.geek.ms.service.Service;
import it.geek.ms.util.MyJNDIConnection;

public class UtenteService implements Service<Utente, String> {

	private Logger logger = Logger.getLogger(UtenteService.class);
	
	@Override
	public Utente get(String k) {
		logger.info("UtenteService::get(k)");
		
		Utente utente = null;
		Connection conn = null;
		
		try {
			
			conn = MyJNDIConnection.getConnection();
			IDAO dao = DaoFactory.getUtenteDAO();
			utente = (Utente)dao.findById(k, conn);
			
		} catch (Exception e) {
			logger.error("errore inaspettato: "+e);
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("impossibile chiudere la connessione: "+e);
			}
		}
		
		return utente;
	}

	@Override
	public List<Utente> get(Utente u) {
		logger.info("UtenteService::get(e)");
		
		List<Utente> utenti = null;
		Connection conn = null;
		
		try {
			
			conn = MyJNDIConnection.getConnection();
			IDAO dao = DaoFactory.getUtenteDAO();
			utenti = (List<Utente>)dao.findByExample(u, conn);
			
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
	public List<Utente> getAll() {
		
		logger.info("UtenteService::getAll");
		
		List<Utente> utenti = null;
		Connection conn = null;
		
		try {
			
			conn = MyJNDIConnection.getConnection();
			IDAO dao = DaoFactory.getUtenteDAO();
			utenti = (List<Utente>)dao.findAll(conn);
			
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
		logger.info("UtenteService::delete");
		
		Connection conn = null;
		
		try {
			
			conn = MyJNDIConnection.getConnection();
			IDAO dao = DaoFactory.getUtenteDAO();
			boolean wasDeleted = dao.delete(k,conn);
			
			if(!wasDeleted){
				throw new BusinessException("non è stato possibile eliminare il record...");
			}
			
		} catch (Exception e) {
			logger.error("errore inaspettato: "+e);
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("impossibile chiudere la connessione: "+e);
			}
		}
		
	}

	@Override
	public void save(Utente u) {
		logger.info("UtenteService::save");
		
		Connection conn = null;
		
		try {
			
			conn = MyJNDIConnection.getConnection();
			IDAO dao = DaoFactory.getUtenteDAO();
			
			if(u == null || u.getUsername()==null){
				throw new BusinessException("utente non identificabile...");
			}
			
			Utente uf = (Utente)dao.findById(u.getUsername(), conn);
			boolean wasSaved = false;
			
			if(uf==null){
				throw new BusinessException("utente non trovato!");
				
			}else{
				wasSaved = dao.update(u,conn);
			}
			
			if(!wasSaved){
				throw new BusinessException("non è stato possibile modificare l'utente...");
			}
			
		} catch (Exception e) {
			logger.error("errore inaspettato: "+e);
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("impossibile chiudere la connessione: "+e);
			}
		}	
	}

	@Override
	public void create(Utente u) {
		logger.info("UtenteService::create");
		
		Connection conn = null;
		
		try {
			
			conn = MyJNDIConnection.getConnection();
			IDAO dao = DaoFactory.getUtenteDAO();
			
			if(u == null || u.getUsername()==null){
				throw new BusinessException("utente non identificabile...");
			}
			
			Utente uf = (Utente)dao.findById(u.getUsername(), conn);
			boolean wasCreated = false;
			
			if(uf==null){
				wasCreated = dao.insert(u,conn);
			}else{
				throw new BusinessException("utente già registrato!");
			}
			
			if(!wasCreated){
				throw new BusinessException("non è stato possibile inserire l'utente...");
			}
			
		} catch (Exception e) {
			logger.error("errore inaspettato: "+e);
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("impossibile chiudere la connessione: "+e);
			}
		}	
	}

}
