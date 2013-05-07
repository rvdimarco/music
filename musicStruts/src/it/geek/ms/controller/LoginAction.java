package it.geek.ms.controller;

import java.util.List;

import it.geek.ms.factory.ServiceFactory;
import it.geek.ms.form.LoginForm;
import it.geek.ms.model.CasaDiscografica;
import it.geek.ms.model.Utente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LoginAction extends Action {

	private static Logger log = Logger.getLogger(LoginAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String forwardPath = "";
		
		LoginForm login = (LoginForm) form;
		log.debug("form: "+login);
		
		Utente utente = new Utente();
		//copio i dati del form nell'utente
		BeanUtils.copyProperties(utente, form);
		
		//recupero l'utente
		utente = ServiceFactory.getUtenteService().get(utente.getUsername());
		
		if(utente == null){
			request.setAttribute("messaggio", "utente non trovato");
			forwardPath = "failure";
		}else if(!utente.getPassword().equals(login.getPassword())){
			request.setAttribute("messaggio", "password non valida");
			forwardPath = "failure";			
		}else{
			//metto l'utente in sessione
			request.getSession().setAttribute("utente", utente);
			
			//estraggo la lista di case discografiche
			List<CasaDiscografica> caseList = ServiceFactory.getCasaDiscograficaService().getAll();
			request.setAttribute("listaCaseDiscografiche", caseList);
			forwardPath = "success";
		}
		
		return mapping.findForward(forwardPath);
	}

}
