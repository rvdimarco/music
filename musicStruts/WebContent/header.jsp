<%@page import="it.geek.ms.model.Utente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%Utente u = (Utente)session.getAttribute("utente"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Header</title>
</head>
<body><center>
Sessione&nbsp;di&nbsp;lavoro&nbsp;di&nbsp;${utente.nome}&nbsp;${utente.cognome}&nbsp;(ruolo:&nbsp;${utente.ruolo.descrizione})
<a href="logout.do">logout&nbsp;(non implementata)</a>
</center></body>
</html>