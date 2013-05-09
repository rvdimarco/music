package it.geek.resid.sp.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceFactory {
	
private static ApplicationContext ctx;
	
	static {
		
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
	}
	
	public static UtenteService getUtenteService () {
		
		return (UtenteService)ctx.getBean("utenteservice");
		
	}
	
}
