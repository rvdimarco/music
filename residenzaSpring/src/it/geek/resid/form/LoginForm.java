package it.geek.resid.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class LoginForm extends ActionForm {

	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		
		if (username==null || "".equals(username)) {
			errors.add("username", new ActionMessage("errors.login.username.blank"));
		}
		if (username!=null && !"".equals(username) && username.length()<3) {
			errors.add("username", new ActionMessage("errors.login.lunghezza_insufficiente"));
		}
		if (username!=null && !"".equals(username) && (username.contains("'")||username.contains("\"")||username.contains("/")||username.contains("\\")||username.contains("?"))){
			errors.add("username", new ActionMessage("errors.login.caratteri_non_consentiti"));
		}
		
		if (password==null ||"".equals(password)) {
			errors.add("password", new ActionMessage("errors.login.password.blank"));
		}
		if (password!=null && !"".equals(password) && password.length()<3) {
			errors.add("password", new ActionMessage("errors.login.lunghezza_insufficiente"));
		}
		if (password!=null && !"".equals(password) && (password.contains("'")||password.contains("\"")||password.contains("/")||password.contains("\\")||password.contains("?"))){
			errors.add("password", new ActionMessage("errors.login.caratteri_non_consentiti"));
		}
		
		return errors;
	}
	
	
}
