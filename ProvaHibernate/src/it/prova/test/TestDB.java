package it.prova.test;

import org.hibernate.Transaction;

import it.prova.hibernate.dao.LibroDAO;
import it.prova.hibernate.dao.MyDaoFactory;
import it.prova.hibernate.dao.UtenteDAO;
import it.prova.hibernate.sessionfactory.HibernateSessionFactory;
import it.prova.model.Libro;
import it.prova.model.Utente;

public class TestDB {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		UtenteDAO utenteDAO = MyDaoFactory.getUtenteDAO();
		LibroDAO libroDAO = MyDaoFactory.getLibroDAO();

		Utente utente = new Utente();
		utente.setCognome("BIANCHI");
		utente.setNome("MARIO");
		utente.setUsername("mariobi");
		utente.setPassword("mariobi");
		
		Libro libro = new Libro();
		libro.setPagine(90);
		libro.setTitolo("uml distilled");
		libro.setUtente(utente);
		
		Libro secondoLibro = new Libro();
		secondoLibro.setPagine(600);
		secondoLibro.setTitolo("delitto e castigo");
		secondoLibro.setUtente(utente);

		
		Transaction tx = HibernateSessionFactory.getSession().beginTransaction();
		
		utenteDAO.save(utente);
		libroDAO.save(libro);
		libroDAO.save(secondoLibro);
		
//		Utente nuovo = utenteDAO.findById(1);
//		utenteDAO.delete(nuovo);
//		nuovo.setUsername("abracadabra");
//		System.out.println(nuovo.getLibros());
//		libro.setUtente(nuovo);
//		libroDAO.save(libro);
//		//per aggiornare il set di libri di utente
//		//anche se non è la giusta via
//		// modo più corretto è quello di usare l'addTo.....
//		utenteDAO.refresh(nuovo);
//		System.out.println(nuovo.getLibros());
//		nuovo.setPassword("cccccc");
//		utenteDAO.update(nuovo);
//		System.out.println(nuovo);
//		libro = libroDAO.findById(2);
//		System.out.println(libro.getUtente().getIdUtente());
//		libro.setPagine(152);
//		libroDAO.save(libro);
//		utenteDAO.save(nuovo);
//		libro.setUtente(nuovo);
//		libroDAO.save(libro);
//		utenteDAO.save(utente);
//		System.out.println(nuovo);
//		System.out.println("tutti sono: "+utenteDAO.findAll().size());
//		utenteDAO.delete(nuovo);
//		System.out.println("dopo tutti sono: "+utenteDAO.findByExample(new Utente()));
//		System.out.println("I Mario sono: "+utenteDAO.findAllByNomeIgnoreCase("mario").size());
		
		tx.commit();
		
		
		/*
		Utente nuovo = utenteDAO.findById(5);
		utenteDAO.save(utente);
		System.out.println(nuovo);
		System.out.println("tutti sono: "+utenteDAO.findAll().size());
		//utenteDAO.delete(nuovo);
		//System.out.println("dopo tutti sono: "+utenteDAO.findAll().size());
		System.out.println("I giuseppe sono: "+utenteDAO.findAllByNomeIgnoreCase("giuseppe").size());
		
		 */
		

	}

}
