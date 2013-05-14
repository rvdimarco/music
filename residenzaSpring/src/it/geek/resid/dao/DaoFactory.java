package it.geek.resid.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DaoFactory {

	private static ApplicationContext ctx = null;
	
	static{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	public static UtenteDaoInterface getUtenteDao(){
		return (UtenteDaoInterface)ctx.getBean("udao");
	}
	
	public static CittadinoDaoInterface getCittadinoDao(){
		return (CittadinoDaoInterface)ctx.getBean("cdao");
	}
	
	public static InfoRegioneDaoInterface getInfoRegioneDao(){
		return (InfoRegioneDaoInterface)ctx.getBean("irdao");
	}
	
}
