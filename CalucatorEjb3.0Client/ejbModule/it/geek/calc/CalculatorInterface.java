package it.geek.calc;

import javax.ejb.Remote;

@Remote
public interface CalculatorInterface {

	public int sum(int a, int b);
	
}
