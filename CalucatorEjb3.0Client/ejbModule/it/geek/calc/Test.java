package it.geek.calc;

import java.util.Hashtable;
import java.util.Properties;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

public class Test {

	/**
	 * @param args
	 */
	
	// se sono in un "managed bean" mi basta questo!!!!!!!!!
	@EJB/*(name="ejb/Calcur")*/
	private static CalculatorInterface calcu;
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		// ma visto che non sono in un "managed bean"...
		Properties props = new Properties();
	    props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
	    props.put(Context.PROVIDER_URL, "localhost:1099");
		
	    InitialContext ctx = new InitialContext(props);
	    Object obj = ctx.lookup("CalculatorEAP3/ejb/Calcu/remote");
	    CalculatorInterface calcu = (CalculatorInterface)PortableRemoteObject.narrow(obj, CalculatorInterface.class);
	    //...ho dovuto fare la lookup (l'annotation in questo caso è inutile)   
	    
		System.out.println(calcu.sum(2, 5));
		
	}

}
