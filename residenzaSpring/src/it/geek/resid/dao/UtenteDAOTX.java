package it.geek.resid.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import it.geek.resid.model.Utente;

public class UtenteDAOTX implements UtenteDaoInterface{
	
	Logger log = Logger.getLogger(UtenteDAOTX.class);
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}

	public Utente findById(String id){
		
		return findById(id,null);
		
	}
	public Utente findById(String id, Connection c){
		log.info("UtenteDAOTX::findById");
		
		Utente u = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT username, password, cognome, nome, data_nascita, data_registrazione, cod_ruolo, desc_ruolo, attivo ");
		sb.append("FROM utenti, ruoli ");
		sb.append("WHERE ruolo = cod_ruolo ");
		sb.append("AND username = ?");
		
		log.debug("sql::"+sb.toString());
		log.debug("username = "+id);
		
		try {
			u = (Utente)jdbcTemplate.queryForObject(sb.toString(), new Object[]{id}, new UtenteRowMapper());
		} catch (EmptyResultDataAccessException e) {
			log.warn("ATTENZIONE: nessun elemento trovato!");
		}
		
		return u;
	}

	public List<Utente> findAll(){
		return findAll(null);
	}
	public List<Utente> findAll(Connection c){
		log.info("UtenteDAOTX::findAll");
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT username, password, cognome, nome, data_nascita, data_registrazione, cod_ruolo, desc_ruolo, attivo ");
		sb.append("FROM utenti, ruoli ");
		sb.append("WHERE ruolo = cod_ruolo ");
		
		log.debug("sql::"+sb.toString());
		
		return (List<Utente>)jdbcTemplate.query(sb.toString(), new UtenteRowMapper());
	}

	@Override
	public boolean insert(Utente u) {

		return insert(u, null);
		
	}
	public boolean insert(Utente u, Connection c){
		log.info("UtenteDAOTX::insert");
		boolean wasInserted = false;
		
		if(u==null || u.getUsername()==null || u.getNome()==null || u.getCognome()==null || u.getPassword()==null || u.getDataNascita()==null || u.getRuolo()==null || (u.getRuolo()!=null&&u.getRuolo().getCodice()==0)){
			return wasInserted;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO utenti (username, password, cognome, nome, data_nascita, ruolo) ");
		sb.append("VALUES (?,?,?,?,?,?)");
			
		int inserted = jdbcTemplate.update(sb.toString(), 
				new Object[]{u.getUsername(),u.getPassword(),u.getCognome(),u.getNome(),u.getDataNascita(),u.getRuolo().getCodice()});
			
		if(inserted>0){
			wasInserted = true;
		}
		
		return wasInserted;
	}

	public boolean update(Utente u){
		return update(u,null);
	}
	@Override
	public boolean update(Utente u, Connection c) {
		log.info("UtenteDAO::update");
		boolean wasUpdated = false;
		
		if(u==null || u.getUsername()==null){
			return wasUpdated;
		}
		
		if(u.getPassword()==null && u.getCognome()==null && u.getNome()==null && u.getRuolo().getCodice()==null && u.getDataNascita()==null && u.getDataRegistrazione()==null ){
			return wasUpdated;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE utenti SET ");

		List<Object> params = new ArrayList<>();
		
		if(u.getPassword()!=null){
			sb.append("password = ?,");
			params.add(u.getPassword());
		}
		if(u.getCognome()!=null){
			sb.append("cognome = ?,");
			params.add(u.getCognome());
		}
		if(u.getNome()!=null){
			sb.append("nome = ?,");
			params.add(u.getNome());
		}
		if(u.getRuolo().getCodice()!=null){
			sb.append("ruolo = ?,");
			params.add(u.getRuolo().getCodice());
		}
		if(u.getDataNascita()!=null){
			sb.append("data_nascita = ?,");
			params.add(u.getDataNascita());
		}
		if(u.getDataRegistrazione()!=null){
			sb.append("data_registrazione = ?,");
			params.add(u.getDataRegistrazione());
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append(" WHERE username = ?");
		params.add(u.getUsername());
		
		log.debug("sql: "+sb.toString());
		log.debug("utente = "+u);
		
		int updated = jdbcTemplate.update(sb.toString(),params.toArray());
		
		if(updated>0){
			wasUpdated = true;
		}
		
		return wasUpdated;	
	}
	
}
