package it.geek.prenotazioni.dao;

import it.geek.prenotazioni.exception.DaoException;
import it.geek.prenotazioni.model.Corso;
import it.geek.prenotazioni.model.Studente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class PrenotazioneDao {

	private static Logger log = Logger.getLogger(PrenotazioneDao.class);

	public void insert(Studente studente, Corso corso, Connection connection){
		log.info("insert");
		PreparedStatement ps = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO prenotazioni ");
		sb.append("(cod_studente, cod_corso) ");
		sb.append("VALUES(?,?)");

		log.debug("sql: "+sb.toString());
		log.debug("cod_studente = "+studente.getMatricola());
		log.debug("cod_corso = "+corso.getId());
		
		try {
			ps = connection.prepareStatement(sb.toString());
			ps.setString(1, studente.getMatricola());
			ps.setInt(2, corso.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			log.error("errore: "+e);
			throw new DaoException(e.getMessage());
		} finally{
			try {
				ps.close();
			} catch (SQLException e) {
				log.error("Impossibile chiudere il PreparedStatement "+e);
				throw new DaoException(e.getMessage());
			}
		}
	}
	
	public void delete(Studente studente, Corso corso, Connection connection){
		log.info("delete");
		PreparedStatement ps = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM prenotazioni ");
		sb.append("WHERE cod_studente = ? AND cod_corso = ?");

		log.debug("sql: "+sb.toString());
		log.debug("cod_studente = "+studente.getMatricola());
		log.debug("cod_corso = "+corso.getId());
		
		try {
			ps = connection.prepareStatement(sb.toString());
			ps.setString(1, studente.getMatricola());
			ps.setInt(2, corso.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			log.error("errore: "+e);
			throw new DaoException(e.getMessage());
		} finally{
			try {
				ps.close();
			} catch (SQLException e) {
				log.error("Impossibile chiudere il PreparedStatement "+e);
				throw new DaoException(e.getMessage());
			}
		}
	}	
	
}
