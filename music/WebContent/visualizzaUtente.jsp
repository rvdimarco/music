<%@page import="it.geek.musica.util.Constants"%>
<%@page import="it.geek.musica.model.Autore"%>
<%@page import="java.util.Iterator"%>
<%@page import="it.geek.musica.model.Utente"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%Utente utente = (Utente)request.getAttribute("utente"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dettaglio Utente</title>
</head>
<body>
<jsp:include page="header.jsp"/><br/><br/>
<b>Dettaglio Utente:</b><br/><br/>
<form name="visForm" method="GET" action="gestioneUtenti">
	<table>
		<tr><td>Username:</td><td><input type="text" readonly="readonly" value=" <%=utente.getUsername() %>"/><br/></td></tr>
		<tr><td>Cognome:</td><td><input type="text" readonly="readonly" value=" <%=utente.getCognome() %>"/><br/></td></tr>
		<tr><td>Nome:</td><td><input type="text" readonly="readonly" value=" <%=utente.getNome() %>"/><br/></td></tr>
		<tr><td>Profilo:</td><td><select disabled="disabled"><option><%=utente.getRuolo().getDescrizione() %></option></select><br/></td></tr>
	</table><br/>
	<input type="hidden" name="<%=Constants.CODOP_LABEL%>" value="<%=Constants.CODOP_LISTA_VALUE%>"/>
	<input type="submit" name="tornaButton" value="indietro" />
</form>
</body>
</html>