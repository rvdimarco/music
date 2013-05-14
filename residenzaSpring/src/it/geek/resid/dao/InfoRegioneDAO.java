package it.geek.resid.dao;

import it.geek.resid.exception.BusinessException;
import it.geek.resid.model.InfoRegione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

public class InfoRegioneDAO implements InfoRegioneDaoInterface{
	
	Logger log = Logger.getLogger(InfoRegioneDAO.class);
	
	public List<InfoRegione> findAll(Connection c){
		log.info("InfoRegioneDAO::findAll");
		List<InfoRegione> ret = null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT r.descrizione as regione,  ");
		       sb.append("sum(c.num_abitanti) as num_abitanti, ");
		       sb.append("count(ctd.cf) as num_residenti ");
		sb.append("FROM regioni r ");
		sb.append("LEFT JOIN province p ON p.cod_regione=r.codice ");
		sb.append("LEFT JOIN citta c ON c.cod_provincia=p.codice ");
		sb.append("LEFT JOIN cittadini ctd ON ctd.residenza=c.codice ");
		sb.append("GROUP BY regione ");
		
		log.debug("sql::"+sb.toString());
		
		try{
			ps = c.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			ret = new ArrayList<InfoRegione>();
			InfoRegione info = null;
			while(rs.next()){
				info = new InfoRegione();
				info.setRegione(rs.getString("regione"));
				info.setNumAbitanti(rs.getInt("num_abitanti"));
				info.setNumResidenti(rs.getInt("num_residenti"));
				ret.add(info);
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
