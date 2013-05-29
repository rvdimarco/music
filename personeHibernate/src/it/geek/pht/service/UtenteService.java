package it.geek.pht.service;

import it.geek.pht.exception.BusinessException;
import it.geek.pht.pojo.Libro;
import it.geek.pht.pojo.Utente;
import it.geek.pht.util.Constants;
import it.geek.pht.util.HibernateUtil;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class UtenteService implements Service<Utente, Integer>, Constants{
	
	Logger log = Logger.getLogger(UtenteService.class);

	@Override
	public List<Utente> getAll(){
		log.debug("getAll");
		List<Utente> utenti = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Utente.class);
			utenti = criteria.list();
			
			tx.commit();
			
		} catch (Exception e) {
			log.error("errore!"+e);
			try{
				tx.rollback();
			}catch(Exception ex){
				log.error("impossibile chiudere la Transaction: "+ex);
			}
			throw new BusinessException(e);
			
		}
		
		return utenti;
	}

	@Override
	public List<Utente> getByExample(Utente u) {
		log.debug("getByExample");
		List<Utente> utenti = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Utente.class);
			//criteria.add(Example.create(p));//BeanUtils.copyProperties sporca l'oggetto: risolviamo così
			criteria.add(Restrictions.ilike(U_NOME, "%"+u.getNome()+"%"));
			criteria.add(Restrictions.ilike(U_COGNOME, "%"+u.getCognome()+"%"));
			criteria.add(Restrictions.ilike(U_USERNAME, "%"+u.getUsername()+"%"));
			utenti = criteria.list();
			
			tx.commit();
			
		} catch (Exception e) {
			log.error("errore!"+e);
			try{
				tx.rollback();
			}catch(Exception ex){
				log.error("impossibile chiudere la Transaction: "+ex);
			}
			throw new BusinessException(e);
			
		}
		
		return utenti;
	}

	@Override
	public Utente get(Integer id) {
		log.debug("get");
		Utente utente = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Utente.class);
			criteria.add(Restrictions.idEq(id));
			utente = (Utente)criteria.uniqueResult();
			
			//o più semplicemente...
			//utente = (Utente)session.get(Utente.class, id);
			
			tx.commit();
			
		} catch (Exception e) {
			log.error("errore!"+e);
			try{
				tx.rollback();
			}catch(Exception ex){
				log.error("impossibile chiudere la Transaction: "+ex);
			}
			throw new BusinessException(e);
			
		}
		
		return utente;
	}

	@Override
	public void save(Utente u) {
		log.debug("save");
		Session session = null;
		Transaction tx = null;
		
		try{
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			session.createSQLQuery("UPDATE libri SET fk_utente = NULL WHERE fk_utente = "+u.getIdUtente()).executeUpdate();
			
			boolean exist = (Utente)session.get(Utente.class, u.getIdUtente()) != null;
			if(!exist){
				session.save(u);
			}else{
				session.merge(u);
			}
			
			for(Libro l : u.getLibros()){
				l = (Libro)session.get(Libro.class, l.getIdLibro());
				l.setUtente(u);
				session.update(l);
			}
			
			tx.commit();
			
		}catch(Exception e){
			log.error("errore!"+e);
			try{
				tx.rollback();
			}catch(Exception ex){
				log.error("impossibile effettuare il rollback: "+ex);
				throw new BusinessException(ex);
			}
			throw new BusinessException(e);
		}
		
	}

	@Override
	public void delete(Utente u) {
		log.debug("delete");
		Session session = null;
		Transaction tx = null;
		
		try{
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			u = (Utente)session.get(Utente.class, u.getIdUtente());
			
			for(Libro l : u.getLibros()){
				l = (Libro)session.get(Libro.class, l.getIdLibro());
				l.setUtente(null);
				session.update(l);
			}
			
			session.delete(u);
			
			tx.commit();
			
		}catch(Exception e){
			log.error("errore!"+e);
			try{
				tx.rollback();
			}catch(Exception ex){
				log.error("impossibile effettuare il rollback: "+ex);
				throw new BusinessException(ex);
			}
			throw new BusinessException(e);
		}
		
	}

	@Override
	public List<Utente> getOrphans() {
		log.warn("getOrphans - funzione non implementata");
		throw new UnsupportedOperationException("funzione non implementata");
	}
	
}
