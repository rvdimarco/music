package it.geek.pht.service;

import it.geek.pht.exception.BusinessException;
import it.geek.pht.pojo.Persona;
import it.geek.pht.util.HibernateUtil;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

public class PersonaService implements Service<Persona, Integer>{
	
	Logger log = Logger.getLogger(PersonaService.class);

	@Override
	public List<Persona> getAll(){
		log.debug("getAll");
		List<Persona> persone = null;
		Session session = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			
			Criteria criteria = session.createCriteria(Persona.class);
			persone = criteria.list();
			
		} catch (Exception e) {
			log.error("errore!"+e);
			throw new BusinessException(e);
			
		}finally{
			try{
				session.close();
			}catch(Exception e){
				log.error("impossibile chiudere la Session: "+e);
			}
		}
		
		return persone;
	}

	@Override
	public List<Persona> getByExample(Persona p) {
		log.debug("getByExample");
		List<Persona> persone = null;
		Session session = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			
			Criteria criteria = session.createCriteria(Persona.class);
			criteria.add(Example.create(p));
			persone = criteria.list();
			
		} catch (Exception e) {
			log.error("errore!"+e);
			throw new BusinessException(e);
			
		}finally{
			try{
				session.close();
			}catch(Exception e){
				log.error("impossibile chiudere la Session: "+e);
			}
		}
		
		return persone;
	}

	@Override
	public Persona get(Integer id) {
		log.debug("get");
		Persona persona = null;
		Session session = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			
			Criteria criteria = session.createCriteria(Persona.class);
			criteria.add(Restrictions.idEq(id));
			persona = (Persona)criteria.uniqueResult();
			
			//o più semplicemente...
			//persona = (Persona)session.get(Persona.class, id);
			
		} catch (Exception e) {
			log.error("errore!"+e);
			throw new BusinessException(e);
			
		}finally{
			try{
				session.close();
			}catch(Exception e){
				log.error("impossibile chiudere la Session: "+e);
			}
		}
		
		return persona;
	}

	@Override
	public void save(Persona p) {
		log.debug("save");
		Session session = null;
		Transaction tx = null;
		
		try{
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			session.saveOrUpdate(p);
			
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
		finally{
			try{
				session.close();
			}catch(Exception e){
				log.error("impossibile chiudere la Session: "+e);
			}			
		}
	}

	@Override
	public void delete(Persona p) {
		log.debug("delete");
		Session session = null;
		Transaction tx = null;
		
		try{
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			p = (Persona)session.get(Persona.class, p.getIdPersona());
			session.delete(p);
			
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
		finally{
			try{
				session.close();
			}catch(Exception e){
				log.error("impossibile chiudere la Session: "+e);
			}			
		}
	}
	
}
