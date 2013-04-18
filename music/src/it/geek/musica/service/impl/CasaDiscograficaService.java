package it.geek.musica.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import it.geek.musica.dao.IDAO;
import it.geek.musica.factory.DaoFactory;
import it.geek.musica.model.CasaDiscografica;
import it.geek.musica.model.Utente;
import it.geek.musica.service.Service;
import it.geek.musica.util.MyJNDIConnection;

public class CasaDiscograficaService implements Service<CasaDiscografica, String> {

	private Logger logger = Logger.getLogger(UtenteService.class);
	
	@Override
	public CasaDiscografica get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CasaDiscografica> get(CasaDiscografica c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CasaDiscografica> getAll() {
		List<CasaDiscografica> cadili = null;
		Connection conn = null;
		
		try {
			
			conn = MyJNDIConnection.getConnection();
			IDAO dao = DaoFactory.getCasaDiscograficaDAO();
			cadili = (List<CasaDiscografica>)dao.findAll(conn);
			
		} catch (Exception e) {
			logger.error("errore inaspettato: "+e);
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("impossibile chiudere la connessione: "+e);
			}
		}
		
		return cadili;
	}

}
