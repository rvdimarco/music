package it.geek.ms.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import it.geek.ms.dao.IDAO;
import it.geek.ms.exception.BusinessException;
import it.geek.ms.factory.DaoFactory;
import it.geek.ms.model.CasaDiscografica;
import it.geek.ms.model.Utente;
import it.geek.ms.service.Service;
import it.geek.ms.util.MyJNDIConnection;

public class CasaDiscograficaService implements Service<CasaDiscografica, String> {

	private Logger log = Logger.getLogger(CasaDiscograficaService.class);
	
	@Override
	public CasaDiscografica get(String id) {
		log.info("CasaDiscograficaService::get(k)");
		
		CasaDiscografica casa = null;
		Connection conn = null;
		
		try {
			
			conn = MyJNDIConnection.getConnection();
			IDAO dao = DaoFactory.getCasaDiscograficaDAO();
			casa = (CasaDiscografica)dao.findById(id, conn);
			log.debug("casa discografica estratta: "+casa);
			
		} catch (Exception e) {
			log.error("errore inaspettato: "+e);
			throw new BusinessException(e.getMessage());
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				log.error("impossibile chiudere la connessione: "+e);
				throw new BusinessException(e.getMessage());
			}
		}
		
		return casa;
	}

	@Override
	public List<CasaDiscografica> get(CasaDiscografica c) {
		throw new UnsupportedOperationException("operazione non implementata");
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
			log.error("errore inaspettato: "+e);
			throw new BusinessException(e.getMessage());
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				log.error("impossibile chiudere la connessione: "+e);
				throw new BusinessException(e.getMessage());
			}
		}
		
		return cadili;
	}

	@Override
	public void delete(String k) {
		throw new UnsupportedOperationException("operazione non implementata");
		
	}

	@Override
	public void save(CasaDiscografica e) {
		throw new UnsupportedOperationException("operazione non implementata");
		
	}

	@Override
	public void create(CasaDiscografica e) {
		throw new UnsupportedOperationException("operazione non implementata");
		
	}

}
