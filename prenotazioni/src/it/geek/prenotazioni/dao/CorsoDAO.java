package it.geek.prenotazioni.dao;

import it.geek.prenotazioni.exception.DaoException;
import it.geek.prenotazioni.model.Corso;
import it.geek.prenotazioni.model.Studente;
import it.geek.prenotazioni.util.MyJNDIConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class CorsoDAO {

	private static Logger log = Logger.getLogger(CorsoDAO.class);
	
	public List<Corso> findAll(){
		List<Corso> ritorno = null;
		
		Connection connection = MyJNDIConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
			sb.append("id, ");
			sb.append("materia ");
		sb.append("FROM corsi ");

		log.debug("sql: "+sb.toString());
		
		try {
			ps = connection.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			
			Corso corso = null;
			ritorno = new ArrayList<>();
			while(rs.next()){
				corso = new Corso();
				corso.setId(rs.getInt("id"));
				corso.setMateria(rs.getString("materia"));
				ritorno.add(corso);
			}
		} catch (SQLException e) {
			log.error("errore: "+e);
			throw new DaoException(e.getMessage());
		} finally{
			try {
				rs.close();
			} catch (SQLException e) {
				log.error("Impossibile chiudere il ResultSet "+e);
				throw new DaoException(e.getMessage());
			}
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
		
		return ritorno;
	}

}
