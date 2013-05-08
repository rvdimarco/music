package it.geek.resid.controller;

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
		
		//cerco l'utente...
		
		//se ok
		forwardName = "success";
		log.debug("...login effettuata correttamente");
		
		//altrimenti...
		//...
		log.debug("...login effettuata con errore");
		
		return mapping.findForward(forwardName);
	}

}
