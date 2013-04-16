package it.geek.musica.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import it.geek.musica.dao.IDAO;
import it.geek.musica.model.Autore;
import it.geek.musica.util.MyJNDIConnection;

public class AutoreDAO implements IDAO<Autore, String> {

	@Override
	public Autore findById(String id) {
		Autore autore = null;
		
		Connection c = MyJNDIConnection.getConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT  a.cf      as cf_autore, ");
				sb.append("a.nome    as nome_autore, ");
				sb.append("a.cognome as cognome_autore ");
		sb.append("FROM autori a ");
		sb.append("WHERE a.cf = ? ");
		
		try{
			ps = c.prepareStatement(sb.toString());
			ps.setString(1, id);
			
			System.out.println("sql: "+sb.toString());
			System.out.println("a.cf = "+id);
			
			rs = ps.executeQuery();
			
			Autore a = null;
			if(rs.next()){
				a = new Autore();
				a.setCodiceFiscale(rs.getString("cf_autore"));
				a.setNome(rs.getString("nome_autore"));
				a.setCognome(rs.getString("cognome_autore"));
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
			try {
				c.close();
			} catch (Exception e2) {
				System.out.println("impossibile chiudere la Connection");
			}			
		}
		
		return autore;
	}

	@Override
	public List<Autore> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(String id) {
		boolean wasDeleted = false;
		
		Connection c = MyJNDIConnection.getConnection();
		
		PreparedStatement ps = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE ");
		sb.append("FROM autori a ");
		sb.append("WHERE a.cf = ? ");
		
		try{
			ps = c.prepareStatement(sb.toString());
			ps.setString(1, id);
			
			System.out.println("sql: "+sb.toString());
			System.out.println("a.cf = "+id);
			
			int deleted = ps.executeUpdate();
			
			if(deleted>0){
				wasDeleted = true;
			}
			
		}catch(Exception e){
			System.out.println("errore! "+e);
			e.printStackTrace();
			
		}finally{
			try {
				ps.close();
			} catch (Exception e2) {
				System.out.println("impossibile chiudere il PreparedStatement");
			}
			try {
				c.close();
			} catch (Exception e2) {
				System.out.println("impossibile chiudere la Connection");
			}			
		}
		
		return wasDeleted;
	}

	@Override
	public boolean insert(Autore a) {
		boolean wasInserted = false;
		
		Connection c = MyJNDIConnection.getConnection();
		
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
			
			System.out.println("sql: "+sb.toString());
			System.out.println("cf = "+a.getCodiceFiscale());
			System.out.println("nome = "+a.getNome());
			System.out.println("cognome = "+a.getCognome());
			System.out.println("casa_discografica = "+a.getCasaDiscografica());
			
			int inserted = ps.executeUpdate();
			
			if(inserted>0){
				wasInserted = true;
			}
			
		}catch(Exception e){
			System.out.println("errore! "+e);
			e.printStackTrace();
			
		}finally{
			try {
				ps.close();
			} catch (Exception e2) {
				System.out.println("impossibile chiudere il PreparedStatement");
			}
			try {
				c.close();
			} catch (Exception e2) {
				System.out.println("impossibile chiudere la Connection");
			}			
		}
		
		return wasInserted;
	}

	@Override
	public boolean update(Autore a) {
		boolean wasUpdated = false;
		
		Connection c = MyJNDIConnection.getConnection();
		
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
			
			
			System.out.println("sql: "+sb.toString());
			System.out.println("autore = "+a);
			
			int updated = ps.executeUpdate();
			
			if(updated>0){
				wasUpdated = true;
			}
			
		}catch(Exception e){
			System.out.println("errore! "+e);
			e.printStackTrace();
			
		}finally{
			try {
				ps.close();
			} catch (Exception e2) {
				System.out.println("impossibile chiudere il PreparedStatement");
			}
			try {
				c.close();
			} catch (Exception e2) {
				System.out.println("impossibile chiudere la Connection");
			}			
		}
		
		return wasUpdated;
	}

}
