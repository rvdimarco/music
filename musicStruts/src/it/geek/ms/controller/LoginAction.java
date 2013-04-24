package it.geek.ms.controller;

import it.geek.ms.form.LoginForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LoginAction extends Action {

	private static Logger log = Logger.getLogger(LoginAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String forwardPath = "";
		
		LoginForm login = (LoginForm) form;
		log.debug("form: "+login);
		
		//BeanUtils.copyProperties(dest, orig);
		
		if("aaa".equals(login.getUsername())&&"aaa".equals(login.getPassword())){
			forwardPath = "success";
		}else{
			forwardPath = "failure";
		}
		
		return mapping.findForward(forwardPath);
	}

}
