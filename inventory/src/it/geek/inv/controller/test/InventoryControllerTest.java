package it.geek.inv.controller.test;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import it.geek.inv.controller.InventoryController;
import it.geek.inv.service.SimpleProductManager;
import junit.framework.TestCase;

public class InventoryControllerTest extends TestCase{

	   public void testHandleRequestView() throws Exception{		
	        InventoryController controller = new InventoryController();
	        controller.setProductManager(new SimpleProductManager());
	        ModelAndView modelAndView = controller.handleRequest(null, null);		
	        assertEquals("homePage", modelAndView.getViewName());
	        assertNotNull(modelAndView.getModel());
	        Map modelMap = (Map) modelAndView.getModel().get("model");
	        String nowValue = (String) modelMap.get("now");
	        assertNotNull(nowValue);
	    }
	
}
