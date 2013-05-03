<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
</head>
<body>
${studente eq null ? "Attenzione: violazione di sicurezza!<br/><br/>..." : ""}
<%if(session.getAttribute("studente")!=null){%>
Benvenuto&nbsp;${studente.nome}<br/><br/>
Scegli&nbsp;una&nbsp;fra&nbsp;le&nbsp;funzioni&nbsp;offerte:<br/>
<a href="visualizzaPrenotazioni">Visualizza le tue prenotazioni</a>&nbsp;&nbsp;&nbsp;
<a href="visualizzaCorsiPrenotabili">Prenota un corso</a>&nbsp;&nbsp;&nbsp;
<a href="visualizzaCorsiPrenotabili?isJstlTest=true">Prenota un corso (JSTL)</a>&nbsp;&nbsp;&nbsp;
<a href="logout">logout</a>
<%} %>
</body>
</html>