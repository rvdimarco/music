package it.geek.pht.service;

import it.geek.pht.exception.BusinessException;
import it.geek.pht.pojo.Persona;
import it.geek.pht.util.Constants;
import it.geek.pht.util.HibernateUtil;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class PersonaService implements Service<Persona, Integer>, Constants{
	
	Logger log = Logger.getLogger(PersonaService.class);

	@Override
	public List<Persona> getAll(){
		log.debug("getAll");
		List<Persona> persone = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Persona.class);
			persone = criteria.list();
			
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
		
		return persone;
	}

	@Override
	public List<Persona> getByExample(Persona p) {
		log.debug("getByExample");
		List<Persona> persone = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Persona.class);
			//criteria.add(Example.create(p));//BeanUtils.copyProperties sporca l'oggetto: risolviamo così
			criteria.add(Restrictions.ilike(NOME, "%"+p.getNome()+"%"));
			criteria.add(Restrictions.ilike(EMAIL, "%"+p.getEmail()+"%"));
			persone = criteria.list();
			
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
		
		return persone;
	}

	@Override
	public Persona get(Integer id) {
		log.debug("get");
		Persona persona = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Persona.class);
			criteria.add(Restrictions.idEq(id));
			persona = (Persona)criteria.uniqueResult();
			
			//o più semplicemente...
			//persona = (Persona)session.get(Persona.class, id);
			
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
		
	}
	
}
