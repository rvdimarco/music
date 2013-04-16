package it.geek.musica.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.geek.musica.dao.IDAO;
import it.geek.musica.model.CasaDiscografica;

public class CasaDiscograficaDAO implements IDAO<CasaDiscografica, String> {

	@Override
	public CasaDiscografica findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CasaDiscografica> findAll() {

		Connection c = null;
		List<CasaDiscografica> ret = null;
		
		//recupero la connessione
		try {
			
			InitialContext cxt = new InitialContext();
			DataSource ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/MusicDB" );
			c = ds.getConnection();

			//Class.forName("com.mysql.jdbc.Driver");
			//c = DriverManager.getConnection("jdbc:mysql://localhost:3306/music?user=root&password=root");
			
		}  catch (NamingException e) {
			System.out.println("non ho trovato la risorsa!");
			e.printStackTrace();
		}/*catch (ClassNotFoundException e) {
			System.out.println("non ho trovato il driver!");
			e.printStackTrace();
		}*/catch (SQLException e) {
			System.out.println("non ho la connessione!");
			e.printStackTrace();
		}
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql ="SELECT nome, sede FROM case_discografiche";
		
		try{
			ps = c.prepareStatement(sql);
			rs = ps.executeQuery();
			
			ret = new ArrayList<CasaDiscografica>();
			CasaDiscografica cadi = null;
			while(rs.next()){
				cadi = new CasaDiscografica();
				cadi.setNome(rs.getString("nome"));
				cadi.setSede(rs.getString("sede"));
				ret.add(cadi);
			}
		}catch(Exception e){
			System.out.println("errore! "+e);
			e.printStackTrace();
		}finally{
			try {
				rs.close();
			} catch (Exception e2) {
				System.out.println("impossibile chiudere il ResultSet");
			}
			try {
				ps.close();
			} catch (Exception e2) {
				System.out.println("impossibile chiudere il PreparedStatement");
			}
			try {
				c.close();
			} catch (Exception e2) {
				System.out.println("impossibile chiudere la Connection");
			}			
		}
		
		return ret;
	
	}



}
