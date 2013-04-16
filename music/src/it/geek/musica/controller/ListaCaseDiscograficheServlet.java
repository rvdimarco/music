package it.geek.musica.controller;

import it.geek.musica.dao.IDAO;
import it.geek.musica.dao.impl.CasaDiscograficaDAO;
import it.geek.musica.model.CasaDiscografica;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListaCaseDiscograficheServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		IDAO dao = new CasaDiscograficaDAO();
		List<CasaDiscografica> cadilist = dao.findAll();
		
		request.setAttribute("caseDiscografiche", cadilist);
		
		RequestDispatcher rd = request.getRequestDispatcher("/listaCaseDiscografiche.jsp");
		rd.forward(request, response);
	}
}
