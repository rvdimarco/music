package it.geek.pht.test;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import it.geek.pht.pojo.Persona;

public class PersonaTest {

	  public static void main(String[] args) {
		    System.out.println("PersonaTest");
		    
		    // Recupero la session (di Hibernate)
		    Session session=new Configuration().configure().buildSessionFactory().getCurrentSession();
		    
		    //Creo una nuova persona
		    Persona p=new Persona();
		    p.setIdPersona(2);// non ha effetto!
		    p.setNome("Michelino Brevetti");
		    p.setEmail("m.brevetti@html.it");
		    
		    //utilizziamo un modello transazionale programmatico
		    session.beginTransaction();
		    
		    //Chiedo al middleware di salvare questo oggetto nel database
		    session.save(p);
		    
		    //ATTENZIONE: QUESTO STATEMENT MODIFICA UN OGGETTO IN STATO "PERSISTENTE" -> quando la Session sarà rilasciata...
		    // 			  ...le modifiche alla Persona saranno scolpite sulla base dati!
		    p.setNome("Giorgino Grotti");
		    
		    //fine della transazione: salviamo tramite commit()
		    session.getTransaction().commit();
		  }
	
}
