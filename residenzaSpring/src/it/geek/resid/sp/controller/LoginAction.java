package it.geek.resid.sp.controller;

import it.geek.resid.sp.dao.UtenteDAO;
import it.geek.resid.sp.form.LoginForm;
import it.geek.resid.sp.pojo.Utente;
import it.geek.resid.sp.service.UtenteService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LoginAction extends Action {
	
	Logger log = Logger.getLogger(LoginAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception{
		log.debug("login in corso...");

		String forwardName = "";
		
		LoginForm lf = (LoginForm)form;
		
		Utente u = new UtenteService().get(lf.getUsername());
		
		if(u==null){
			request.setAttribute("messaggio", "username '"+lf.getUsername()+"' non corrisponde a nessun utente registrato.");
			log.debug("operazione eseguita: utente non trovato");
			forwardName = "failure";
		}else if(!u.getPassword().equals(lf.getPassword())){
			request.setAttribute("messaggio", "password errata.");
			log.debug("operazione eseguita: password errata");
			forwardName = "failure";			
		}else{
			request.getSession().setAttribute("utenteSession", u);
			log.debug("...login effettuata correttamente");
			forwardName = "success";
		}
		
		return mapping.findForward(forwardName);
	}

}
