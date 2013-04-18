package it.geek.log.test;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Prova {

	private static Logger logger = Logger.getLogger(Prova.class);
	
	public static void main(String[] args) {

		//BasicConfigurator.configure();
		PropertyConfigurator.configure("log4j.properties");
		
		logger.debug("Debug");
		logger.info("Info");
		logger.error("Error");
		
		/*
		 0 [main] DEBUG it.geek.log.test.Prova  - Debug
		 2 [main] INFO  it.geek.log.test.Prova  - Info
		 2 [main] ERROR it.geek.log.test.Prova  - Error
		 * */

	}

}
