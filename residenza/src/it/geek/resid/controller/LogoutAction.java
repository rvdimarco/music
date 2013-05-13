package it.geek.resid.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LogoutAction extends Action {
	
	Logger logger = Logger.getLogger(LogoutAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String forwardName = "";
		try {
			HttpSession session = request.getSession();
			logger.info("chiusura della sessione con id "+session.getId());
			session.invalidate();
			request.setAttribute("messaggio", "logout effettuata correttamente");
			forwardName = "success";
		} catch (Exception e) {
			logger.error("problemi durante la logout...");
			request.setAttribute("messaggio", "problemi durante la logout...");
			forwardName = "failure";
		}
		
		return mapping.findForward(forwardName);

	}
	
}
