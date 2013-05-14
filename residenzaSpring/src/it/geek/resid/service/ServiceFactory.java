package it.geek.resid.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceFactory {

	private static ApplicationContext ctx = null;
	
	static{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	public static CittadinoServiceInterface getCittadinoService(){
		return (CittadinoServiceInterface)ctx.getBean("cservice");
	}
	
	public static StatisticheServiceInterface getStatisticheService(){
		return (StatisticheServiceInterface)ctx.getBean("stservice");
	}
	
}
