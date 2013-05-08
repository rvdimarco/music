package it.geek.resid.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class GestioneUtenteAction extends DispatchAction {
	
	Logger log = Logger.getLogger(GestioneUtenteAction.class);

	public ActionForward inserimento(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception{
		log.debug("inserimento...");
		
		return mapping.findForward("success");
	}

}
