package it.geek.musica.dao.impl;

import it.geek.musica.dao.IDAO;
import it.geek.musica.model.Utente;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UtenteDAO implements IDAO<Utente, String> {

	@Override
	public Utente findById(String id) {
	
		Connection c = null;
		Utente ret = null;
		
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
		
		String sql ="SELECT username, password, cognome, nome FROM utenti where username = ?";
		
		try{
			ps = c.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				ret = new Utente();
				ret.setUsername(rs.getString("username"));
				ret.setPassword(rs.getString("password"));
				ret.setCognome(rs.getString("cognome"));
				ret.setNome(rs.getString("nome"));
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

	@Override
	public List<Utente> findAll() {
		System.out.println("UtenteDAO.findAll::FUNZIONE NON IMPLEMENTATA!");
		return null;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(Utente e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Utente e) {
		// TODO Auto-generated method stub
		return false;
	}

}
