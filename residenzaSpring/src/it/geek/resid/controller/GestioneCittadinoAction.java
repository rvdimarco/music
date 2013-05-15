package it.geek.resid.controller;

import it.geek.resid.exception.BusinessException;
import it.geek.resid.form.CittadinoForm;
import it.geek.resid.model.Cittadino;
import it.geek.resid.model.InfoRegione;
import it.geek.resid.service.ServiceFactory;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
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

		List<Cittadino> list = ServiceFactory.getCittadinoService().getAll();
		request.setAttribute("cittadini", list);
		
		return mapping.findForward("cambioResidenza");
	}
	
	public ActionForward statistiche(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception{
		log.debug("statistiche");

		//con il metodo "classico"
		//List<InfoRegione> infos = new StatisticheService().getInfoRegioneAll();
		
		//con spring (è cambiato sia il service che il dao... 
		//ma cambiando la configurazione posso ottenere l'esecuzione
		//delle classi del metodo "classico" senza modificare l'invocazione qui sotto!)
		List<InfoRegione> infos = ServiceFactory.getStatisticheService().getInfoRegioneAll();
		
		request.setAttribute("infos", infos);
		
		return mapping.findForward("statistichePage");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception{
		log.debug("statistiche");
		CittadinoForm cform = (CittadinoForm)form;
		log.debug("citta: "+cform.getCodCitta());
		log.debug("cf: "+cform.getCodiceFiscale());
		
		Cittadino c = new Cittadino();
		BeanUtils.copyProperties(c, cform);
		try {
			ServiceFactory.getCittadinoService().save(c);
		} catch (BusinessException e) {
			request.setAttribute("messaggio",e.getMessage());
		}
		
		List<Cittadino> list = ServiceFactory.getCittadinoService().getAll();
		request.setAttribute("cittadini", list);
		
		return mapping.findForward("cambioResidenza");
	}
	
}
