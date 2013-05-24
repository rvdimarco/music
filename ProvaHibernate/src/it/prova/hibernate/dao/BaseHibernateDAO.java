package it.prova.hibernate.dao;

import it.prova.hibernate.sessionfactory.HibernateSessionFactory;

import org.hibernate.Session;


public class BaseHibernateDAO implements IBaseHibernateDAO {
	
	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}
	
}