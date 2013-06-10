package it.geek.inv.controller;


import it.geek.inv.service.PriceIncrease;
import it.geek.inv.service.ProductManager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PriceIncreaseFormController {

	   /** Logger for this class and subclasses */
    protected final Logger log = Logger.getLogger(getClass());

    @Autowired
    private ProductManager productManager;

    @RequestMapping("/increase")
    public String increase(@ModelAttribute("priceincreasePage")
    							 PriceIncrease price, BindingResult result){

        int increase = ((PriceIncrease) price).getPercentage();
        log.info("Increasing prices by " + increase + "%.");

        productManager.increasePrice(increase);

        log.info("returning from PriceIncreaseForm view to homePage");

        return "homeController";
    }

    @RequestMapping("/priceincrease")
    public ModelAndView formBackingObject() {
        PriceIncrease priceIncrease = new PriceIncrease();
        priceIncrease.setPercentage(20);
        return new ModelAndView("priceincreasePage", "command", priceIncrease);
    }

    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }

    public ProductManager getProductManager() {
        return productManager;
    }
}
