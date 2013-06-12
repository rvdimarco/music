package it.geek.ejb2.test;


import it.geek.ejb2.HelloWorld;
import it.geek.ejb2.HelloWorldHome;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String result = null;
		try{
			
			Object obj = EjbUtil.getHome("HelloWorld");
			
			HelloWorldHome home = 
					(HelloWorldHome)javax.rmi.PortableRemoteObject.narrow(obj,HelloWorldHome.class);
			
			HelloWorld ejb = home.create();
			result = ejb.sayHello();

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(result);

	}

}
