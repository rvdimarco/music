package it.geek.resid.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import it.geek.resid.model.Ruolo;
import it.geek.resid.model.Utente;

import org.springframework.jdbc.core.RowMapper;

public class UtenteRowMapper implements RowMapper<Utente> {

	@Override
	public Utente mapRow(ResultSet rs, int rowNum) throws SQLException {
		Utente ret = new Utente();
		ret.setUsername(rs.getString("username"));
		ret.setPassword(rs.getString("password"));
		ret.setCognome(rs.getString("cognome"));
		ret.setNome(rs.getString("nome"));
		java.sql.Date dn = rs.getDate("data_nascita");
		if(dn!=null){
			ret.setDataNascita(new Date(dn.getTime()));
		}
		java.sql.Timestamp dr = rs.getTimestamp("data_registrazione");
		if(dr!=null){
			ret.setDataRegistrazione(new Date(dr.getTime()));
		}
		Ruolo r = new Ruolo();
		r.setCodice(rs.getInt("cod_ruolo"));
		r.setDescrizione(rs.getString("desc_ruolo"));
		r.setAttivo(rs.getBoolean("attivo"));
		ret.setRuolo(r);
		return ret;
	}

}
