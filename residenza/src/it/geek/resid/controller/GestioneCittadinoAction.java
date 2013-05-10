package it.geek.resid.controller;

import it.geek.resid.form.CittadinoForm;
import it.geek.resid.model.Cittadino;
import it.geek.resid.service.CittadinoService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class GestioneCittadinoAction extends DispatchAction {
	
	Logger log = Logger.getLogger(GestioneCittadinoAction.class);

	public ActionForward sarch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception{
		log.debug("sarch");

		
		return mapping.findForward("anagraficaCittadini");
	}
	
	public ActionForward residenza(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception{
		log.debug("residenza");

		List list = new CittadinoService().getAll();
		request.setAttribute("cittadini", list);
		
		return mapping.findForward("cambioResidenza");
	}
	
	public ActionForward statistiche(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception{
		log.debug("statistiche");

		
		return mapping.findForward("statistichePage");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception{
		log.debug("statistiche");
		CittadinoForm cform = (CittadinoForm)form;
		
		log.debug("citta: "+cform.getCodCitta());
		request.setAttribute("test","eccolo!");
		
		return mapping.findForward("cambioResidenza");
	}
	
}
