package it.geek.prenotazioni.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.OperationsException;

import org.apache.log4j.Logger;

import it.geek.prenotazioni.dao.PrenotazioneDao;
import it.geek.prenotazioni.exception.DaoException;
import it.geek.prenotazioni.model.Corso;
import it.geek.prenotazioni.model.Studente;
import it.geek.prenotazioni.util.MyJNDIConnection;

public class SegretarioService {

	private static Logger log = Logger.getLogger(SegretarioService.class);
	
	public void prenota(Studente studente, Corso corso){
		log.info("prenota");
		Connection connection = MyJNDIConnection.getConnection();
		
		try {
			PrenotazioneDao dao = new PrenotazioneDao();
			dao.insert(studente, corso, connection);
		} catch (Exception e) {
			log.error("errore: "+e);
			throw new DaoException(e.getMessage());
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Impossibile chiudere la Connection "+e);
				throw new DaoException(e.getMessage());
			}			
		}
	}
	
	public void cancellaPrenotazione(Studente studente, Corso corso){
		log.info("cancellaPrenotazione");
		Connection connection = MyJNDIConnection.getConnection();
		
		try {
			PrenotazioneDao dao = new PrenotazioneDao();
			dao.delete(studente, corso, connection);
		} catch (Exception e) {
			log.error("errore: "+e);
			throw new DaoException(e.getMessage());
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Impossibile chiudere la Connection "+e);
				throw new DaoException(e.getMessage());
			}			
		}		
		
	}
	
	public void cancellaPrenotazioni(Studente studente, List<Integer> corsi){
		log.info("cancellaPrenotazioni");
		Connection connection = MyJNDIConnection.getConnection();
		
		try {
			//dichiaro che dirò io quando "scolpire" i dati sul db
			connection.setAutoCommit(false);
			
			//eseguo con un ciclo tutte le delete
			PrenotazioneDao dao = new PrenotazioneDao();
			/*int i=0;*/ //test transazione
			for(Integer id : corsi){
				/*if(i==2) throw new RuntimeException();*/ //test transazione
				dao.delete(studente, new Corso(id), connection);
				/*i++;*/ //test transazione
			}
			
			//se tutto va bene "scolpisco"
			connection.commit();
			
		} catch (Exception e) {
			//se qualcosa è andata male... abbiamo scherzato!
			try {
				connection.rollback();
			} catch (SQLException sqle) {
				log.error("impossibile effettuare l'operazione di rollback... "+sqle);
				throw new DaoException(e.getMessage());
			}
			
			log.error("errore: "+e);
			throw new DaoException(e.getMessage());
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Impossibile chiudere la Connection "+e);
				throw new DaoException(e.getMessage());
			}			
		}		
		
	}	
	
}
