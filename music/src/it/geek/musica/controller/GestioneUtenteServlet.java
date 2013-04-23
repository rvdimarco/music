package it.geek.musica.controller;

import it.geek.musica.factory.ServiceFactory;
import it.geek.musica.model.Ruolo;
import it.geek.musica.model.Utente;
import it.geek.musica.util.Constants;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


public class GestioneUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private static Logger logger = Logger.getLogger(GestioneUtenteServlet.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String codopValue = request.getParameter(Constants.CODOP_LABEL);
		String forwardPath = null;
		
		logger.info("GestioneUtenteServlet::codop: "+codopValue);
		
		HttpSession session = request.getSession();
		Utente utente = (Utente)session.getAttribute(Constants.UTENTE_LABEL);
		if(utente==null || !utente.isAmministratore()){
			codopValue = "ho inibito l'accesso a chi non è amministratore!";
		}
		
		List<Utente> ulist = null;
		List<Ruolo> rlist = null;
		String key = null;
		Utente u = null;
		
		switch (codopValue) {
		
			//estrae la lista di utenti e predispone l'inoltro alla pagina di visualizzazione lista
			case Constants.CODOP_LISTA_VALUE:
				ulist = ServiceFactory.getUtenteService().getAll();
				request.setAttribute("utenti", ulist);
				forwardPath = "/listaUtenti.jsp";
				break;
			
			//estrae la lista di ruoli e predispone l'inoltro alla pagina di inserimento
			case Constants.CODOP_INS_VALUE:
				rlist = ServiceFactory.getRuoloService().getAll();
				request.setAttribute("ruoli", rlist);
				forwardPath = "/inserisciUtente.jsp";				
				break;
				
			//estrae l'utente e la lista di ruoli e predispone l'inoltro alla pagina di modifica
			case Constants.CODOP_MOD_VALUE:
				key = request.getParameter("id");
				u = ServiceFactory.getUtenteService().get(key);
				request.setAttribute("utente", u);
				
				rlist = ServiceFactory.getRuoloService().getAll();
				request.setAttribute("ruoli", rlist);
				
				forwardPath = "/modificaUtente.jsp";				
				break;
			
			//recupera i dati dalla pagina di inserimento, inserisce, estrae la lista aggiornata e predispone l'inoltro alla pagina di visuaizzazione lista
			case Constants.CODOP_DO_INS_VALUE:
				String usernameIns = request.getParameter("username");
				String passwordIns = usernameIns;
				String cognomeIns = request.getParameter("cognome");
				String nomeIns = request.getParameter("nome");
				int codRuoloIns = 0;
				try {
					codRuoloIns = Integer.parseInt(request.getParameter("ruolo"));
				} catch (NumberFormatException e) {
					codRuoloIns = Constants.RUOLO_OSPITE;
				} 
				Utente utenteIns = new Utente();
				utenteIns.setUsername(usernameIns);
				utenteIns.setPassword(passwordIns);
				utenteIns.setCognome(cognomeIns);
				utenteIns.setNome(nomeIns);
				utenteIns.setRuolo(codRuoloIns);
				
				ServiceFactory.getUtenteService().create(utenteIns);
				
				ulist = ServiceFactory.getUtenteService().getAll();
				request.setAttribute("utenti", ulist);
				forwardPath = "/listaUtenti.jsp";
				
				break;

				//recupera i dati dalla pagina di modifica, modifica, estrae la lista aggiornata e predispone l'inoltro alla pagina di visuaizzazione lista
				case Constants.CODOP_DO_MOD_VALUE:
					String usernameMod = request.getParameter("username");
					String passwordMod = usernameMod;
					String cognomeMod = request.getParameter("cognome");
					String nomeMod = request.getParameter("nome");
					int codRuoloMod = 0;
					try {
						codRuoloMod = Integer.parseInt(request.getParameter("ruolo"));
					} catch (NumberFormatException e) {
						codRuoloMod = Constants.RUOLO_OSPITE;
					} 
					Utente utenteMod = new Utente();
					utenteMod.setUsername(usernameMod);
					utenteMod.setPassword(passwordMod);
					utenteMod.setCognome(cognomeMod);
					utenteMod.setNome(nomeMod);
					utenteMod.setRuolo(codRuoloMod);
					
					ServiceFactory.getUtenteService().save(utenteMod);
					
					ulist = ServiceFactory.getUtenteService().getAll();
					request.setAttribute("utenti", ulist);
					forwardPath = "/listaUtenti.jsp";
					
					break;
				
			//estrae l'utente selezionato e predispone l'inoltro alla pagina di visualizzazione dettaglio
			case Constants.CODOP_VIS_VALUE:
				key = request.getParameter("id");
				u = ServiceFactory.getUtenteService().get(key);
				request.setAttribute("utente", u);
				forwardPath = "/visualizzaUtente.jsp";
				break;
				
			//elimina l'utente selezionato, estrtae la lista aggiornata e predispone l'inoltro alla pagina di visualizzazione	lista
			case Constants.CODOP_ELI_VALUE:
				key = request.getParameter("id");
				ServiceFactory.getUtenteService().delete(key);
				
				ulist = ServiceFactory.getUtenteService().getAll();
				request.setAttribute("utenti", ulist);
				forwardPath = "/listaUtenti.jsp";
				break;
				
			//se l'operazione non è fra quelle previste predispone l'inoltro alla pagina di errore
			default:
				request.setAttribute("messaggio", "operazione non consentita");
				forwardPath = "/error.jsp";
				break;
		}
		
		//inoltro
		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);
		
	}

}
