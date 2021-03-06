package it.geek.prenotazioni.controller;

import it.geek.prenotazioni.dao.CorsoDAO;
import it.geek.prenotazioni.dao.StudenteDAO;
import it.geek.prenotazioni.model.Corso;
import it.geek.prenotazioni.model.Studente;
import it.geek.prenotazioni.service.SegretarioService;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VisualizzaCorsiPrenotabiliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CorsoDAO corsoDao = new CorsoDAO();
		List<Corso> corsi = corsoDao.findAll();
		
		request.setAttribute("corsi", corsi);
		
		String forwardPath = "/visualizzaCorsiPrenotabili.jsp";
		
		//entro in questa if solo se il link utilizzato � quello per JSTL..
		if(new Boolean(request.getParameter("isJstlTest"))){
			
			HttpSession session = request.getSession();
			Studente studente = (Studente)session.getAttribute("studente");
			
			//agisco qui per non complicare la pagina
			//l'obbiettivo � di non consentire prenotazioni duplicate
			//nel giro "non jstl" veniva gestito direttamente in pagina
			corsi.removeAll(studente.getPrenotazioni());
			
			//aggiorno i corsi in request
			request.setAttribute("corsi", corsi);
			
			
			forwardPath = "/visualizzaCorsiPrenotabiliJSTL.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);
		
	}

}
