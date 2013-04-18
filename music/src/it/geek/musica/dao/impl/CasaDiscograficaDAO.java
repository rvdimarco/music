package it.geek.musica.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import it.geek.musica.dao.IDAO;
import it.geek.musica.model.Autore;
import it.geek.musica.model.CasaDiscografica;
import it.geek.musica.util.MyJNDIConnection;

public class CasaDiscograficaDAO implements IDAO<CasaDiscografica, String> {

	@Override
	public CasaDiscografica findById(String id, Connection c) {
		
		CasaDiscografica casa = null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT  c.nome    as nome_casa, ");
				sb.append("c.sede    as sede_casa, ");
				sb.append("a.cf      as cf_autore, ");
				sb.append("a.nome    as nome_autore, ");
				sb.append("a.cognome as cognome_autore ");
		sb.append("FROM case_discografiche c, autori a ");
		sb.append("WHERE c.nome = a.casa_discografica ");
		sb.append("AND   c.nome = ?");
		
		try{
			ps = c.prepareStatement(sb.toString());
			ps.setString(1, id);
			
			System.out.println("sql: "+sb.toString());
			System.out.println("c.nome = "+id);
			
			rs = ps.executeQuery();
			
			Autore a = null;
			if(rs.next()){
				casa = new CasaDiscografica();
					casa.setNome(rs.getString("nome_casa"));
					casa.setSede(rs.getString("sede_casa"));
				a = new Autore();
					a.setCodiceFiscale(rs.getString("cf_autore"));
					a.setNome(rs.getString("nome_autore"));
					a.setCognome(rs.getString("cognome_autore"));
				casa.addAutore(a);
			}
			while(rs.next()){
				a = new Autore();
					a.setCodiceFiscale(rs.getString("cf_autore"));
					a.setNome(rs.getString("nome_autore"));
					a.setCognome(rs.getString("cognome_autore"));
				casa.addAutore(a);
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
		
		return casa;
	}

	@Override
	public List<CasaDiscografica> findAll(Connection c) {

		List<CasaDiscografica> ret = null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql ="SELECT nome, sede FROM case_discografiche";
		
		try{
			ps = c.prepareStatement(sql);
			rs = ps.executeQuery();
			
			ret = new ArrayList<CasaDiscografica>();
			CasaDiscografica cadi = null;
			while(rs.next()){
				cadi = new CasaDiscografica();
				cadi.setNome(rs.getString("nome"));
				cadi.setSede(rs.getString("sede"));
				ret.add(cadi);
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(CasaDiscografica e, Connection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(CasaDiscografica e, Connection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<CasaDiscografica> findByExample(CasaDiscografica e, Connection c) {
		// TODO Auto-generated method stub
		return null;
	}



}
