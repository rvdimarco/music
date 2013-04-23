package it.geek.musica.dao.impl;

import it.geek.musica.dao.IDAO;
import it.geek.musica.model.Ruolo;
import it.geek.musica.model.Utente;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAO implements IDAO<Utente, String> {

	@Override
	public Utente findById(String id, Connection c) {
	
		Utente ret = null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT username, password, cognome, nome, codice, descrizione ");
		sb.append("FROM utenti, ruoli ");
		sb.append("WHERE id_ruolo = codice ");
		sb.append("AND username = ?");
		
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
			System.out.println("errore! "+e);
			e.printStackTrace();
		}finally{
			try {
				rs.close();
			} catch (Exception e2) {
				System.out.println("impossibile chiudere il ResultSet");
			}
			try {
				ps.close();
			} catch (Exception e2) {
				System.out.println("impossibile chiudere il PreparedStatement");
			}
		}
		
		return ret;
	}

	@Override
	public List<Utente> findAll(Connection c) {
		List<Utente> ret = null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT username, password, cognome, nome, codice, descrizione ");
		sb.append("FROM utenti, ruoli ");
		sb.append("WHERE id_ruolo = codice ");
		
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
			System.out.println("errore! "+e);
			e.printStackTrace();
		}finally{
			try {
				rs.close();
			} catch (Exception e2) {
				System.out.println("impossibile chiudere il ResultSet");
			}
			try {
				ps.close();
			} catch (Exception e2) {
				System.out.println("impossibile chiudere il PreparedStatement");
			}
		}
		
		return ret;
	}

	@Override
	public boolean delete(String id, Connection c) {
		throw new UnsupportedOperationException("UtenteDAO::delete:: metodo non implementato");
	}

	@Override
	public boolean insert(Utente e, Connection c) {
		throw new UnsupportedOperationException("UtenteDAO::insert:: metodo non implementato");
	}

	@Override
	public boolean update(Utente e, Connection c) {
		throw new UnsupportedOperationException("UtenteDAO::update:: metodo non implementato");
	}

	@Override
	public List<Utente> findByExample(Utente ex, Connection c) {
		throw new UnsupportedOperationException("UtenteDAO::findByExample:: metodo non implementato");
	}

}
