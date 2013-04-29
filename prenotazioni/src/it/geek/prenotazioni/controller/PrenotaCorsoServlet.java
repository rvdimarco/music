package it.geek.prenotazioni.controller;

import it.geek.prenotazioni.dao.StudenteDAO;
import it.geek.prenotazioni.model.Corso;
import it.geek.prenotazioni.model.Studente;
import it.geek.prenotazioni.service.SegretarioService;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PrenotaCorsoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		HttpSession session = request.getSession();
		Studente studente = (Studente)session.getAttribute("studente");
		
		SegretarioService segretario = new SegretarioService();
		segretario.prenota(studente, new Corso(id));
		
		//aggiorno lo studente in session
		StudenteDAO studenteDao = new StudenteDAO();
		session.setAttribute("studente", studenteDao.findById(studente.getMatricola()));
		
		RequestDispatcher rd = request.getRequestDispatcher("/esito.jsp");
		rd.forward(request, response);
		
	}

}
