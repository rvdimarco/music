package it.geek.musica.dao.impl;

import it.geek.musica.dao.IDAO;
import it.geek.musica.model.Ruolo;
import it.geek.musica.model.Utente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class UtenteDAO implements IDAO<Utente, String> {
	
	private static Logger logger = Logger.getLogger(UtenteDAO.class);

	@Override
	public Utente findById(String id, Connection c) {
		logger.info("UtenteDAO::findById");
		Utente ret = null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT username, password, cognome, nome, codice, descrizione ");
		sb.append("FROM utenti, ruoli ");
		sb.append("WHERE id_ruolo = codice ");
		sb.append("AND username = ?");
		
		logger.debug("sql::"+sb.toString());
		logger.debug("username = "+id);
		
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
				Ruolo r = new Ruolo();
				r.setCodice(rs.getInt("codice"));
				r.setDescrizione(rs.getString("descrizione"));
				ret.setRuolo(r);
			}
		}catch(Exception e){
			logger.error("errore! "+e);
			e.printStackTrace();
		}finally{
			try {
				rs.close();
			} catch (Exception e2) {
				logger.error("impossibile chiudere il ResultSet");
			}
			try {
				ps.close();
			} catch (Exception e2) {
				logger.error("impossibile chiudere il PreparedStatement");
			}
		}
		
		return ret;
	}

	@Override
	public List<Utente> findAll(Connection c) {
		logger.info("UtenteDAO::findAll");
		List<Utente> ret = null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT username, password, cognome, nome, codice, descrizione ");
		sb.append("FROM utenti, ruoli ");
		sb.append("WHERE id_ruolo = codice ");
		
		logger.debug("sql::"+sb.toString());
		
		try{
			ps = c.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			
			ret = new ArrayList<Utente>();
			Utente u = null;
			Ruolo r = null;
			while(rs.next()){
				u = new Utente();
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setCognome(rs.getString("cognome"));
				u.setNome(rs.getString("nome"));
				r = new Ruolo();
				r.setCodice(rs.getInt("codice"));
				r.setDescrizione(rs.getString("descrizione"));
				u.setRuolo(r);
				ret.add(u);
			}
		}catch(Exception e){
			logger.error("errore! "+e);
			e.printStackTrace();
		}finally{
			try {
				rs.close();
			} catch (Exception e2) {
				logger.error("impossibile chiudere il ResultSet");
			}
			try {
				ps.close();
			} catch (Exception e2) {
				logger.error("impossibile chiudere il PreparedStatement");
			}
		}
		
		return ret;
	}

	@Override
	public boolean delete(String id, Connection c) {
		logger.info("UtenteDAO::delete");
		boolean wasDeleted = false;
		
		if(id==null){
			return wasDeleted;
		}
		
		PreparedStatement ps = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE ");
		sb.append("FROM utenti ");
		sb.append("WHERE username = ? ");
		
		logger.info("sql::"+sb.toString());
		logger.info("username = "+id);
		
		try{
			ps = c.prepareStatement(sb.toString());
			ps.setString(1, id);
			
			int deleted = ps.executeUpdate();
			
			if(deleted>0){
				wasDeleted = true;
			}
			
		}catch(Exception e){
			logger.error("errore! "+e);
			e.printStackTrace();
			
		}finally{
			try {
				ps.close();
			} catch (Exception e2) {
				logger.error("impossibile chiudere il PreparedStatement");
			}
		}
		
		return wasDeleted;	}

	@Override
	public boolean insert(Utente u, Connection c) {
		logger.info("UtenteDAO::insert");
		boolean wasInserted = false;
		
		if(u==null || u.getUsername()==null || u.getNome()==null || u.getCognome()==null || u.getPassword()==null || u.getCodiceRuolo()==0){
			return wasInserted;
		}
		
		PreparedStatement ps = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO utenti (username, password, nome, cognome, id_ruolo) ");
		sb.append("VALUES (?,?,?,?,?)");
		
		try{
			ps = c.prepareStatement(sb.toString());
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getNome());
			ps.setString(4, u.getCognome());
			ps.setInt(5, u.getCodiceRuolo());
			
			logger.debug("sql: "+sb.toString());
			logger.debug("username = "+u.getUsername());
			logger.debug("password = "+u.getPassword());
			logger.debug("nome = "+u.getNome());
			logger.debug("cognome = "+u.getCognome());
			logger.debug("id_ruolo = "+u.getCodiceRuolo());
			
			int inserted = ps.executeUpdate();
			
			if(inserted>0){
				wasInserted = true;
			}
			
		}catch(Exception e){
			logger.error("errore! "+e);
			e.printStackTrace();
			
		}finally{
			try {
				ps.close();
			} catch (Exception e2) {
				logger.error("impossibile chiudere il PreparedStatement");
			}
		}
		
		return wasInserted;	}

	@Override
	public boolean update(Utente u, Connection c) {
		logger.info("UtenteDAO::update");
		boolean wasUpdated = false;
		
		if(u==null || u.getUsername()==null){
			return wasUpdated;
		}
		
		PreparedStatement ps = null;
		
		if(u.getNome()==null && u.getCognome()==null && u.getPassword()==null&& u.getCodiceRuolo()==0){
			return wasUpdated;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE utenti SET ");
		if(u.getPassword()!=null){
			sb.append("password = ?,");
		}
		if(u.getNome()!=null){
			sb.append("nome = ?,");
		}
		if(u.getCognome()!=null){
			sb.append("cognome = ?,");
		}
		if(u.getCodiceRuolo()!=0){
			sb.append("casa_discografica = ?,");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append("WHERE username = ?");
		
		try{
			ps = c.prepareStatement(sb.toString());
			int i = 0;
			if(u.getPassword()!=null){
				ps.setString(++i, u.getPassword());
			}
			if(u.getNome()!=null){
				ps.setString(++i, u.getNome());
			}
			if(u.getCognome()!=null){
				ps.setString(++i, u.getCognome());
			}
			if(u.getCodiceRuolo()!=0){
				ps.setInt(++i, u.getCodiceRuolo());
			}
			ps.setString(++i, u.getUsername());
			
			
			logger.debug("sql: "+sb.toString());
			logger.debug("utente = "+u);
			
			int updated = ps.executeUpdate();
			
			if(updated>0){
				wasUpdated = true;
			}
			
		}catch(Exception e){
			logger.error("errore! "+e);
			e.printStackTrace();
			
		}finally{
			try {
				ps.close();
			} catch (Exception e2) {
				logger.error("impossibile chiudere il PreparedStatement");
			}
		}
		
		return wasUpdated;	}

	@Override
	public List<Utente> findByExample(Utente ex, Connection c) {
		throw new UnsupportedOperationException("UtenteDAO::findByExample:: metodo non implementato");
	}

}
