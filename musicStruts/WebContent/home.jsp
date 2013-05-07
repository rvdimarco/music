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
		<title>Insert title here</title>
	</head>
	<body>
	HOME<jsp:include page="header.jsp"/><br/><br/>
		<div>
		<br/>(questo &egrave; un primo esempio di utilizzo della validazione e della tag library html di struts)<br/>
			<html:errors/>
			<html:form styleId="homeForm" action="visualizza.do">
				<html:text property="parola" />
				<html:submit value="vai"/>
			</html:form>
		</div>
		<br/><br/>
		<div>
		<br/><b>operazioni disponibili:</b><br/>
			<c:if test="${utente.amministratore}">
				Vai alla gestione utenti(implementata nella web app music):
				<form name="form1" method="get" action="gestioneUtenti.do">
					<input type="submit" name="vai" value="Vai"/>
					<input type="hidden" name="codop" value="list"/>
				</form><br/><br/>		
			</c:if>

			Scegli la case discografica su cui lavorare:<br/>
			<form name="form2" method="get" action="gestioneCaseDiscografiche.do">
				<table>
					<tr><td>sel.</td><td>Casa Discografica</td></tr>
					<logic:iterate name="listaCaseDiscografiche" id="casa">
					<tr>
						<td><input type="radio" name="nome" value="${casa.nome}"/></td>
						<td><bean:write name="casa" property="nome"/></td> 
						<td><bean:write name="casa" property="sede"/></td>
					</tr>
					</logic:iterate>	
				</table>
				<html:hidden property="operazione" value="dettaglioCasa"/>
				<input type="submit" name="vai" value="Vai"/>
			</form><br/><br/>			
		</div>
		
	</body>
</html>