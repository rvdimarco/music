package it.geek.ms.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import it.geek.ms.dao.IDAO;
import it.geek.ms.factory.DaoFactory;
import it.geek.ms.model.Ruolo;
import it.geek.ms.service.Service;
import it.geek.ms.util.MyJNDIConnection;

public class RuoloService implements Service<Ruolo, Integer> {
	
	private Logger logger = Logger.getLogger(RuoloService.class);

	@Override
	public Ruolo get(Integer k) {
		throw new UnsupportedOperationException("RuoloService::get(k)::metodo non implementato");
	}

	@Override
	public List<Ruolo> get(Ruolo e) {
		throw new UnsupportedOperationException("RuoloService::get(e)::metodo non implementato");	}

	@Override
	public List<Ruolo> getAll() {
		logger.info("RuoloService::getAll");
		
		List<Ruolo> ruoli = null;
		Connection conn = null;
		
		try {
			
			conn = MyJNDIConnection.getConnection();
			IDAO dao = DaoFactory.getRuoloDAO();
			ruoli = (List<Ruolo>)dao.findAll(conn);
			
		} catch (Exception e) {
			logger.error("errore inaspettato: "+e);
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("impossibile chiudere la connessione: "+e);
			}
		}
		
		return ruoli;
	}

	@Override
	public void delete(Integer k) {
		throw new UnsupportedOperationException("RuoloService::delete::metodo non implementato");
	}

	@Override
	public void save(Ruolo e) {
		throw new UnsupportedOperationException("RuoloService::save::metodo non implementato");
	}

	@Override
	public void create(Ruolo e) {
		// TODO Auto-generated method stub
		
	}


}
