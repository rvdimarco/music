<%@page import="it.geek.musica.model.Utente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%Utente u = (Utente)session.getAttribute("utente"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Header</title>
</head>
<body>
Salve&nbsp;<%=u.getNome()%>&nbsp;<%=u.getCognome()%>&nbsp;(<%=u.getRuolo().getDescrizione()%>)
<a href="logout">logout</a>
</body>
</html>