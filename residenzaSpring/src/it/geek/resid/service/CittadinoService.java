package it.geek.resid.service;

import it.geek.resid.dao.CittadinoDaoInterface;
import it.geek.resid.exception.BusinessException;
import it.geek.resid.model.Cittadino;
import it.geek.resid.util.MyJNDIConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

public class CittadinoService implements CittadinoServiceInterface{

	private Logger logger = Logger.getLogger(CittadinoService.class);
	
	private CittadinoDaoInterface cittadinoDAO;
	
	public void setCittadinoDAO(CittadinoDaoInterface cittadinoDAO){
		this.cittadinoDAO = cittadinoDAO;
	}
	
	public List<Cittadino> getAll() {
		logger.info("CittadinoService::getAll()");
		
		List<Cittadino> cittadini = null;
		Connection conn = null;
		
		try {
			
			conn = MyJNDIConnection.getConnection();
			cittadini = cittadinoDAO.findAll(conn);
			
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
	
	public void save(Cittadino c) {
		logger.info("save");
		
		Connection conn = null;
		
		try {
			
			conn = MyJNDIConnection.getConnection();
			
			if(c == null || c.getCodiceFiscale()==null){
				throw new BusinessException("cittadino non identificabile...");
			}
			
			Cittadino cf = cittadinoDAO.findById(c.getCodiceFiscale(), conn);
			boolean wasSaved = false;
			
			if(cf==null){
				throw new BusinessException("cittadino non trovato!");
				
			}else{
				wasSaved = cittadinoDAO.update(c,conn);
			}
			
			if(!wasSaved){
				throw new BusinessException("non è stato possibile modificare il cittadino...");
			}
			
		} catch(BusinessException e){
			throw e;
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
