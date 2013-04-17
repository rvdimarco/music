<%@page import="it.geek.musica.util.HtmlUtil"%>
<%@page import="java.util.Iterator"%>
<%@page import="it.geek.musica.model.CasaDiscografica"%>
<%@page import="java.util.List"%>
<%@page import="it.geek.musica.model.Utente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%Utente ut = (Utente)session.getAttribute("utente"); 
  List<CasaDiscografica> list = (List<CasaDiscografica>)request.getAttribute("caseDiscografiche");%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
HOME&nbsp;-&nbsp;Benvenuto&nbsp;<%=ut.getNome()%>&nbsp;<%=ut.getCognome()%><br/><br/>
Visualizza la lista di tutte le case discografiche:<br/>
	<form method="get" action="listaCaseDiscografiche">
		<input type="submit" name="vai" value="Vai"/>
	</form><br/><br/>
	<% if(list!=null){%>
Oppure scegline una delle seguenti:<br/>
	<form name="form1" method="get" action="visualizzaCasaDiscografica">
		<select name="id">
			<% Iterator<CasaDiscografica> it = list.iterator();
			   CasaDiscografica ca = null;
				while(it.hasNext()){
					ca = it.next();%>
				<option value="<%=ca.getNome()%>"><%=ca.getNome()%></option>
			<% }%>
		</select>
		<input type="submit" name="esegui1" value="Esegui"/>
	</form><br/><br/>
	
(Come sopra, ma la funzione questa combo ha una diversa implementazione):<br/>
	<form name="form2" method="get" action="visualizzaCasaDiscografica">
	<%= HtmlUtil.writeComboBoxCaseDiscografiche(list) %>
		<input type="submit" name="esegui2" value="Esegui"/>
	</form><br/><br/>	
	<%} %>
</body>
</html>