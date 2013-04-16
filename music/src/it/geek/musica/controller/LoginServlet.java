package it.geek.musica.controller;

import it.geek.musica.dao.IDAO;
import it.geek.musica.dao.impl.UtenteDAO;
import it.geek.musica.model.Utente;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		IDAO dao = new UtenteDAO();
		Utente u = (Utente)dao.findById(username);
		
		String forwardPath = "";
		
		if(u!=null && u.getPassword().equals(password)){
			
			forwardPath= "/home.jsp";
			request.setAttribute("utente", u);
			
		}else{
			
			forwardPath= "/error.jsp";
			
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);
		
	}
	
}
