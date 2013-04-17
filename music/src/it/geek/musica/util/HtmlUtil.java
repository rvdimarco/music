package it.geek.musica.util;

import it.geek.musica.model.CasaDiscografica;

import java.util.Iterator;
import java.util.List;

public class HtmlUtil {

	public static String writeComboBoxCaseDiscografiche
							(List<CasaDiscografica> list){

		String combo = "<select name='id'>";
		Iterator<CasaDiscografica> it = list.iterator();
		CasaDiscografica ca = null;
		while(it.hasNext()){
			ca = it.next();
			combo += "<option value='"+ca.getNome()+"'>"+ca.getNome()+"</option>";
		}
		combo += "</select>";
		
		return combo;
	}
	
}
