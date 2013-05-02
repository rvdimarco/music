<%@ page import="it.geek.prenotazioni.model.Studente"%>
<%@ page import="java.util.List"%>
<%@ page import="it.geek.prenotazioni.model.Corso"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Prenota Corsi</title>
	</head>
	<body>
	${studente eq null ? "Attenzione: violazione di sicurezza!<br/><br/>..." : ""}
	<c:if test="${studente ne null}">
		${studente.nome}&nbsp;${studente.cognome},
		&nbsp;ecco&nbsp;i&nbsp;corsi&nbsp;da&nbsp;te&nbsp;prenotabili (JSTL):<br/><br/>
		<table>
			<tr>
				<td><b>Codice&nbsp;Corso</b></td>
				<td><b>Materia</b></td>
				<td><b>azione</b></td>
			</tr>
			<c:forEach var="corso" items="${corsi}">
			
						<tr>
							<td>${corso.id}</td>
							<td>${corso.materia}</td>
							<td><a href="prenotaCorso?id=${corso.id}">prenota ora</a></td>
						</tr>					
			
			</c:forEach>
		</table>
		<form name="indietro" action="home">
			<input type="hidden"name="matricola" value="${studente.matricola}"/>
			<input type="submit" value="Home"/>
		</form>	
	</c:if>
	</body>
</html>