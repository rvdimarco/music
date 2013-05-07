package it.geek.ms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import it.geek.ms.controller.LoginAction;
import it.geek.ms.dao.IDAO;
import it.geek.ms.model.Autore;
import it.geek.ms.model.CasaDiscografica;

public class CasaDiscograficaDAO implements IDAO<CasaDiscografica, String> {
	
	private static Logger log = Logger.getLogger(CasaDiscograficaDAO.class);

	@Override
	public CasaDiscografica findById(String id, Connection c) throws SQLException{
		log.info("findById");
		CasaDiscografica casa = null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT  c.nome    as nome_casa, ");
				sb.append("c.sede    as sede_casa, ");
				sb.append("a.cf      as cf_autore, ");
				sb.append("a.nome    as nome_autore, ");
				sb.append("a.cognome as cognome_autore ");
		sb.append("FROM case_discografiche c LEFT JOIN autori a ");
		sb.append("ON  c.nome = a.casa_discografica  ");
		sb.append("WHERE   c.nome = ?");
		
		try{
			ps = c.prepareStatement(sb.toString());
			ps.setString(1, id);
			
			log.debug("sql: "+sb.toString());
			log.debug("c.nome = "+id);
			
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
			
		}catch(SQLException e){
			log.error("errore! "+e);
			throw e;
			
		}finally{
			try {
				rs.close();
			} catch (Exception e2) {
				log.error("impossibile chiudere il ResultSet");
				throw e2;
			}
			try {
				ps.close();
			} catch (Exception e2) {
				log.error("impossibile chiudere il PreparedStatement");
				throw e2;
			}
		}
		
		return casa;
	}

	@Override
	public List<CasaDiscografica> findAll(Connection c) throws SQLException{
		log.info("findAll");
		List<CasaDiscografica> ret = null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql ="SELECT nome, sede FROM case_discografiche";
		log.debug("sql: "+ sql);
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
			
		}catch(SQLException e){
			log.error("errore! "+e);
			throw e;
			
		}finally{
			try {
				rs.close();
			} catch (Exception e2) {
				log.error("impossibile chiudere il ResultSet");
			}
			try {
				ps.close();
			} catch (Exception e2) {
				log.error("impossibile chiudere il PreparedStatement");
			}
		}
		
		return ret;
	
	}

	@Override
	public boolean delete(String id, Connection c) {
		throw new UnsupportedOperationException("operazione non implementata");
	}

	@Override
	public boolean insert(CasaDiscografica e, Connection c) {
		throw new UnsupportedOperationException("operazione non implementata");
	}

	@Override
	public boolean update(CasaDiscografica e, Connection c) {
		throw new UnsupportedOperationException("operazione non implementata");
	}

	@Override
	public List<CasaDiscografica> findByExample(CasaDiscografica e, Connection c) {
		throw new UnsupportedOperationException("operazione non implementata");
	}



}
