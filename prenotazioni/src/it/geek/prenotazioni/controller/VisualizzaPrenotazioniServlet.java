package it.geek.prenotazioni.controller;

import it.geek.prenotazioni.dao.CorsoDAO;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VisualizzaPrenotazioniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// - le prenotazioni sono già in session con lo studente...
		// - l'unica accortezza è quella di aggiornare lo studente in
		//   session quando effettua una nuova prenotazione!
		
		RequestDispatcher rd = request.getRequestDispatcher("/visualizzaPrenotazioni.jsp");
		rd.forward(request, response);
	}

}
