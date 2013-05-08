package it.geek.resid.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import it.geek.resid.dao.UtenteDAO;
import it.geek.resid.exception.BusinessException;
import it.geek.resid.model.Utente;
import it.geek.resid.util.MyJNDIConnection;

public class UtenteService {

	private Logger logger = Logger.getLogger(UtenteService.class);
	
	public Utente get(String k) {
		logger.info("UtenteService::get(k)");
		
		Utente utente = null;
		Connection conn = null;
		
		try {
			
			conn = MyJNDIConnection.getConnection();
			UtenteDAO dao = new UtenteDAO();
			utente = dao.findById(k, conn);
			
		} catch (Exception e) {
			logger.error("errore inaspettato: "+e);
			throw new BusinessException(e.getMessage());
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("impossibile chiudere la connessione: "+e);
				throw new BusinessException(e.getMessage());
			}
		}
		
		return utente;
	}

}
