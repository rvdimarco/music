package it.geek.prenotazioni.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.geek.prenotazioni.model.Corso;
import it.geek.prenotazioni.model.Studente;
import it.geek.prenotazioni.util.MyJNDIConnection;

import org.apache.log4j.Logger;

public class StudenteDAO {

	private static Logger log = Logger.getLogger(StudenteDAO.class);
	
	public Studente findById(String id){
		Studente ritorno = null;
		
		Connection connection = MyJNDIConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
			sb.append("s.matricola as matricola_studente, ");
			sb.append("s.nome as nome_studente, ");
			sb.append("s.cognome as cognome_studente, ");
			sb.append("c.id as id_corso, ");
			sb.append("c.materia as materia_corso ");
		sb.append("FROM studenti s ");
		sb.append("LEFT JOIN  prenotazioni p ON s.matricola = p.cod_studente ");
		sb.append("LEFT JOIN  corsi c ON p.cod_corso = c.id ");
		sb.append("WHERE s.matricola = ?");

		log.debug("sql: "+sb.toString());
		log.debug("s.matricola = "+id);
		
		try {
			ps = connection.prepareStatement(sb.toString());
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			Corso corso = null;
			if(rs.next()){
				ritorno = new Studente();
				ritorno.setMatricola(rs.getString("matricola_studente"));
				ritorno.setNome(rs.getString("nome_studente"));
				ritorno.setCognome(rs.getString("cognome_studente"));
				corso = new Corso();
				corso.setId(rs.getInt("id_corso"));
				corso.setMateria(rs.getString("materia_corso"));
				if(!corso.isNull()){
					ritorno.addPrenotazione(corso);
				}
			}
			while(rs.next()){
				corso = new Corso();
				corso.setId(rs.getInt("id_corso"));
				corso.setMateria(rs.getString("materia_corso"));
				if(!corso.isNull()){
					ritorno.addPrenotazione(corso);
				}			
			}
			
		} catch (SQLException e) {
			log.error("errore: "+e);
		} finally{
			try {
				rs.close();
			} catch (SQLException e) {
				log.error("Impossibile chiudere il ResultSet "+e);
			}
			try {
				ps.close();
			} catch (SQLException e) {
				log.error("Impossibile chiudere il PreparedStatement "+e);
			}
			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Impossibile chiudere la Connection "+e);
			}		
		}
		
		return ritorno;
	}

}
