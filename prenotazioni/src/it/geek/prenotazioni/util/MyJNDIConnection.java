package it.geek.prenotazioni.util;


import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class MyJNDIConnection {
	
	private static Logger log = Logger.getLogger(MyJNDIConnection.class);
	private static String resource_str = "java:/comp/env/jdbc/PrenotazioniDB";
	
	public static Connection getConnection(){
		
			Connection connection = null;
			
			try {
				
				InitialContext cxt = new InitialContext();
				DataSource ds = (DataSource) cxt.lookup( resource_str );
				connection = ds.getConnection();

				
			} catch (NamingException e) {
				log.error("non ho trovato la risorsa! "+e);
				e.printStackTrace();
			} catch (SQLException e) {
				log.error("non ho la connessione!" +e);
				e.printStackTrace();
			}	
			
			return connection;
	} 
	
	
}
