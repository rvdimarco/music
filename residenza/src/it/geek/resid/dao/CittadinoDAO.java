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

public class CittadinoDAO {
	
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

}
