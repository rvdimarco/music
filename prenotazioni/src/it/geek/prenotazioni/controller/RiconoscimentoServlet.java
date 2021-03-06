package it.geek.prenotazioni.controller;

import it.geek.prenotazioni.dao.StudenteDAO;
import it.geek.prenotazioni.model.Studente;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


public class RiconoscimentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	private static Logger log = Logger.getLogger(RiconoscimentoServlet.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String matricola = request.getParameter("matricola");
		log.info("riconoscimento della matricola "+matricola+" in corso...");

		StudenteDAO studenteDao = new StudenteDAO();
		Studente studente = studenteDao.findById(matricola);
		
		String forwardPath = null;
		
		if(studente==null){
			request.setAttribute("messaggio","Spiacenti, matricola "+matricola+" non riconosciuta.");
			forwardPath = "/error.jsp";
		}else{
			HttpSession session = request.getSession();
			session.setAttribute("studente", studente);
			forwardPath = "/home.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);
		
	}

}
