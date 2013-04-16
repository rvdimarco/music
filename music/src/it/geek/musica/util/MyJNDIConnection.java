package it.geek.musica.util;


import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MyJNDIConnection {
	
	/*Configurazione DB via JNDI:
	
	- [tomcat root]/conf/contex.xml
	  all'interno del tag <Context>...</Context> inserisco il seguente tag  

  <Resource name="jdbc/MusicDB" auth="Container" type="javax.sql.DataSource"
			   maxActive="100" maxIdle="30" maxWait="10000"
			   username="root" password="root" driverClassName="com.mysql.jdbc.Driver"
			   url="jdbc:mysql://localhost:3306/music"/>
			   
	- [tomcat root]/lib
	  copiare  all'interno di questa cartella il connettore (jar) (eliminarlo dalla cartella lib della web app)

	- [app root]/WEB-INF/web.xml
	  all'interno del tag <web-app>...</web-app> inserisco il seguente tag
	  
	<resource-ref>
		<description>Music DB Connection</description>
	    <res-ref-name>jdbc/MusicDB</res-ref-name>
	    <res-type>javax.sql.DataSource</res-type>
	    <res-auth>Container</res-auth>
	</resource-ref>
	
	- per ottenere una connessione dal DataSource effettuo la "lookup" 
	  (vedi il codice sotto)

	 * */	

	private static String resource_str = "java:/comp/env/jdbc/MusicDB";
	

	public static Connection getConnection(){
		
			Connection connection = null;
			
			try {
				
				InitialContext cxt = new InitialContext();
				DataSource ds = (DataSource) cxt.lookup( resource_str );
				connection = ds.getConnection();

				
			} catch (NamingException e) {
				System.out.println("non ho trovato la risorsa!");
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("non ho la connessione!");
				e.printStackTrace();
			}	
			
			return connection;
	} 
	
	
}
