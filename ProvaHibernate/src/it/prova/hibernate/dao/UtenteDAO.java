package it.prova.hibernate.dao;

import it.prova.model.Utente;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

public class UtenteDAO extends BaseHibernateDAO {

	// property constants
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String NOME = "nome";
	public static final String COGNOME = "cognome";

	public void save(Utente transientInstance) {
		getSession().save(transientInstance);
	}

	public void delete(Utente persistentInstance) {
		getSession().delete(persistentInstance);
	}
	
	/*questo metodo viene usato solo per 'forzare il salvataggio'
	 * infatti hibernate automaticamente rileva i cambiamenti fatti attraverso il 'set' 
	 * all'interno del persistent context, sulla chiusura della transazione li rende persistenti sul db 
	*/
	public void update(Utente persistentInstance) {
		getSession().saveOrUpdate(persistentInstance);
	}

	public void refresh(Utente persistentInstance){
		getSession().refresh(persistentInstance);
	}

	public Utente findById(Integer id) {
		return (Utente) getSession().get(Utente.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Utente> findByExample(Utente instance) {
		Criteria cri = getSession().createCriteria(Utente.class);
		cri.add(Example.create(instance));
		List<Utente> results = cri.list();
		return results;
	}

	@SuppressWarnings("unchecked")
	public List<Utente> findByLoginAndPassword(String login, String pwd) {
		Criteria cri = getSession().createCriteria(Utente.class);
		cri.add(Restrictions.eq(USERNAME, login));
		cri.add(Restrictions.eq(PASSWORD, pwd));
		List<Utente> results = cri.list();
		return results;
	}

	@SuppressWarnings("unchecked")
	public List<Utente> findAllByNomeIgnoreCase(String nome) {
		Criteria cri = getSession().createCriteria(Utente.class);
		cri.add(Restrictions.ilike(NOME, nome));
		List<Utente> results = cri.list();
		return results;
	}

	/*
	 * 
	 * Criteria cri = s.createCriteria(ProspettoInformativo.class);
	 * 
	 * cri.add(Restrictions.eq(FLG_APPROVATO,PROSPETTO_INVIATO_FLAG ));
	 * cri.add(Restrictions.eq(AZIENDA_PRIMARY_KEY, a.getPrimaryKey()));
	 */

	@SuppressWarnings("unchecked")
	public List<Utente> findAll() {
		String queryString = "from Utente";
		Query queryObject = getSession().createQuery(queryString);
		return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	public List<Utente> findByProperty(String propertyName, Object value) {
		String queryString = "from Utente as model where model." + propertyName
				+ "= ?";
		Query queryObject = getSession().createQuery(queryString);
		queryObject.setParameter(0, value);
		return queryObject.list();
	}

	public List<Utente> findByUsername(Object username) {
		return findByProperty(USERNAME, username);
	}

	public List<Utente> findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List<Utente> findByNome(Object nome) {
		return findByProperty(NOME, nome);
	}

	public List<Utente> findByCognome(Object cognome) {
		return findByProperty(COGNOME, cognome);
	}
	public Utente merge(Utente detachedInstance) {
		Utente result = (Utente) getSession().merge(detachedInstance);
		return result;
	}

	/*
	 * 
	 * public void attachDirty(Utente instance) { try {
	 * getSession().saveOrUpdate(instance); } catch (RuntimeException re) {
	 * throw re; } }
	 * 
	 * public void attachClean(Utente instance) { try {
	 * getSession().lock(instance, LockMode.NONE); } catch (RuntimeException re)
	 * { throw re; } }
	 */
}