package it.geek.resid.service;

import it.geek.resid.dao.CittadinoDAO;
import it.geek.resid.exception.BusinessException;
import it.geek.resid.model.Cittadino;
import it.geek.resid.model.Utente;
import it.geek.resid.util.MyJNDIConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

public class CittadinoService {

	private Logger logger = Logger.getLogger(CittadinoService.class);
	
	public List<Cittadino> getAll() {
		logger.info("CittadinoService::getAll()");
		
		List<Cittadino> cittadini = null;
		Connection conn = null;
		
		try {
			
			conn = MyJNDIConnection.getConnection();
			CittadinoDAO dao = new CittadinoDAO();
			cittadini = dao.findAll(conn);
			
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
		
		return cittadini;
	}
	
}
