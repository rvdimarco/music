package it.geek.calc;

import javax.ejb.Stateless;

@Stateless(name="ejb/Calcu")
public class Calculator implements CalculatorInterface{

	@Override
	public int sum(int a, int b){
		return (a+b);
	}
}
