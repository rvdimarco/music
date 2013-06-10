package it.geek.inv.service;

import org.apache.log4j.Logger;

public class PriceIncrease {

	/** Logger for this class and subclasses */
    protected final Logger log = Logger.getLogger(getClass());

    private int percentage;

    public void setPercentage(int i) {
        percentage = i;
        log.info("Percentage set to " + i);
    }

    public int getPercentage() {
        return percentage;
    }
	
}
