package it.geek.resid.sp.dao;

import it.geek.resid.sp.exception.BusinessException;
import it.geek.resid.sp.pojo.Ruolo;
import it.geek.resid.sp.pojo.Utente;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class UtenteDAOImplB implements UtenteDAO {
	
	Logger log = Logger.getLogger(UtenteDAOImplB.class);

	@Override
	public Utente findById(String id, Connection c) {
		log.info("UtenteDAO::findById");
		Utente utente = new Utente();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		SAXBuilder builder = new SAXBuilder();
		Document document = null;
		
		try {
			document = builder.build(new File("data_base.xml"));
		} catch (JDOMException | IOException e) {
			log.error("errore: "+e);
			throw new BusinessException(e);
		}

		Element rootElement = document.getRootElement();
		
		List<Element> children = rootElement.getChildren(); 
		Iterator<Element> iterator = children.iterator();
		Element element = null;
		String name = null;
		String text = null;
		while (iterator.hasNext()){ 
		   element = iterator.next();
		   name = element.getName();
		   text = element.getText();
		   switch (name) {
		   		case "username":
		   			if(!id.equals(text)){
		   				throw new BusinessException("utente non trovato con username '"+id+"'");
		   			}
		   			utente.setUsername(text);
		   			break;
		   		case "password":
		   			utente.setPassword(text);
		   			break;
		   		case "nome":
		   			utente.setNome(text);
		   			break;
		   		case "cognome":
		   			utente.setCognome(text);
		   			break;
		   		case "dataNascita":
					try {
						utente.setDataNascita(sdf.parse(text));
					} catch (ParseException e) {
						log.error("errore: "+e);
						throw new BusinessException(e);
					}
		   			break;
		   		case "Ruolo":
		   			Ruolo ruolo = new Ruolo();
		   			List<Element> ruoloChildren = element.getChildren();
		   			Iterator<Element> rcIterator = ruoloChildren.iterator();
		   			Element rcElement = null;
		   			String rcName = null;
		   			String rcText = null;
		   			while (rcIterator.hasNext()){ 
		   			   rcElement = rcIterator.next();
		   			   rcName = rcElement.getName();
		   			   rcText = rcElement.getText();
		   			   switch (rcName) {
							case "codice":
								ruolo.setCodice(Integer.parseInt(rcText));
								break;
							case "descrizione":
								ruolo.setDescrizione(rcText);
								break;
							case "attivo":
								ruolo.setAttivo("1".equals(rcText)?true:false);
								break;
					   		default:
					   			log.info("elemento non trovato: "+rcName);
					   			break;

		   			   }
		   			}
		   			utente.setRuolo(ruolo);
		   			break;
		   		case "dataRegistrazione":
			try {
				utente.setDataRegistrazione(sdf.parse(text));
			} catch (ParseException e) {
				log.error("errore: "+e);
				throw new BusinessException(e);
			}
		   			break;

		   		default:
		   			log.info("elemento non trovato: "+name);
		   			break;
		   }
		   
		} 
		
		
		return utente;
	}



}
