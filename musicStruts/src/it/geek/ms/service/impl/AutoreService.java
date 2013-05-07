package it.geek.ms.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import it.geek.ms.model.Autore;
import it.geek.ms.service.Service;
import it.geek.ms.dao.IDAO;
import it.geek.ms.exception.BusinessException;
import it.geek.ms.factory.DaoFactory;
import it.geek.ms.util.MyJNDIConnection;

public class AutoreService implements Service<Autore, String> {

	private Logger log = Logger.getLogger(AutoreService.class);
	
	@Override
	public Autore get(String id) {
		log.info("AutoreService::get(k)");
		
		Autore autore = null;
		Connection conn = null;
		
		try {
			
			conn = MyJNDIConnection.getConnection();
			IDAO dao = DaoFactory.getAutoreDAO();
			autore = (Autore)dao.findById(id,conn);
			log.debug("autore estratto: "+autore);
			
		} catch (Exception e) {
			log.error("errore inaspettato: "+e);
			throw new BusinessException(e.getCause()+" - "+e.getMessage());
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				log.error("impossibile chiudere la connessione: "+e);
				throw new BusinessException(e.getCause()+" - "+e.getMessage());
			}
		}
		
		return autore;	
	}

	@Override
	public List<Autore> get(Autore a) {
		log.info("AutoreService::get(e)");
		
		List<Autore> autori = null;
		Connection conn = null;
		
		try {
			
			conn = MyJNDIConnection.getConnection();
			IDAO dao = DaoFactory.getAutoreDAO();
			autori = (List<Autore>)dao.findByExample(a, conn);
			
		} catch (Exception e) {
			log.error("errore inaspettato: "+e);
			throw new BusinessException(e.getCause()+" - "+e.getMessage());
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				log.error("impossibile chiudere la connessione: "+e);
				throw new BusinessException(e.getCause()+" - "+e.getMessage());
			}
		}
		
		return autori;
	}

	@Override
	//in realtà, per il modello scelto, il significato di questa operazione è "findAllFree"...
	public List<Autore> getAll() {
		log.info("AutoreService::getAll");
		
		List<Autore> autori = null;
		Connection conn = null;
		
		try {
			
			conn = MyJNDIConnection.getConnection();
			IDAO dao = DaoFactory.getAutoreDAO();
			autori = (List<Autore>)dao.findAll(conn);
			
			Iterator<Autore> it = autori.iterator();
			
			while(it.hasNext()){
				Autore a = it.next();
				if(a.getCasaDiscografica()!=null)
					it.remove();
			}
			
		} catch (Exception e) {
			log.error("errore inaspettato: "+e);
			throw new BusinessException(e.getCause()+" - "+e.getMessage()+" ["+e+"]");
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				log.error("impossibile chiudere la connessione: "+e);
				throw new BusinessException(e.getCause()+" - "+e.getMessage()+" ["+e+"]");
			}
		}
		
		return autori;
	}

	@Override
	public void delete(String k) {
		log.info("delete");
		
		Connection conn = null;
		
		try {
			
			conn = MyJNDIConnection.getConnection();
			IDAO dao = DaoFactory.getAutoreDAO();
			boolean wasDeleted = dao.delete(k,conn);
			
			if(!wasDeleted){
				throw new BusinessException("non è stato possibile eliminare il record...");
			}
			
		} catch (Exception e) {
			log.error("errore inaspettato: "+e);
			throw new BusinessException(e.getMessage()+" ["+e+"]");
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				log.error("impossibile chiudere la connessione: "+e);
				throw new BusinessException(e.getMessage()+" ["+e+"]");
			}
		}
		
	}

	@Override
	public void save(Autore a) {
		log.info("save");
		
		Connection conn = null;
		
		try {
			
			conn = MyJNDIConnection.getConnection();
			IDAO dao = DaoFactory.getAutoreDAO();
			
			if(a == null || a.getCodiceFiscale()==null){
				throw new BusinessException("autore non identificabile...");
			}
			
			Autore af = (Autore)dao.findById(a.getCodiceFiscale(), conn);
			boolean wasSaved = false;
			
			if(af==null){
				throw new BusinessException("autore non trovato!");
				
			}else{
				wasSaved = dao.update(a,conn);
			}
			
			if(!wasSaved){
				throw new BusinessException("non è stato possibile modificare l'autore...");
			}
			
		} catch (Exception e) {
			log.error("errore inaspettato: "+e);
			throw new BusinessException(e.getMessage()+" ["+e+"]");
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				log.error("impossibile chiudere la connessione: "+e);
				throw new BusinessException(e.getMessage()+" ["+e+"]");
			}
		}	
	}

	@Override
	public void create(Autore a) {
		log.info("create");
		
		Connection conn = null;
		
		try {
			
			conn = MyJNDIConnection.getConnection();
			IDAO dao = DaoFactory.getAutoreDAO();
			
			if(a == null || a.getCodiceFiscale()==null){
				throw new BusinessException("autore non identificabile...");
			}
			
			Autore af = (Autore)dao.findById(a.getCodiceFiscale(), conn);
			boolean wasCreated = false;
			
			//il modello prevede che la insert in realtà sia una update...
			if(af.getCasaDiscografica()==null){
				wasCreated = dao.insert(a,conn);
			}else{
				throw new BusinessException("autore già registrato presso la casa discografica '"+af.getCasaDiscografica()+"'!");
			}
			
			if(!wasCreated){
				throw new BusinessException("non è stato possibile inserire l'autore...");
			}
			
		} catch (Exception e) {
			log.error("errore inaspettato: "+e);
			throw new BusinessException(e.getMessage()+" ["+e+"]");
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				log.error("impossibile chiudere la connessione: "+e);
				throw new BusinessException(e.getMessage()+" ["+e+"]");
			}
		}	
	}

}
