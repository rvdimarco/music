package it.geek.resid.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.struts.util.LabelValueBean;

public class Collezioni {
	
	public static List<LabelValueBean> getOptions(String tab){
		return getOptionsFiltered(tab,null,null);
	}
	
	public static List<LabelValueBean> getOptionsFiltered(String tab, String whereColumn, Integer whereValue){
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		LabelValueBean lvb = new LabelValueBean("","0");		
		List<LabelValueBean> lis = new ArrayList<LabelValueBean>();
		lis.add(lvb);
		
		try{
			c = MyJNDIConnection.getConnection();
			String sql = "SELECT * FROM " + tab;
			if(whereColumn!=null && whereValue!=null){
				sql+=" WHERE "+whereColumn+"=?";
			}
			ps = c.prepareStatement(sql);
			if(whereColumn!=null && whereValue!=null){
				ps.setInt(1, whereValue);
			}
			rs = ps.executeQuery();
			
			LabelValueBean l = null;
			while(rs.next()){
				String value = rs.getString(1);//codice
				String label = rs.getString(2);//descrizione
				l = new LabelValueBean(label,value);
				lis.add(l);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				rs.close();
				ps.close();
				c.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return lis;
		
	}
	
}
