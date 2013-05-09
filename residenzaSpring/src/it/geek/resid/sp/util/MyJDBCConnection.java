package it.geek.resid.sp.util;


import it.geek.resid.sp.exception.BusinessException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class MyJDBCConnection {
	
	private static Logger log = Logger.getLogger(MyJDBCConnection.class);
	
	private static String driver_class = "com.mysql.jdbc.Driver";
	private static String resource_str = "jdbc:mysql://localhost:3306/geografia?user=root&password=root";
	
	public static Connection getConnection(){
		
			Connection connection = null;
			
			try {
				
				Class.forName(driver_class);
				connection = DriverManager.getConnection(resource_str);

				
			} catch (ClassNotFoundException e) {
				log.error("non ho trovato la risorsa!");
				throw new BusinessException(e);
			} catch (SQLException e) {
				log.error("non ho la connessione!");
				throw new BusinessException(e);
			}	
			
			log.info("using JDBC connection");
			return connection;
	} 
	
	
}
