package it.geek.resid.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import it.geek.resid.model.InfoRegione;

import org.springframework.jdbc.core.RowMapper;

public class InfoRegioneRowMapper implements RowMapper<InfoRegione> {

	@Override
	public InfoRegione mapRow(ResultSet rs, int rowNum) throws SQLException {
		InfoRegione info = new InfoRegione();
		info.setRegione(rs.getString("regione"));
		info.setNumAbitanti(rs.getInt("num_abitanti"));
		info.setNumResidenti(rs.getInt("num_residenti"));
	    return info;
	}

}
