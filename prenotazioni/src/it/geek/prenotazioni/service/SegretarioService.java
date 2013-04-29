package it.geek.prenotazioni.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import it.geek.prenotazioni.exception.DaoException;
import it.geek.prenotazioni.model.Corso;
import it.geek.prenotazioni.model.Studente;
import it.geek.prenotazioni.util.MyJNDIConnection;

public class SegretarioService {

	private static Logger log = Logger.getLogger(SegretarioService.class);
	
	public void prenota(Studente studente, Corso corso){
		
		Connection connection = MyJNDIConnection.getConnection();
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
			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Impossibile chiudere la Connection "+e);
				throw new DaoException(e.getMessage());
			}		
		}
		
	}
	
	public void cancellaPrenotazione(Studente studente, Corso corso){
		
		Connection connection = MyJNDIConnection.getConnection();
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
			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Impossibile chiudere la Connection "+e);
				throw new DaoException(e.getMessage());
			}		
		}
		
	}
	
}
