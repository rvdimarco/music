package it.geek.musica.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GestioneAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String codop = request.getParameter("codop");
		
		String forwardPath = "";
		
		if("INS".equalsIgnoreCase(codop)){
			
		}else if("VIS".equalsIgnoreCase(codop)){
			
		}else if("MOD".equalsIgnoreCase(codop)){
			
		}else if("ELI".equalsIgnoreCase(codop)){
			
		}
		
		
	}

}
