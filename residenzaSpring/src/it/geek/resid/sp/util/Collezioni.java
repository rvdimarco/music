package it.geek.resid.sp.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.struts.util.LabelValueBean;

public class Collezioni {
	
	public static List<LabelValueBean> getOptions(String tab){
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		LabelValueBean lvb = new LabelValueBean("","0");		
		List<LabelValueBean> lis = new ArrayList<LabelValueBean>();
		lis.add(lvb);
		
		try{
			c = MyJNDIConnection.getConnection();
			String sql = "SELECT * FROM " + tab;
			ps = c.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				String value = rs.getString(1);//codice
				String label = rs.getString(2);//descrizione
				LabelValueBean l = new LabelValueBean(label,value);
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
