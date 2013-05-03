<%@page import="it.geek.prenotazioni.model.Studente"%>
<%@page import="java.util.List"%>
<%@ page import="it.geek.prenotazioni.model.Corso"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% List<Corso> corsi = (List<Corso>)request.getAttribute("corsi");%>
<% Studente studente = (Studente)session.getAttribute("studente"); %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Prenota Corsi</title>
	</head>
	<body>
	${studente eq null ? "Attenzione: violazione di sicurezza!<br/><br/>..." : ""}
	<%if(session.getAttribute("studente")!=null){%>
		${studente.nome}&nbsp;${studente.cognome},
		&nbsp;ecco&nbsp;i&nbsp;corsi&nbsp;da&nbsp;te&nbsp;prenotabili:
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="logout">logout</a><br/><br/>
		<table>
			<tr>
				<td><b>Codice&nbsp;Corso</b></td>
				<td><b>Materia</b></td>
				<td><b>azione</b></td>
			</tr>
			<%for(Corso corso : corsi){
				if(!studente.getPrenotazioni().contains(corso)){%>
						<tr>
							<td><%=corso.getId()%></td
							><td><%=corso.getMateria()%></td>
							<td><a href="prenotaCorso?id=<%=corso.getId()%>">prenota ora</a></td>
						</tr>
				<%}%>	
			<%}%>
		</table>
		<form name="indietro" action="home">
			<input type="hidden"name="matricola" value="${studente.matricola}"/>
			<input type="submit" value="Home"/>
		</form>
		<%} %>
	</body>
</html>