package it.geek.ejb2.test;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

public class EjbUtil {

	public static Object getHome(String ejbClassName) throws Exception{
		
		Properties props = new Properties();
	      props.put(Context.INITIAL_CONTEXT_FACTORY,
	            "org.jnp.interfaces.NamingContextFactory");
	      props.put(Context.PROVIDER_URL, "localhost:1099");
		
		Context ctx = new InitialContext(props);
		
		Object obj = ctx.lookup(ejbClassName);
		
		return obj;
	}

}
