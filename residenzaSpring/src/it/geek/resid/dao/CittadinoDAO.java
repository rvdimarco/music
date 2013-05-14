package it.geek.resid.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import it.geek.resid.exception.BusinessException;
import it.geek.resid.model.Cittadino;

public class CittadinoDAO implements CittadinoDaoInterface{
	
	Logger log = Logger.getLogger(CittadinoDAO.class);

	public List<Cittadino> findAll(Connection c){
		log.info("CittadinoDAO::findAll");
		List<Cittadino> ret = null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT cf, nominativo, impiego, descrizione ");
		sb.append("FROM cittadini, citta ");
		sb.append("WHERE residenza = codice ");
		
		log.debug("sql::"+sb.toString());
		
		try{
			ps = c.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			ret = new ArrayList<Cittadino>();
			Cittadino ci = null;
			while(rs.next()){
				ci = new Cittadino();
				ci.setCodiceFiscale(rs.getString("cf"));
				ci.setNominativo(rs.getString("nominativo"));
				ci.setImpiego(rs.getString("impiego"));
				ci.setResidenza(rs.getString("descrizione"));
				ret.add(ci);
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

	public Cittadino findById(String id, Connection c) {
		log.info("CittadinoDAO::findById");
		Cittadino ret = null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT cf, nominativo, impiego, descrizione ");
		sb.append("FROM cittadini, citta ");
		sb.append("WHERE residenza = codice ");
		sb.append("AND cf = ?");
		
		log.debug("sql::"+sb.toString());
		log.debug("cf = "+id);
		
		try{
			ps = c.prepareStatement(sb.toString());
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				ret = new Cittadino();
				ret.setCodiceFiscale(rs.getString("cf"));
				ret.setNominativo(rs.getString("nominativo"));
				ret.setImpiego(rs.getString("impiego"));
				ret.setResidenza(rs.getString("descrizione"));
			}
		}catch(Exception e){
			log.error("errore! "+e);
			throw new BusinessException(e);
		}finally{
			try {
				rs.close();
			} catch (Exception e) {
				log.error("impossibile chiudere il ResultSet");
				throw new BusinessException(e);
			}
			try {
				ps.close();
			} catch (Exception e) {
				log.error("impossibile chiudere il PreparedStatement");
				throw new BusinessException(e);
			}
		}
		
		return ret;
	}
	
	public boolean update(Cittadino ci, Connection c) {
		log.info("CittadinoDAO::update");
		boolean wasUpdated = false;
		
		if(ci==null || ci.getCodiceFiscale()==null){
			return wasUpdated;
		}
		
		PreparedStatement ps = null;
		
		if(ci.getNominativo()==null && ci.getImpiego()==null && ci.getCodCitta()==null){
			return wasUpdated;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE cittadini SET ");
		if(ci.getNominativo()!=null){
			sb.append("nominativo = ?,");
		}
		if(ci.getImpiego()!=null){
			sb.append("impiego = ?,");
		}
		if(ci.getCodCitta()!=null){
			sb.append("residenza = ?,");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append(" WHERE cf = ?");
		
		try{
			ps = c.prepareStatement(sb.toString());
			int i = 0;
			if(ci.getNominativo()!=null){
				ps.setString(++i, ci.getNominativo());
			}
			if(ci.getImpiego()!=null){
				ps.setString(++i, ci.getImpiego());
			}
			if(ci.getCodCitta()!=null){
				ps.setInt(++i, ci.getCodCitta());
			}
			ps.setString(++i, ci.getCodiceFiscale());
			
			log.debug("sql: "+sb.toString());
			log.debug("cittadino = "+ci);
			
			int updated = ps.executeUpdate();
			
			if(updated>0){
				wasUpdated = true;
			}
			
		}catch(Exception e){
			log.error("errore! "+e);
			throw new BusinessException(e);
		}finally{
			try {
				ps.close();
			} catch (Exception e) {
				log.error("impossibile chiudere il PreparedStatement");
				throw new BusinessException(e);
			}
		}
		
		return wasUpdated;	
	}
	
}
