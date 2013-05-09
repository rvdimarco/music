package it.geek.resid.sp.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import it.geek.resid.sp.dao.UtenteDAO;
import it.geek.resid.sp.exception.BusinessException;
import it.geek.resid.sp.pojo.Utente;
import it.geek.resid.sp.util.MyJDBCConnection;

public class UtenteServiceImpl implements UtenteService{

	private Logger logger = Logger.getLogger(UtenteServiceImpl.class);
	
	private UtenteDAO utenteDAO;

	public void setUtenteDAO(UtenteDAO utenteDAO) {
		this.utenteDAO = utenteDAO;
	}
	
	public Utente get(String k) {
		logger.info("UtenteService::get(k)");
		
		Utente utente = null;
		Connection conn = null;
		
		try {
			
			conn = MyJDBCConnection.getConnection();
			utente = utenteDAO.findById(k, conn);
			
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
