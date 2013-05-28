package it.geek.pht.controller;

import it.geek.pht.form.PersonaForm;
import it.geek.pht.pojo.Persona;
import it.geek.pht.service.PersonaService;
import it.geek.pht.service.ServiceFactory;
import it.geek.pht.util.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class PersonaAction extends DispatchAction implements Constants{
	
	Logger log = Logger.getLogger(PersonaAction.class);

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		log.debug("list");
		String forwardName = null;
		
		try {
			List<Persona> lista = ServiceFactory.getService(PERSONA).getAll();
			request.setAttribute("plist", lista);
			forwardName = "list";
			
		} catch (Exception e) {
			log.error("errore! "+e);
			request.setAttribute("messaggio", e.getMessage());
			forwardName = "error";
		}
		
		return mapping.findForward(forwardName);
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		log.debug("delete");
		String forwardName = null;
		
		try {
			PersonaForm pf = (PersonaForm)form;
			Persona p = new Persona();
			BeanUtils.copyProperties(p, pf);
			
			ServiceFactory.getService(PERSONA).delete(p);
			
			request.setAttribute("messaggio", "eliminazione effettuata correttamente");
			List<Persona> lista = ServiceFactory.getService(ServiceFactory.PERSONA).getAll();
			request.setAttribute("plist", lista);
			forwardName = "list";
			
		} catch (Exception e) {
			log.error("errore! "+e);
			request.setAttribute("messaggio", e.getMessage());
			forwardName = "failure";
		}
		
		return mapping.findForward(forwardName);
	}
	
}
