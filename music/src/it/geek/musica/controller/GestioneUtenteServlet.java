package it.geek.musica.controller;

import it.geek.musica.factory.ServiceFactory;
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

/**
 * Servlet implementation class GestioneUtenteServlet
 */
@WebServlet("/GestioneUtenteServlet")
public class GestioneUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String codopValue = request.getParameter(Constants.CODOP_LABEL);
		String forwardPath = null;
		
		HttpSession session = request.getSession();
		Utente utente = (Utente)session.getAttribute(Constants.UTENTE_LABEL);
		if(utente==null || utente.getRuolo().getCodice() != Constants.RUOLO_AMMINISTRATORE){
			codopValue = "ho inibito l'accesso a chi non è amministratore!";
		}
		
		switch (codopValue) {
		
			case Constants.CODOP_LISTA_VALUE:
				List<Utente> ulist = ServiceFactory.getUtenteService().getAll();
				request.setAttribute("utenti", ulist);
				forwardPath = "/listaUtenti.jsp";
				break;
				
			case Constants.CODOP_INS_VALUE:
				
				break;
				
			case Constants.CODOP_DO_INS_VALUE:
				
				break;
				
			case Constants.CODOP_VIS_VALUE:
				
				break;
				
			case Constants.CODOP_ELI_VALUE:
				
				break;
				
			case Constants.CODOP_MOD_VALUE:
				
				break;
				
			case Constants.CODOP_DO_MOD_VALUE:
				
				break;	
	
			default:
				request.setAttribute("messaggio", "operazione non consentita");
				forwardPath = "/error.jsp";
				break;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);
		
	}

}
