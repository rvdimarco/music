package it.geek.ms.controller;

import it.geek.ms.form.HomeForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class HomeAction extends Action {

	public HomeAction() {
		// TODO Auto-generated constructor stub
	}
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		 
		String path = null;
		
		HomeForm home = (HomeForm)form;
		
		if("".equals(home.getParola())){
			path = "failure";
		}else{
			path = "success";
		}
		
		return mapping.findForward(path);
	}

}
