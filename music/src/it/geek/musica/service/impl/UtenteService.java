package it.geek.musica.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import it.geek.musica.dao.IDAO;
import it.geek.musica.factory.DaoFactory;
import it.geek.musica.model.Utente;
import it.geek.musica.service.Service;
import it.geek.musica.util.MyJNDIConnection;

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
		throw new UnsupportedOperationException("UtenteService::get(e):: metodo non implementato");
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
		throw new UnsupportedOperationException("UtenteService::delete:: metodo non implementato");
	}

	@Override
	public void save(Utente e) {
		throw new UnsupportedOperationException("UtenteService::save:: metodo non implementato");
	}

}
