creare un nuovo Dynamic Web project con eclipse.

Copiare il contenuto della cartella struts-1.3.10\lib nella WEB-INF\lib del nostro progetto

Copiare nel web.xml il seguente frammento di codice

  	<servlet>
	        <servlet-name>action</servlet-name>
	        <servlet-class>
	            org.apache.struts.action.ActionServlet
	        </servlet-class>
	        <init-param>
	            <param-name>config</param-name>
	            <param-value>/WEB-INF/struts-config.xml</param-value>
	        </init-param>
	        <load-on-startup>1</load-on-startup>
	</servlet>
	<servlet-mapping>
	    <servlet-name>action</servlet-name>
	    <url-pattern>*.do</url-pattern>
	</servlet-mapping>


Creare il file struts-config.xml dentro WEB-INF con (ad esempio) il seguente contenuto

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE struts-config PUBLIC "-//Apache Software Foundation//DTD Struts Configuration 

1.3//EN" "http://struts.apache.org/dtds/struts-config_1_3.dtd">


<struts-config>
	<form-beans>
		<form-bean name="LoginForm"
			type="it.prova.struts.actions.LoginForm" />
	</form-beans>

	<global-exceptions>
	</global-exceptions>
	<global-forwards></global-forwards>

	<action-mappings>
		<action path="/login" name="LoginForm" scope="request"
			type="it.prova.struts.actions.LoginAction">
			<forward name="success" path="/home.jsp" />
			<forward name="failure" path="/error.jsp" />
		</action>
	</action-mappings>

	<message-resources parameter="ApplicationResources"></message-resources>

</struts-config>


questa è una action di prova

public class LoginAction extends Action {


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		LoginForm l = (LoginForm) form;

		//...

		return mapping.findForward("success");

	}

}



Per utilizzare le custom tag di struts nelle jsp è necessario importare le seguenti linee

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
                