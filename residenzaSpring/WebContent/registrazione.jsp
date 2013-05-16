<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrazione</title>
</head>
<body><center><b><h2>Registrazione</h2></b></center><br/>
	<html:form action="gestioneUtente.do">
		<html:hidden property="method" value="eseguiRegistrazione"/>
		<table>
			<tr><td>Nome:</td><td><html:text property="nome"/></td></tr>
			<tr><td>Cognome:</td><td><html:text property="cognome"/></td></tr>
			<tr><td>Data di nascita (dd/mm/aaaa):</td><td><html:text property="dataNascita"/></td></tr>
			<tr><td>Username:</td><td><html:text property="username"/></td></tr>
			<tr>
				<td>Ruolo:</td>
				<td>
					<html:select name ="utenteForm" property="ruolo.codice">
						<html:optionsCollection name="utenteForm" property="listaRuoli"/>
					</html:select>
				</td>
			</tr>
			<tr><td>Password:</td><td><html:password property="password"/></td></tr>
			<tr><td>Confirm password:</td><td><html:password property="confirmPassword" onblur="if(this.value!=document.forms['utenteForm'].password.value){alert('digitazione errata');this.value='';document.forms['utenteForm'].password.value=''}"/></td></tr>
		</table>
		<html:submit>invia</html:submit>
	</html:form>

</body>
</html>