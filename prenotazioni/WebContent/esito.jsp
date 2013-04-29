<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
${studente eq null ? "Attenzione: violazione di sicurezza!<br/><br/>..." : ""}
<%if(session.getAttribute("studente")!=null){%>
Prenotazione effettuata correttamente!
		<a href="visualizzaCorsiPrenotabili">Torna alla lista corsi</a>
		<a href="logout">logout</a>
<%} %>
</body>
</html>