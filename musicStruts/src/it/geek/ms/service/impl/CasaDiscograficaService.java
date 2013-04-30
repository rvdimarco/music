package it.geek.ms.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import it.geek.ms.dao.IDAO;
import it.geek.ms.factory.DaoFactory;
import it.geek.ms.model.CasaDiscografica;
import it.geek.ms.model.Utente;
import it.geek.ms.service.Service;
import it.geek.ms.util.MyJNDIConnection;

public class CasaDiscograficaService implements Service<CasaDiscografica, String> {

	private Logger logger = Logger.getLogger(CasaDiscograficaService.class);
	
	@Override
	public CasaDiscografica get(String id) {
		logger.info("CasaDiscograficaService::get(k)");
		
		CasaDiscografica casa = null;
		Connection conn = null;
		
		try {
			
			conn = MyJNDIConnection.getConnection();
			IDAO dao = DaoFactory.getCasaDiscograficaDAO();
			casa = (CasaDiscografica)dao.findById(id, conn);
			
		} catch (Exception e) {
			logger.error("errore inaspettato: "+e);
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("impossibile chiudere la connessione: "+e);
			}
		}
		
		return casa;
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

	@Override
	public void delete(String k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(CasaDiscografica e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create(CasaDiscografica e) {
		// TODO Auto-generated method stub
		
	}

}
