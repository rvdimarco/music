package it.geek.resid.sp.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import it.geek.resid.sp.dao.UtenteDAO;
import it.geek.resid.sp.exception.BusinessException;
import it.geek.resid.sp.pojo.Utente;
import it.geek.resid.sp.util.MyJNDIConnection;

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
