<%@ page import="it.geek.prenotazioni.model.Corso"%>
<%@ page import="it.geek.prenotazioni.model.Studente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% Studente studente = (Studente)session.getAttribute("studente");%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Visualizza Prenotazioni</title>
	</head>
	<body>
	${studente eq null ? "Attenzione: violazione di sicurezza!<br/><br/>..." : ""}
	<%if(session.getAttribute("studente")!=null){%>
		${studente.nome}&nbsp;${studente.cognome},
		&nbsp;ecco&nbsp;le&nbsp;prenotazioni&nbsp;da&nbsp;te&nbsp;effettuate:<br/><br/>
		<form name="delete" action="eliminaPrenotazioniCorsi">
		<table>
			<tr>
				<td>sel.</td>
				<td><b>Codice&nbsp;Corso</b></td>
				<td><b>Materia</b></td>
			</tr>		
			<%  int i = 0;
				for(Corso corso : studente.getPrenotazioni()){%>
					<tr>
						<td><input type="checkbox" name="corsiDaEliminare<%=++i%>" value="<%=corso.getId()%>"/></td>
						<td><%=corso.getId()%></td>
						<td><%=corso.getMateria()%></td>
					</tr>		
			<%	}%>
		</table>
		<input type="submit" value="elimina i corsi selezionati"/>
		</form>
		<form name="indietro" action="home">
			<input type="hidden"name="matricola" value="${studente.matricola}"/>
			<input type="submit" value="Home"/>
		</form>
		<%} %>
	</body>
</html>