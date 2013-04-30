package it.geek.ms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import it.geek.ms.dao.IDAO;
import it.geek.ms.model.Ruolo;

public class RuoloDAO implements IDAO<Ruolo, Integer> {

	@Override
	public Ruolo findById(Integer id, Connection c) {
		throw new UnsupportedOperationException("RuoloDAO::findById::metodo non implementato");
	}

	@Override
	public List<Ruolo> findAll(Connection c) {
		List<Ruolo> ret = null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT codice, descrizione ");
		sb.append("FROM ruoli ");
		
		try{
			ps = c.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			
			ret = new ArrayList<Ruolo>();
			Ruolo r = null;
			while(rs.next()){
				r = new Ruolo();
				r.setCodice(rs.getInt("codice"));
				r.setDescrizione(rs.getString("descrizione"));
				ret.add(r);
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
		}
		
		return ret;
	}

	@Override
	public List<Ruolo> findByExample(Ruolo e, Connection c) {
		throw new UnsupportedOperationException("RuoloDAO::findByExample::metodo non implementato");	}

	@Override
	public boolean delete(Integer id, Connection c) {
		throw new UnsupportedOperationException("RuoloDAO::delete::metodo non implementato");	}

	@Override
	public boolean insert(Ruolo e, Connection c) {
		throw new UnsupportedOperationException("RuoloDAO::insert::metodo non implementato");	}

	@Override
	public boolean update(Ruolo e, Connection c) {
		throw new UnsupportedOperationException("RuoloDAO::update::metodo non implementato");	}

}
