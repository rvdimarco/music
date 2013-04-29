package it.geek.prenotazioni.controller;

import it.geek.prenotazioni.dao.CorsoDAO;
import it.geek.prenotazioni.dao.StudenteDAO;
import it.geek.prenotazioni.model.Corso;
import it.geek.prenotazioni.model.Studente;
import it.geek.prenotazioni.service.SegretarioService;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class EliminaPrenotazioniCorsiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(EliminaPrenotazioniCorsiServlet.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Studente studente = (Studente)session.getAttribute("studente");
		
		SegretarioService segretario = new SegretarioService();
		
		
		Enumeration<String> eli = request.getParameterNames();
		while(eli.hasMoreElements()){
			String s = eli.nextElement();
			log.debug("param name: "+s+" - param value: "+request.getParameter(s));
			
			if(s.contains("corsiDaEliminare")){
				segretario.cancellaPrenotazione(studente, new Corso(Integer.parseInt(request.getParameter(s))));
				
			}
		}
		
		//aggiorno lo studente in session
		StudenteDAO studenteDao = new StudenteDAO();
		session.setAttribute("studente", studenteDao.findById(studente.getMatricola()));
		
		//torno alla pagina da cui provengo
		RequestDispatcher rd= request.getRequestDispatcher("/visualizzaPrenotazioni.jsp");
		rd.forward(request, response);
		
	}

}
