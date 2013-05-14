package it.geek.resid.dao;

import it.geek.resid.model.InfoRegione;

import java.sql.Connection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

public class InfoRegioneSpringDAO implements InfoRegioneDaoInterface {
	
	Logger log = Logger.getLogger(InfoRegioneSpringDAO.class);
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<InfoRegione> findAll(Connection c) {
		log.info("InfoRegioneSpringDAO::findAll");
		
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
		
		List<InfoRegione> infos = 
				(List<InfoRegione>) jdbcTemplate.query(sb.toString(), new InfoRegioneRowMapper());
		
		return infos;
	}

}
