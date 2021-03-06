package it.geek.inv.controller;

import it.geek.inv.service.ProductManager;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class InventoryController implements Controller{
	
    protected final Logger log = Logger.getLogger(getClass());

	@Autowired
	private ProductManager productManager;
	
	public void setProductManager(ProductManager productManager){
		this.productManager = productManager;
	}
    
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	log.debug("handleRequest");
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String now = sdf.format(new Date());
        log.debug("Returning home view with " + now);
        
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("now", now);
        model.put("products", this.productManager.getProducts());

        return new ModelAndView("homePage", "model", model);
    }

}
