package it.geek.musica.controller;

import it.geek.musica.dao.IDAO;
import it.geek.musica.dao.impl.CasaDiscograficaDAO;
import it.geek.musica.model.CasaDiscografica;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VisualizzaCasaDiscograficaServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		String id = request.getParameter("id");
		IDAO<CasaDiscografica, String> dao = new CasaDiscograficaDAO();
		CasaDiscografica casa = dao.findById(id);
		
		request.setAttribute("casa",casa);
		
		RequestDispatcher rd = request.getRequestDispatcher("/visualizzaCasaDiscografica.jsp");
		rd.forward(request, response);
	}
}
