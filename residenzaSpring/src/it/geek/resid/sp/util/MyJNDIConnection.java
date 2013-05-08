package it.geek.resid.sp.util;


import it.geek.resid.sp.exception.BusinessException;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class MyJNDIConnection {
	
	private static Logger log = Logger.getLogger(MyJNDIConnection.class);
	
	private static String resource_str = "java:/comp/env/jdbc/ResidenzaDB";
	
	public static Connection getConnection(){
		
			Connection connection = null;
			
			try {
				
				InitialContext cxt = new InitialContext();
				DataSource ds = (DataSource) cxt.lookup( resource_str );
				connection = ds.getConnection();

				
			} catch (NamingException e) {
				log.error("non ho trovato la risorsa!");
				throw new BusinessException(e);
			} catch (SQLException e) {
				log.error("non ho la connessione!");
				throw new BusinessException(e);
			}	
			
			log.info("using JNDI connection");
			return connection;
	} 
	
	
}
