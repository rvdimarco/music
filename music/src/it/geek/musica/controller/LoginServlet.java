package it.geek.musica.controller;

import it.geek.musica.dao.IDAO;
import it.geek.musica.dao.impl.AutoreDAO;
import it.geek.musica.dao.impl.CasaDiscograficaDAO;
import it.geek.musica.dao.impl.UtenteDAO;
import it.geek.musica.factory.DaoFactory;
import it.geek.musica.factory.ServiceFactory;
import it.geek.musica.model.CasaDiscografica;
import it.geek.musica.model.Utente;
import it.geek.musica.service.Service;
import it.geek.musica.service.impl.UtenteService;
import it.geek.musica.util.Constants;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		/* utilizzo il factory così:
		Service us = ServiceFactory.getUtenteService();
		Utente u0 = (Utente)us.get(username);*/
		
		// o in alternativa in un unica riga:
		Utente u = (Utente)ServiceFactory.getUtenteService().get(username);
		
		String forwardPath = "";
		
		if(u!=null && u.getPassword().equals(password)){
			
			forwardPath= "/home.jsp";
			
			HttpSession session = request.getSession();
			session.setAttribute(Constants.UTENTE_LABEL, u);
			
			List<CasaDiscografica> cadilist = ServiceFactory.getCasaDiscograficaService()
														    .getAll();
			
			request.setAttribute("caseDiscografiche", cadilist);
			
			
		}else{
			
			forwardPath= "/error.jsp";
			
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);
		
	}
	
}
