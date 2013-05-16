package it.geek.resid.service;

import java.util.List;

import org.apache.log4j.Logger;

import it.geek.resid.dao.UtenteDaoInterface;
import it.geek.resid.exception.BusinessException;
import it.geek.resid.model.Ruolo;
import it.geek.resid.model.Utente;

public class UtenteServiceTX implements UtenteServiceInterface {

	private Logger logger = Logger.getLogger(UtenteServiceTX.class);
	
	private UtenteDaoInterface utenteDAO;
	
	public void setUtenteDAO(UtenteDaoInterface utenteDAO){
		this.utenteDAO = utenteDAO;
	}
	
	public Utente get(String k) {
		logger.info("UtenteService::get(k)");
		
		Utente utente = null;
		
		try {
			
			utente = utenteDAO.findById(k);
			
		} catch (Exception e) {
			logger.error("errore inaspettato: "+e);
			throw new BusinessException(e.getMessage());
		}
		
		return utente;
	}

	public List<Utente> getAll() {
		logger.info("UtenteService::getAll()");
		
		List<Utente> utenti = null;
		
		try {
			
			utenti = utenteDAO.findAll();
			
		} catch (Exception e) {
			logger.error("errore inaspettato: "+e);
			throw new BusinessException(e.getMessage());
		}
		
		return utenti;
	}

	@Override
	public void multiSave(String[] ids, Ruolo ruolo) {
		logger.info("multiSave");
		
		try {
			//eseguo con un ciclo tutte le delete
			Utente u = new Utente();
			u.setRuolo(ruolo);
			int i=0; //test transazione
			for(String id : ids){
					
				u.setUsername(id);
				if(i==2) throw new RuntimeException("test eseguito!"); //test transazione
				
				if(id == null){
					throw new BusinessException("utente non identificabile... [usedrname:"+" id]");
				}
				
				Utente uf = utenteDAO.findById(id);
				boolean wasSaved = false;
				
				if(uf==null){
					throw new BusinessException("utente non trovato! [usedrname:"+" id]");
					
				}else{
					wasSaved = utenteDAO.update(u);
					i++; //test transazione
				}
				
				if(!wasSaved){
					throw new BusinessException("non è stato possibile modificare l'utente... [usedrname:"+" id]");
				}
					
			}
			
			
		} catch (Exception e) {
			logger.error("errore: "+e);
			throw new BusinessException(e);
		}
	}

	public void create(Utente u){
		logger.info("UtenteServiceTX::create");
		
		try {
			
			if(u == null || u.getUsername()==null){
				throw new BusinessException("utente non identificabile...");
			}
			
			Utente uf = utenteDAO.findById(u.getUsername());
			boolean wasCreated = false;
			
			if(uf==null){
				wasCreated = utenteDAO.insert(u);
			}else{
				throw new BusinessException("utente già registrato!");
			}
			
			if(!wasCreated){
				throw new BusinessException("non è stato possibile inserire l'utente...");
			}
			
		} catch (Exception e) {
			logger.error("errore inaspettato: "+e);
			throw new BusinessException(e.getMessage());
		} 
	}
}

