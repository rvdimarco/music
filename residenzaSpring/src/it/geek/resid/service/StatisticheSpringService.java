package it.geek.resid.service;

import it.geek.resid.dao.InfoRegioneDaoInterface;
import it.geek.resid.exception.BusinessException;
import it.geek.resid.model.InfoRegione;

import java.util.List;

import org.apache.log4j.Logger;

public class StatisticheSpringService implements StatisticheServiceInterface{
	
	private Logger logger = Logger.getLogger(StatisticheSpringService.class);

	private InfoRegioneDaoInterface infoRegioneDAO;
	
	public void setInfoRegioneDAO(InfoRegioneDaoInterface infoRegioneDAO){
		this.infoRegioneDAO = infoRegioneDAO;
	}
	
	@Override
	public List<InfoRegione> getInfoRegioneAll() {
		logger.info("StatisticheServiceSpring::getInfoRegioneAll()");
		
		List<InfoRegione> info = null;
		
		try {
			info = infoRegioneDAO.findAll(null);
			
		} catch (Exception e) {
			logger.error("errore inaspettato: "+e);
			throw new BusinessException(e.getMessage());
		} 
		
		return info;
	}

	
	
}
