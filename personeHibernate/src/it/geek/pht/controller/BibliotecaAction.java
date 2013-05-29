package it.geek.pht.controller;

import it.geek.pht.form.BibliotecaForm;
import it.geek.pht.pojo.Libro;
import it.geek.pht.pojo.Utente;
import it.geek.pht.service.ServiceFactory;
import it.geek.pht.util.Constants;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class BibliotecaAction extends DispatchAction implements Constants{
	
	Logger log = Logger.getLogger(BibliotecaAction.class);

	public ActionForward biblioteca(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		log.debug("biblioteca");
		
		return mapping.findForward("biblioteca");
	}
	
	//GESTIONE UTENTI
	public ActionForward listUtenti(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		log.debug("listUtenti");
		String forwardName = null;
		
		try {
			List<Utente> lista = ServiceFactory.getService(UTENTE).getAll();
			request.setAttribute("ulist", lista);
			forwardName = "listUtenti";
			
		} catch (Exception e) {
			log.error("errore! "+e);
			request.setAttribute("messaggio", e.getMessage());
			forwardName = "failure";
		}
		
		return mapping.findForward(forwardName);
	}
	
	public ActionForward deleteUtente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		log.debug("deleteUtente");
		String forwardName = null;
		
		try {
			BibliotecaForm bf = (BibliotecaForm)form;
			Utente u = new Utente();
			BeanUtils.copyProperties(u, bf);
			
			ServiceFactory.getService(UTENTE).delete(u);
			
			request.setAttribute("messaggio", "eliminazione effettuata correttamente");
			List<Utente> lista = ServiceFactory.getService(ServiceFactory.UTENTE).getAll();
			request.setAttribute("ulist", lista);
			forwardName = "listUtenti";
			
		} catch (Exception e) {
			log.error("errore! "+e);
			request.setAttribute("messaggio", e.getMessage());
			forwardName = "failure";
		}
		
		return mapping.findForward(forwardName);
	}
	
	public ActionForward showInsertUtente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		log.debug("showInsertUtente");
		
		List<Libro> libriDisponibili = ServiceFactory.getService(LIBRO).getOrphans();
		request.setAttribute("libriDisponibili", libriDisponibili);
		
		return mapping.findForward("insertUtente");
	}
	public ActionForward insertUtente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		log.debug("insertUtente");
		String forwardName = null;
		
		try {
			BibliotecaForm bf = (BibliotecaForm)form;
			Utente u = new Utente();
			BeanUtils.copyProperties(u, bf);
			
			log.debug("BibliotecaForm: "+bf);
			log.debug("Utente: "+u);
			
			Libro l = new Libro();
			l.setIdLibro(bf.getIdLibro());
			
			u.addLibro(l);
			ServiceFactory.getService(UTENTE).save(u);
			
			request.setAttribute("messaggio", "inserimento effettuato correttamente");
			List<Utente> lista = ServiceFactory.getService(ServiceFactory.UTENTE).getAll();
			request.setAttribute("ulist", lista);
			forwardName = "listUtenti";
			
		} catch (Exception e) {
			log.error("errore! "+e);
			request.setAttribute("messaggio", e.getMessage());
			forwardName = "insertUtente";
		}
		
		return mapping.findForward(forwardName);
	}
	
	public ActionForward showUtente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		log.debug("showUtente");
		
		String forwardName = null;
		
		try {
			BibliotecaForm bf = (BibliotecaForm)form;
			
			Utente u = (Utente)ServiceFactory.getService(UTENTE).get(bf.getIdUtente());
			
			BeanUtils.copyProperties(bf, u);
			
			forwardName = "showUtente";
			
		} catch (Exception e) {
			log.error("errore! "+e);
			request.setAttribute("messaggio", e.getMessage());
			forwardName = "failure";
		}
		
		return mapping.findForward(forwardName);
	}
	
	public ActionForward showUpdateUtente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		log.debug("showUpdateUtente");
		
		String forwardName = null;
		
		try {
			BibliotecaForm bf = (BibliotecaForm)form;
			
			Utente u = (Utente)ServiceFactory.getService(UTENTE).get(bf.getIdUtente());
			
			BeanUtils.copyProperties(bf, u);
			
			List<Libro> libriDisponibili = ServiceFactory.getService(LIBRO).getOrphans();
			request.setAttribute("libriDisponibili", libriDisponibili);
			
			forwardName = "updateUtente";
			
		} catch (Exception e) {
			log.error("errore! "+e);
			request.setAttribute("messaggio", e.getMessage());
			forwardName = "failure";
		}
		
		return mapping.findForward(forwardName);
	}
	public ActionForward updateUtente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		log.debug("updateUtente");
		String forwardName = null;
		
		try {
			BibliotecaForm bf = (BibliotecaForm)form;
			Utente u = (Utente)ServiceFactory.getService(UTENTE).get(bf.getIdUtente());
			u.setCognome(bf.getCognome());
			u.setNome(bf.getNome());
			u.setUsername(bf.getUsername());
			u.setPassword(bf.getPassword());
			//BeanUtils.copyProperties(u, bf);
			
			if(bf.getIdLibro()!=0){
				Libro nuovoPrestito = new Libro();
				nuovoPrestito.setIdLibro(bf.getIdLibro());
				u.addLibro(nuovoPrestito);
			}	
			Libro restituendo = null;
			Set<Libro> restituzioni = new HashSet<>();
			if(bf.getLibriSelezionati()!=null){
				for(String id : bf.getLibriSelezionati()){
					restituendo = new Libro();
					restituendo.setIdLibro(Integer.parseInt(id));
					restituzioni.add(restituendo);
				}
			}
			u.getLibros().removeAll(restituzioni);
			
			ServiceFactory.getService(UTENTE).save(u);
			
			request.setAttribute("messaggio", "modifica effettuata correttamente");
			List<Utente> lista = ServiceFactory.getService(ServiceFactory.UTENTE).getAll();
			request.setAttribute("ulist", lista);
			forwardName = "listUtenti";
			
		} catch (Exception e) {
			log.error("errore! "+e);
			request.setAttribute("messaggio", e.getMessage());
			forwardName = "updateUtente";
		}
		
		return mapping.findForward(forwardName);
	}
	
	public ActionForward searchUtente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		log.debug("searchUtente");
		String forwardName = null;
		
		try {
			BibliotecaForm bf = (BibliotecaForm)form;
			Utente u = new Utente();
			BeanUtils.copyProperties(u, bf);
			
			List<Utente> lista = ServiceFactory.getService(UTENTE).getByExample(u);
			request.setAttribute("ulist", lista);
			forwardName = "listUtenti";
			
		} catch (Exception e) {
			log.error("errore! "+e);
			request.setAttribute("messaggio", e.getMessage());
			forwardName = "failure";
		}
		
		return mapping.findForward(forwardName);
	}
	
	//GESTIONE LIBRI
	public ActionForward listLibri(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		log.debug("listLibri");
		String forwardName = null;
		
		try {
			List<Libro> lista = ServiceFactory.getService(LIBRO).getAll();
			request.setAttribute("llist", lista);
			forwardName = "listLibri";
			
		} catch (Exception e) {
			log.error("errore! "+e);
			request.setAttribute("messaggio", e.getMessage());
			forwardName = "failure";
		}
		
		return mapping.findForward(forwardName);
	}
	
	public ActionForward deleteLibro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		log.debug("deleteLibro");
		String forwardName = null;
		
		try {
			BibliotecaForm bf = (BibliotecaForm)form;
			Libro l = new Libro();
			l.setIdLibro(bf.getIdLibro());
			//BeanUtils.copyProperties(l, bf);
			
			ServiceFactory.getService(LIBRO).delete(l);
			
			request.setAttribute("messaggio", "eliminazione effettuata correttamente");
			List<Libro> lista = ServiceFactory.getService(ServiceFactory.LIBRO).getAll();
			request.setAttribute("llist", lista);
			forwardName = "listLibri";
			
		} catch (Exception e) {
			log.error("errore! "+e);
			request.setAttribute("messaggio", e.getMessage());
			forwardName = "failure";
		}
		
		return mapping.findForward(forwardName);
	}
	
	public ActionForward showInsertLibro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		log.debug("showInsertLibro");
		
		return mapping.findForward("insertLibro");
	}
	public ActionForward insertLibro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		log.debug("insertLibro");
		String forwardName = null;
		
		try {
			BibliotecaForm bf = (BibliotecaForm)form;
			Libro l = new Libro();
			l.setPagine(bf.getPagine());
			l.setTitolo(bf.getTitolo());
			//BeanUtils.copyProperties(l, bf);
			
			ServiceFactory.getService(LIBRO).save(l);
			
			request.setAttribute("messaggio", "inserimento effettuato correttamente");
			List<Libro> lista = ServiceFactory.getService(ServiceFactory.LIBRO).getAll();
			request.setAttribute("llist", lista);
			forwardName = "listLibri";
			
		} catch (Exception e) {
			log.error("errore! "+e);
			request.setAttribute("messaggio", e.getMessage());
			forwardName = "insertLibro";
		}
		
		return mapping.findForward(forwardName);
	}
	
	public ActionForward showLibro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		log.debug("showLibro");
		
		String forwardName = null;
		
		try {
			BibliotecaForm bf = (BibliotecaForm)form;
			
			Libro l = (Libro)ServiceFactory.getService(LIBRO).get(bf.getIdLibro());
			
			bf.setPagine(l.getPagine());
			bf.setTitolo(l.getTitolo());
			bf.setUtente(l.getUtente());
			//BeanUtils.copyProperties(bf, l);
			
			forwardName = "showLibro";
			
		} catch (Exception e) {
			log.error("errore! "+e);
			request.setAttribute("messaggio", e.getMessage());
			forwardName = "failure";
		}
		
		return mapping.findForward(forwardName);
	}
	
	public ActionForward showUpdateLibro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		log.debug("showUpdateLibro");
		
		String forwardName = null;
		
		try {
			BibliotecaForm bf = (BibliotecaForm)form;
			
			Libro l = (Libro)ServiceFactory.getService(LIBRO).get(bf.getIdLibro());
			
			BeanUtils.copyProperties(bf, l);
			
			forwardName = "updateLibro";
			
		} catch (Exception e) {
			log.error("errore! "+e);
			request.setAttribute("messaggio", e.getMessage());
			forwardName = "failure";
		}
		
		return mapping.findForward(forwardName);
	}
	public ActionForward updateLibro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		log.debug("updateLibro");
		String forwardName = null;
		
		try {
			BibliotecaForm bf = (BibliotecaForm)form;
			Libro l = new Libro();
			l.setTitolo(bf.getTitolo());
			l.setPagine(bf.getPagine());
			//BeanUtils.copyProperties(l, bf);
			
			ServiceFactory.getService(LIBRO).save(l);
			
			request.setAttribute("messaggio", "modifica effettuata correttamente");
			List<Libro> lista = ServiceFactory.getService(ServiceFactory.LIBRO).getAll();
			request.setAttribute("llist", lista);
			forwardName = "listLibri";
			
		} catch (Exception e) {
			log.error("errore! "+e);
			request.setAttribute("messaggio", e.getMessage());
			forwardName = "updateLibro";
		}
		
		return mapping.findForward(forwardName);
	}
	
	public ActionForward searchLibro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		log.debug("searchLibro");
		String forwardName = null;
		
		try {
			BibliotecaForm bf = (BibliotecaForm)form;
			Libro l = new Libro();
			l.setTitolo(bf.getTitolo());
			l.setPagine(bf.getPagine());
			//BeanUtils.copyProperties(l, bf);
			
			List<Libro> lista = ServiceFactory.getService(LIBRO).getByExample(l);
			request.setAttribute("llist", lista);
			forwardName = "listLibri";
			
		} catch (Exception e) {
			log.error("errore! "+e);
			request.setAttribute("messaggio", e.getMessage());
			forwardName = "failure";
		}
		
		return mapping.findForward(forwardName);
	}
	
}
