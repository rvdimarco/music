package it.prova.hibernate.dao;

import it.prova.model.Libro;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

public class LibroDAO extends BaseHibernateDAO {

	// property constants
	public static final String TITOLO = "titolo";
	public static final String PAGINE = "pagine";

	public void save(Libro transientInstance) {
		getSession().save(transientInstance);
	}
	
	/*questo metodo viene usato solo per 'forzare il salvataggio'
	 * infatti hibernate automaticamente rileva i cambiamenti fatti attraverso il 'set' 
	 * all'interno del persistent context, sulla chiusura della transazione li rende persistenti sul db 
	*/
	public void update(Libro transientInstance) {
		getSession().saveOrUpdate(transientInstance);
	}

	public void delete(Libro persistentInstance) {
		getSession().delete(persistentInstance);
	}

	public Libro findById(Integer id) {
		Libro instance = (Libro) getSession().get(Libro.class, id);
		return instance;
	}

	@SuppressWarnings("unchecked")
	public List<Libro> findByExample(Libro instance) {
		List<Libro> results = getSession().createCriteria("it.prova.Libro")
				.add(Example.create(instance)).list();
		return results;
	}

	@SuppressWarnings("unchecked")
	public List<Libro> findByProperty(String propertyName, Object value) {
		String queryString = "from Libro as model where model." + propertyName
				+ "= ?";
		Query queryObject = getSession().createQuery(queryString);
		queryObject.setParameter(0, value);
		return queryObject.list();
	}

	public List<Libro> findByTitolo(Object titolo) {
		return findByProperty(TITOLO, titolo);
	}

	public List<Libro> findByPagine(Object pagine) {
		return findByProperty(PAGINE, pagine);
	}

	public Libro merge(Libro detachedInstance) {
		Libro result = (Libro) getSession().merge(detachedInstance);
		return result;
	}

	/*
	 * public void attachDirty(Libro instance) { try {
	 * getSession().saveOrUpdate(instance); } catch (RuntimeException re) {
	 * throw re; } }
	 * 
	 * public void attachClean(Libro instance) { try {
	 * getSession().lock(instance, LockMode.NONE); } catch (RuntimeException re)
	 * { throw re; } }
	 */
}