package it.geek.ms.controller;

import it.geek.ms.factory.ServiceFactory;
import it.geek.ms.form.GestCasaDiscograficaForm;
import it.geek.ms.model.Autore;
import it.geek.ms.model.CasaDiscografica;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GestioneCaseDiscograficheAction extends Action {

	private static Logger log = Logger.getLogger(GestioneCaseDiscograficheAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String forwardPath = "";
		
		GestCasaDiscograficaForm casaForm = (GestCasaDiscograficaForm) form;
		log.debug("form: "+casaForm);
		
		CasaDiscografica casa = null;
		Autore autore = null;
		switch (casaForm.getOperazione()) {
		case "dettaglioCasa":
			casa = ServiceFactory.getCasaDiscograficaService().get(casaForm.getNome());
			if(casa==null){
				request.setAttribute("messaggio", "Casa Discografica non trovata");
				forwardPath = "failure";
			}else{
				List<Autore> autori = ServiceFactory.getAutoreService().getAll();
				autori.removeAll(casa.getAutori());
				casaForm.setAutoriList(autori);
				request.setAttribute("casaDiscografica", casa);
				forwardPath = "view";
			}
			break;
		case "inserisciAutore":
			autore = ServiceFactory.getAutoreService().get(casaForm.getIdAutore());
			casa = ServiceFactory.getCasaDiscograficaService().get(casaForm.getNome());
			if(autore==null){
				request.setAttribute("messaggio", "Autore non trovato");
				forwardPath = "failure";
			}else if(casa==null){
				request.setAttribute("messaggio", "Casa Discografica non trovata");
				forwardPath = "failure";
			}else{
				autore.setCasaDiscografica(casaForm.getNome());
				ServiceFactory.getAutoreService().create(autore);
				casa = ServiceFactory.getCasaDiscograficaService().get(casaForm.getNome());
				List<Autore> autori = ServiceFactory.getAutoreService().getAll();
				autori.removeAll(casa.getAutori());
				casaForm.setAutoriList(autori);
				request.setAttribute("casaDiscografica", casa);
				forwardPath = "view";
			}
			break;
		case "visualizzaAutore":
			//...
			break;
		case "modificaAutore":
			//...
			break;
		case "eseguiModificaAutore":
			//...
			break;			
		case "eliminaAutore":
			casa = ServiceFactory.getCasaDiscograficaService().get(casaForm.getNome());
			if(casa==null){
				request.setAttribute("messaggio", "Casa Discografica non trovata");
				forwardPath = "failure";
			}else{
				ServiceFactory.getAutoreService().delete(casaForm.getIdAutore());
				casa = ServiceFactory.getCasaDiscograficaService().get(casaForm.getNome());
				List<Autore> autori = ServiceFactory.getAutoreService().getAll();
				autori.removeAll(casa.getAutori());
				casaForm.setAutoriList(autori);
				request.setAttribute("casaDiscografica", casa);
				forwardPath = "view";
			}
			break;			
		default:
			request.setAttribute("messaggio", "operazione non consentita");
			forwardPath = "failure";
			break;
		}
		
		return mapping.findForward(forwardPath);
	}
}
