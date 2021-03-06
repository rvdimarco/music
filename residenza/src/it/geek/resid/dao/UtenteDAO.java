package it.geek.resid.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import it.geek.resid.exception.BusinessException;
import it.geek.resid.model.Cittadino;
import it.geek.resid.model.Ruolo;
import it.geek.resid.model.Utente;

public class UtenteDAO {
	
	Logger log = Logger.getLogger(UtenteDAO.class);

	public Utente findById(String id, Connection c){
		log.info("UtenteDAO::findById");
		Utente ret = null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT username, password, cognome, nome, data_nascita, data_registrazione, cod_ruolo, desc_ruolo, attivo ");
		sb.append("FROM utenti, ruoli ");
		sb.append("WHERE ruolo = cod_ruolo ");
		sb.append("AND username = ?");
		
		log.debug("sql::"+sb.toString());
		log.debug("username = "+id);
		
		try{
			ps = c.prepareStatement(sb.toString());
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				ret = new Utente();
				ret.setUsername(rs.getString("username"));
				ret.setPassword(rs.getString("password"));
				ret.setCognome(rs.getString("cognome"));
				ret.setNome(rs.getString("nome"));
				java.sql.Date dn = rs.getDate("data_nascita");
				if(dn!=null){
					ret.setDataNascita(new Date(dn.getTime()));
				}
				java.sql.Timestamp dr = rs.getTimestamp("data_registrazione");
				if(dr!=null){
					ret.setDataRegistrazione(new Date(dr.getTime()));
				}
				Ruolo r = new Ruolo();
				r.setCodice(rs.getInt("cod_ruolo"));
				r.setDescrizione(rs.getString("desc_ruolo"));
				r.setAttivo(rs.getBoolean("attivo"));
				ret.setRuolo(r);
			}
		}catch(SQLException e){
			log.error("errore! "+e);
			throw new BusinessException(e);
		}finally{
			try {
				rs.close();
			} catch (SQLException e) {
				log.error("impossibile chiudere il ResultSet");
				throw new BusinessException(e);
			}
			try {
				ps.close();
			} catch (SQLException e) {
				log.error("impossibile chiudere il PreparedStatement");
				throw new BusinessException(e);
			}
		}
		
		return ret;
	}

	public List<Utente> findAll(Connection c){
		log.info("UtenteDAO::findAll");
		List<Utente> ret = null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT username, password, cognome, nome, data_nascita, data_registrazione, cod_ruolo, desc_ruolo, attivo ");
		sb.append("FROM utenti, ruoli ");
		sb.append("WHERE ruolo = cod_ruolo ");
		
		log.debug("sql::"+sb.toString());
		
		try{
			ps = c.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			ret = new ArrayList<Utente>();
			Utente ut = null;
			while(rs.next()){
				ut = new Utente();
				ut.setUsername(rs.getString("username"));
				ut.setPassword(rs.getString("password"));
				ut.setCognome(rs.getString("cognome"));
				ut.setNome(rs.getString("nome"));
				java.sql.Date dn = rs.getDate("data_nascita");
				if(dn!=null){
					ut.setDataNascita(new Date(dn.getTime()));
				}
				java.sql.Timestamp dr = rs.getTimestamp("data_registrazione");
				if(dr!=null){
					ut.setDataRegistrazione(new Date(dr.getTime()));
				}
				Ruolo r = new Ruolo();
				r.setCodice(rs.getInt("cod_ruolo"));
				r.setDescrizione(rs.getString("desc_ruolo"));
				r.setAttivo(rs.getBoolean("attivo"));
				ut.setRuolo(r);
				ret.add(ut);
			}
		}catch(SQLException e){
			log.error("errore! "+e);
			throw new BusinessException(e);
		}finally{
			try {
				rs.close();
			} catch (SQLException e) {
				log.error("impossibile chiudere il ResultSet");
				throw new BusinessException(e);
			}
			try {
				ps.close();
			} catch (SQLException e) {
				log.error("impossibile chiudere il PreparedStatement");
				throw new BusinessException(e);
			}
		}
		
		return ret;
	}
}
