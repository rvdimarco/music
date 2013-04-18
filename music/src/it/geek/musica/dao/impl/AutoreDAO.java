package it.geek.musica.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import it.geek.musica.dao.IDAO;
import it.geek.musica.model.Autore;
import it.geek.musica.util.MyJNDIConnection;

public class AutoreDAO implements IDAO<Autore, String> {

	private static Logger logger = Logger.getLogger(AutoreDAO.class);
	
	@Override
	public Autore findById(String id, Connection c) {
		logger.info("AutoreDAO::findById");
		Autore autore = null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT  cf, nome, cognome, casa_discografica ");
		sb.append("FROM autori ");
		sb.append("WHERE cf = ? ");
		
		try{
			ps = c.prepareStatement(sb.toString());
			ps.setString(1, id);
			
			logger.debug("sql: "+sb.toString());
			logger.debug("cf = "+id);
			
			rs = ps.executeQuery();
			
			Autore a = null;
			if(rs.next()){
				a = new Autore();
				a.setCodiceFiscale(rs.getString("cf"));
				a.setNome(rs.getString("nome"));
				a.setCognome(rs.getString("cognome"));
				a.setCasaDiscografica(rs.getString("casa_discografica"));
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
		
		return autore;
	}

	@Override
	public List<Autore> findAll(Connection c) {
		logger.info("AutoreDAO::findAll");
		List<Autore> autori = null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT  cf, nome, cognome, casa_discografica ");
		sb.append("FROM autori ");
		
		try{
			ps = c.prepareStatement(sb.toString());
			
			logger.debug("sql: "+sb.toString());
			
			rs = ps.executeQuery();
			
			Autore a = null;
			autori = new ArrayList<Autore>();
			while(rs.next()){
				a = new Autore();
				a.setCodiceFiscale(rs.getString("cf"));
				a.setNome(rs.getString("nome"));
				a.setCognome(rs.getString("cognome"));
				a.setCasaDiscografica(rs.getString("casa_discografica"));
				autori.add(a);
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
		
		return autori;
	}

	@Override
	public boolean delete(String id, Connection c) {
		logger.info("AutoreDAO::delete");
		boolean wasDeleted = false;
		
		if(id==null){
			return wasDeleted;
		}
		
		PreparedStatement ps = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE ");
		sb.append("FROM autori ");
		sb.append("WHERE cf = ? ");
		
		try{
			ps = c.prepareStatement(sb.toString());
			ps.setString(1, id);
			
			logger.debug("sql: "+sb.toString());
			logger.debug("cf = "+id);
			
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
		
		return wasDeleted;
	}

	@Override
	public boolean insert(Autore a, Connection c) {
		logger.info("AutoreDAO::insert");
		boolean wasInserted = false;
		
		if(a==null || a.getCodiceFiscale()==null || a.getNome()==null || a.getCognome()==null || a.getCasaDiscografica()==null ){
			return wasInserted;
		}
		
		PreparedStatement ps = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO autori (cf, nome, cognome, casa_discografica) ");
		sb.append("VALUES (?,?,?,?)");
		
		try{
			ps = c.prepareStatement(sb.toString());
			ps.setString(1, a.getCodiceFiscale());
			ps.setString(2, a.getNome());
			ps.setString(3, a.getCognome());
			ps.setString(4, a.getCasaDiscografica());
			
			logger.debug("sql: "+sb.toString());
			logger.debug("cf = "+a.getCodiceFiscale());
			logger.debug("nome = "+a.getNome());
			logger.debug("cognome = "+a.getCognome());
			logger.debug("casa_discografica = "+a.getCasaDiscografica());
			
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
		
		return wasInserted;
	}

	@Override
	public boolean update(Autore a, Connection c) {
		logger.info("AutoreDAO::update");
		boolean wasUpdated = false;
		
		if(a==null || a.getCodiceFiscale()==null){
			return wasUpdated;
		}
		
		PreparedStatement ps = null;
		
		if(a==null || (a.getNome()==null && a.getCognome()==null && a.getCasaDiscografica()==null)){
			return wasUpdated;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE autori SET ");
		if(a.getNome()!=null){
			sb.append("nome = ?,");
		}
		if(a.getCognome()!=null){
			sb.append("cognome = ?,");
		}
		if(a.getCognome()==null){
			sb.append("casa_discografica = ?,");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append("WHERE cf = ?");
		
		try{
			ps = c.prepareStatement(sb.toString());
			int i = 0;
			if(a.getNome()!=null){
				ps.setString(++i, a.getNome());
			}
			if(a.getCognome()!=null){
				ps.setString(++i, a.getCognome());
			}
			if(a.getCognome()==null){
				ps.setString(++i, a.getCasaDiscografica());
			}
			ps.setString(++i, a.getCodiceFiscale());
			
			
			logger.debug("sql: "+sb.toString());
			logger.debug("autore = "+a);
			
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
		
		return wasUpdated;
	}

	@Override
	public List<Autore> findByExample(Autore ex, Connection c) {
		logger.info("AutoreDAO::findByExample");
		List<Autore> autori = null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT  cf, nome, cognome, casa_discografica ");
		sb.append("FROM autori WHERE 1=1 ");
		
		if(ex.getCodiceFiscale()!=null){
			sb.append("AND cf = ? ");
		}
		if(ex.getNome()!=null){
			sb.append("AND nome = ? ");
		}
		if(ex.getCognome()!=null){
			sb.append("AND cognome = ? ");
		}
		if(ex.getCasaDiscografica()!=null){
			sb.append("AND casa_discografica = ? ");
		}
		
		try{
			ps = c.prepareStatement(sb.toString());
			int i = 0;
			if(ex.getCodiceFiscale()!=null){
				ps.setString(++i, ex.getCodiceFiscale());
			}
			if(ex.getNome()!=null){
				ps.setString(++i, ex.getNome());
			}
			if(ex.getCognome()!=null){
				ps.setString(++i, ex.getCognome());
			}
			if(ex.getCasaDiscografica()!=null){
				ps.setString(++i, ex.getCasaDiscografica());
			}
			
			logger.debug("sql: "+sb.toString());
			logger.debug("autore = "+ex);
			
			rs = ps.executeQuery();
			
			Autore a = null;
			autori = new ArrayList<Autore>();
			while(rs.next()){
				a = new Autore();
				a.setCodiceFiscale(rs.getString("cf"));
				a.setNome(rs.getString("nome"));
				a.setCognome(rs.getString("cognome"));
				a.setCasaDiscografica(rs.getString("casa_discografica"));
				autori.add(a);
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
		
		return autori;
	}

}
