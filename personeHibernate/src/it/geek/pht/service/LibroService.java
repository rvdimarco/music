package it.geek.pht.service;

import it.geek.pht.exception.BusinessException;
import it.geek.pht.pojo.Libro;
import it.geek.pht.util.Constants;
import it.geek.pht.util.HibernateUtil;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class LibroService implements Service<Libro, Integer>, Constants{
	
	Logger log = Logger.getLogger(LibroService.class);

	@Override
	public List<Libro> getAll(){
		log.debug("getAll");
		List<Libro> libri = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Libro.class);
			libri = criteria.list();
			
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
		
		return libri;
	}

	@Override
	public List<Libro> getByExample(Libro l) {
		log.debug("getByExample");
		List<Libro> libri = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Libro.class);
			//criteria.add(Example.create(p));//BeanUtils.copyProperties sporca l'oggetto: risolviamo così
			criteria.add(Restrictions.ilike(L_TITOLO, "%"+l.getTitolo()+"%"));
			if(l.getPagine()!=null && l.getPagine()!=0){
				criteria.add(Restrictions.le(L_PAGINE, l.getPagine()));
			}
			libri = criteria.list();
			
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
		
		return libri;
	}

	@Override
	public Libro get(Integer id) {
		log.debug("get");
		Libro libro = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Libro.class);
			criteria.add(Restrictions.idEq(id));
			libro = (Libro)criteria.uniqueResult();
			
			//o più semplicemente...
			//libro = (Libro)session.get(Libro.class, id);
			
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
		
		return libro;
	}

	@Override
	public void save(Libro l) {
		log.debug("save");
		Session session = null;
		Transaction tx = null;
		
		try{
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			session.saveOrUpdate(l);
			
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
	public void delete(Libro l) {
		log.debug("delete");
		Session session = null;
		Transaction tx = null;
		
		try{
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			l = (Libro)session.get(Libro.class, l.getIdLibro());
			session.delete(l);
			
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
	public List<Libro> getOrphans() {
		log.debug("getByExample");
		List<Libro> libri = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Libro.class);
			//criteria.add(Example.create(p));//BeanUtils.copyProperties sporca l'oggetto: risolviamo così
			criteria.add(Restrictions.isNull(L_UTENTE));
			libri = criteria.list();
			
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
		
		return libri;
	}
	
}
