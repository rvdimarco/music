<%@page import="it.geek.musica.model.Utente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%Utente ut = (Utente)request.getAttribute("utente"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
HOME&nbsp;-&nbsp;Benvenuto&nbsp;<%=ut.getNome()%>&nbsp;<%=ut.getCognome()%><br/>
	<form method="get" action="listaCaseDiscografiche">
		<input type="submit" name="vai" value="Vai alle case discografiche"/>
	</form>
</body>
</html>