package it.geek.resid.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import it.geek.resid.dao.UtenteDAO;
import it.geek.resid.exception.BusinessException;
import it.geek.resid.model.Ruolo;
import it.geek.resid.model.Utente;
import it.geek.resid.util.MyJNDIConnection;

public class UtenteService implements UtenteServiceInterface{

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

	public List<Utente> getAll() {
		logger.info("UtenteService::getAll()");
		
		List<Utente> utenti = null;
		Connection conn = null;
		
		try {
			
			conn = MyJNDIConnection.getConnection();
			UtenteDAO dao = new UtenteDAO();
			utenti = dao.findAll(conn);
			
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
		
		return utenti;
	}

	@Override
	public void multiSave(String[] ids, Ruolo ruolo) {
		logger.info("multiSave");
		Connection connection = MyJNDIConnection.getConnection();
		
		try {
			connection.setAutoCommit(false);
			
			//eseguo con un ciclo tutte le delete
			UtenteDAO dao = new UtenteDAO();
			Utente u = new Utente();
			u.setRuolo(ruolo);
			int i=0; //test transazione
			for(String id : ids){
					
				u.setUsername(id);
				if(i==2) throw new RuntimeException("test eseguito!"); //test transazione
				
				if(id == null){
					throw new BusinessException("utente non identificabile... [usedrname:"+" id]");
				}
				
				Utente uf = dao.findById(id, connection);
				boolean wasSaved = false;
				
				if(uf==null){
					throw new BusinessException("utente non trovato! [usedrname:"+" id]");
					
				}else{
					wasSaved = dao.update(u, connection);
					i++; //test transazione
				}
				
				if(!wasSaved){
					throw new BusinessException("non è stato possibile modificare l'utente... [usedrname:"+" id]");
				}
					
			}
			
			//se tutto va bene "scolpisco"
			connection.commit();
			
		} catch (Exception e) {
			//se qualcosa è andata male... abbiamo scherzato!
			try {
				connection.rollback();
			} catch (SQLException sqle) {
				logger.error("impossibile effettuare l'operazione di rollback... "+sqle);
				throw new BusinessException(e);
			}
			
			logger.error("errore: "+e);
			throw new BusinessException(e);
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error("Impossibile chiudere la Connection "+e);
				throw new BusinessException(e);
			}			
		}		
	}

	public void create(Utente u){
		throw new UnsupportedOperationException("operazione non disponibile per questa implementazione....");
	}
	
}
