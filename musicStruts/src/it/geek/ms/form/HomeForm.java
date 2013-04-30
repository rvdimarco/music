package it.geek.ms.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class HomeForm extends ActionForm {

	private String parola = "mela";
	
	public HomeForm() {
		// TODO Auto-generated constructor stub
	}

	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

public ActionErrors validate(ActionMapping map, HttpServletRequest req) {
	
		ActionErrors errors = new ActionErrors();
		
		if ("".equals(parola)) {
			errors.add("parola", new ActionMessage("errors.fiels.blank"));
		}
		if (parola.length()<3) {
			errors.add("parola", new ActionMessage("errors.fiels.lunghezza_insufficiente"));
		}
		if ("pera".equalsIgnoreCase(parola)) {
			errors.add("parola", new ActionMessage("errors.fiels.not_pera"));
		}

		
		return errors;
		
	}
	
}
