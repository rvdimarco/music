package it.geek.ejb2;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

public interface HelloWorld extends EJBObject {

	public String sayHello() throws RemoteException;
	
}
