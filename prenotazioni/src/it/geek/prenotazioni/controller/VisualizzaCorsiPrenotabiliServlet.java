package it.geek.prenotazioni.controller;

import it.geek.prenotazioni.dao.CorsoDAO;
import it.geek.prenotazioni.model.Corso;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VisualizzaCorsiPrenotabiliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CorsoDAO corsoDao = new CorsoDAO();
		List<Corso> corsi = corsoDao.findAll();
		
		request.setAttribute("corsi", corsi);
		
		RequestDispatcher rd = request.getRequestDispatcher("/visualizzaCorsiPrenotabili.jsp");
		rd.forward(request, response);
		
	}

}
