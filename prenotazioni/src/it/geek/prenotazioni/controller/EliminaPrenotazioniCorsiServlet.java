package it.geek.prenotazioni.controller;

import it.geek.prenotazioni.dao.CorsoDAO;
import it.geek.prenotazioni.dao.StudenteDAO;
import it.geek.prenotazioni.model.Corso;
import it.geek.prenotazioni.model.Studente;
import it.geek.prenotazioni.service.SegretarioService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.management.OperationsException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class EliminaPrenotazioniCorsiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(EliminaPrenotazioniCorsiServlet.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Studente studente = (Studente)session.getAttribute("studente");
		
		SegretarioService segretario = new SegretarioService();
		Enumeration<String> eli = request.getParameterNames();
		
		if(new Boolean(request.getParameter("transazioneCheck"))){			//richiamo ciclicamente la delete senza transazione...
			//richiamo una serie di delete in transazione:
			//se dopo una cancellazione qualcosa va male il DB non viene alterato!!
			//la prima cancellazione non vale anche se era già stata effettuata!!!
			//o tutte o nessuna...

			//preparo una lista con gli id delle prenotazioni da eliminare
			List<Integer> ids = new ArrayList<>();
			while(eli.hasMoreElements()){
				String s = eli.nextElement();
				log.debug("param name: "+s+" - param value: "+request.getParameter(s));
	
				if(s.contains("corsiDaEliminare")){
					ids.add(Integer.parseInt(request.getParameter(s)));
				}
			}
			
			//e ora che ho la lista della spesa...
			segretario.cancellaPrenotazioni(studente, ids);
			
			
		}else{
			//richiamo ciclicamente la delete senza transazione:
			//se dopo una cancellazione qualcosa va male ho eseguito una operazione monca!!
		
			/*int i=0;*/ //test transazione
			while(eli.hasMoreElements()){
				String s = eli.nextElement();
				log.debug("param name: "+s+" - param value: "+request.getParameter(s));
				
				if(s.contains("corsiDaEliminare")){
					/*if(i==2)throw new RuntimeException();*/ //test transazione
					segretario.cancellaPrenotazione(studente, new Corso(Integer.parseInt(request.getParameter(s))));
					/*i++;*/ //test transazione
				}
			}
		
		}
		//aggiorno lo studente in session
		StudenteDAO studenteDao = new StudenteDAO();
		session.setAttribute("studente", studenteDao.findById(studente.getMatricola()));
		
		//torno alla pagina da cui provengo
		RequestDispatcher rd= request.getRequestDispatcher("/visualizzaPrenotazioni.jsp");
		rd.forward(request, response);
		
	}

}
