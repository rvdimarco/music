<%@page import="it.geek.musica.util.Constants"%>
<%@page import="java.util.Iterator"%>
<%@page import="it.geek.musica.model.Utente"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%List ulist = (List)request.getAttribute("utenti"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista Utenti</title>
</head>
<body>
<jsp:include page="header.jsp"/><br/><br/>
Utenti: (per inserire un nuovo autore <a href="gestioneUtenti?codop=INS">clicca qui</a>)<br/>
	<table>
		<tr>
			<td><b>Nome</b></td>
			<td><b>Cognome</b></td>
			<td><b>Username</b></td>
			<td><b>Ruolo</b></td>
			<td><b>op. 1</b></td>
			<td><b>op. 2</b></td>
			<td><b>op. 3</b></td>
		</tr>
		
	<%if(ulist!=null){
		Iterator<Utente> it = ulist.iterator();
		Utente u = null;
		while(it.hasNext()){
			u = it.next();
		%>
			<tr>
				<td><%=u.getNome()%></td>
				<td><%=u.getCognome()%></td>
				<td><%=u.getUsername()%></td>
				<td><%=u.getRuolo().getDescrizione()%></td>
				<td><a href="gestioneUtenti?<%=Constants.CODOP_LABEL%>=<%=Constants.CODOP_VIS_VALUE%>&id=<%=u.getUsername()%>">Visualizza</a></td>
				<td><a href="gestioneUtenti?<%=Constants.CODOP_LABEL%>=<%=Constants.CODOP_MOD_VALUE%>&id=<%=u.getUsername()%>">Modifica</a></td>
				<td><a href="gestioneUtenti?<%=Constants.CODOP_LABEL%>=<%=Constants.CODOP_ELI_VALUE%>&id=<%=u.getUsername()%>">Elimina</a></td>
			</tr>
		<%}%>
	<%}%>		
		
	</table>
</body>
</html>