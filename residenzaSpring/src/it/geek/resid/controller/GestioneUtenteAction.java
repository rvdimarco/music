package it.geek.resid.controller;

import java.util.List;

import it.geek.resid.form.UtenteForm;
import it.geek.resid.model.Utente;
import it.geek.resid.service.ServiceFactory;
import it.geek.resid.service.UtenteService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class GestioneUtenteAction extends DispatchAction {
	
	Logger log = Logger.getLogger(GestioneUtenteAction.class);

	public ActionForward registrazione(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception{
		log.debug("registrazione...");
		
		return mapping.findForward("registrazione");
	}
	
	public ActionForward eseguiRegistrazione(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception{
		log.debug("eseguiRegistrazione...");
		
			UtenteForm uform = (UtenteForm)form;
			log.debug("utente form:"+uform);
			Utente u = new Utente();
			
			/*
				//se voglio popolare l'utente senza l'uso di BeanUtils.copyProperties:
				//per i campi data
				String dataN = uform.getDataNascita();
				SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
				Date d = sd.parse(dataN);
				u.setDataNascita(d);
				//per gli altri campi
				u.setNome(uform.getNome());
				u.setCognome(uform.getCognome());
				//...per tutti i campi... !!!
			 */
			
			//IN ALTERNATIVA (uso di BeanUtils.copyProperties)
			//dal form arrivano stringhe, BeanUtils.copyProperties dovr� convertire una stringa in una data...
			//devo specificare la conversione per i campi di tipo java.util.Date
			java.util.Date defaultValue = null;
			DateConverter dateConverter = new DateConverter(defaultValue);
			dateConverter.setPattern("dd/MM/yyyy");
			ConvertUtils.register(dateConverter, java.util.Date.class);
			log.debug("Converter da Strina a java.util.date registrato!");
			
			BeanUtils.copyProperties(u, uform);
			log.debug("utente:"+u);
			
			//quando utilizzavo solo IoC, senza jdbcTemplate...
			//DaoFactory.getUtenteDao().insert(u);
			
			//ora, con jdbcTemplate
			ServiceFactory.getUtenteService().create(u);
			
			request.getSession().setAttribute("utenteSession",ServiceFactory.getUtenteService().get(u.getUsername()));
			log.debug("...login effettuata correttamente");
		
		return mapping.findForward("success");
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception{
		log.debug("list");

		List<Utente> ulist = ServiceFactory.getUtenteService().getAll();
		request.setAttribute("ulist", ulist);
		
		return mapping.findForward("listaUtenti");
	}
	
	public ActionForward modifica(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception{
		log.debug("modifica");

		
		UtenteForm uform = (UtenteForm)form;
		
		try {
			//transazione programmatica
			//new UtenteService().multiSave(uform.getUtentiSelezionati(), uform.getRuolo());
			
			//transazione dichiarativa
			 ServiceFactory.getUtenteService().multiSave(uform.getUtentiSelezionati(), uform.getRuolo());
		} catch (Exception e) {
			request.setAttribute("messaggio", e.getMessage());
		}
		
		
		List<Utente> ulist = ServiceFactory.getUtenteService().getAll();
		request.setAttribute("ulist", ulist);
		
		return mapping.findForward("listaUtenti");
	}
	
}
