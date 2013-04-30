package it.geek.ms.controller;

import java.util.Arrays;
import java.util.List;

import it.geek.ms.factory.ServiceFactory;
import it.geek.ms.form.HomeForm;
import it.geek.ms.model.Autore;

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
			
			String[] lista1 = {"uno","due","tre"};
			request.setAttribute("listaDiStringhe", Arrays.asList(lista1));
			
			List<Autore> lista2 = ServiceFactory.getAutoreService().getAll();
			request.setAttribute("listaDiAutori", lista2);
			
			path = "success";
		}
		
		return mapping.findForward(path);
	}

}
