package it.geek.ejb2;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class HelloWorldBean implements SessionBean {

	// Methods of Remote interface
	public String sayHello() {
	return "Hello, world !";
	}
	
	// Methods of Home interface
	public void ejbCreate() {}
	
	// Methods of SessionBean interface
	protected SessionContext ctx;
	
	public void setSessionContext(SessionContext ctx) {
	this.ctx = ctx;
	}
	
	public void ejbRemove() {}
	
	public void ejbActivate() {}
	
	public void ejbPassivate() {}
}
