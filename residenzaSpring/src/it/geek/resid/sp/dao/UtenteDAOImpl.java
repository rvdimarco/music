package it.geek.resid.sp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;

import it.geek.resid.sp.exception.BusinessException;
import it.geek.resid.sp.pojo.Ruolo;
import it.geek.resid.sp.pojo.Utente;

public class UtenteDAOImpl implements UtenteDAO{
	
	Logger log = Logger.getLogger(UtenteDAOImpl.class);

	public Utente findById(String id, Connection c){
		log.info("UtenteDAO::findById");
		Utente ret = null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT username, password, cognome, nome, data_nascita, data_registrazione, cod_ruolo, desc_ruolo, attivo ");
		sb.append("FROM utenti, ruoli ");
		sb.append("WHERE ruolo = cod_ruolo ");
		sb.append("AND username = ?");
		
		log.debug("sql::"+sb.toString());
		log.debug("username = "+id);
		
		try{
			ps = c.prepareStatement(sb.toString());
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				ret = new Utente();
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
