package it.geek.inv.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

//Questa non viene utilizzata, stiamo lavorando annotation based...
//servono dei jar ad hoc e l'utilizzo delle annotations @Valid e
//di quelle per validare (@Max, @Min,@NotNull, ...)
public class PriceIncreaseValidator implements Validator {
	
	private int DEFAULT_MIN_PERCENTAGE = 0;
	private int DEFAULT_MAX_PERCENTAGE = 50;
	private int minPercentage = DEFAULT_MIN_PERCENTAGE;
	private int maxPercentage = DEFAULT_MAX_PERCENTAGE;

	/** Logger for this class and subclasses */
	protected final Logger log = Logger.getLogger(getClass());

	public boolean supports(Class clazz) {
		return PriceIncrease.class.equals(clazz);
	}

	public void validate(Object obj, Errors errors) {
		PriceIncrease pi = (PriceIncrease) obj;
			if (pi == null) {
				errors.rejectValue("percentage", "error.not-specified", null, "Value required.");
	        }
	        else {
	        	log.info("Validating with " + pi + ": " + pi.getPercentage());
	            if (pi.getPercentage() > maxPercentage) {
	                errors.rejectValue("percentage", "error.too-high",
	                    new Object[] {new Integer(maxPercentage)}, "Value too high.");
	            }
	            if (pi.getPercentage() <= minPercentage) {
	                errors.rejectValue("percentage", "error.too-low",
	                    new Object[] {new Integer(minPercentage)}, "Value too low.");
	            }
	        }
	}

    public void setMinPercentage(int i) {
        minPercentage = i;
    }

    public int getMinPercentage() {
        return minPercentage;
    }

    public void setMaxPercentage(int i) {
        maxPercentage = i;
    }

    public int getMaxPercentage() {
        return maxPercentage;
    }
	
}
