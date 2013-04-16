<%@page import="it.geek.musica.model.Autore"%>
<%@page import="java.util.Iterator"%>
<%@page import="it.geek.musica.model.CasaDiscografica"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%CasaDiscografica casa = (CasaDiscografica)request.getAttribute("casa"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dettaglio Casa Disc.</title>
</head>
<body>
Dettaglio Casa Discografica:<br/>

	<table>
		<tr>
			<td><b>Nome</b></td>
			<td><b>Sede</b></td>
		</tr>
		<%if(casa!=null){ %>
		<tr>
			<td><%=casa.getNome()%></td>
			<td><%=casa.getSede()%></td>
		</tr>
		<%} %>
	</table><br/><br/>
Autori della Casa: (per inserire un nuovo autore <a href="gestioneAutore?codop=INS">clicca qui</a>)<br/>
	<table>
		<tr>
			<td><b>Codice Fiscale</b></td>
			<td><b>Nome</b></td>
			<td><b>Cognome</b></td>
			<td><b>op. 1</b></td>
			<td><b>op. 2</b></td>
			<td><b>op. 3</b></td>
		</tr>
		
	<%if(casa!=null && !casa.getAutori().isEmpty()){
		Iterator<Autore> it = casa.getAutori().iterator();
		Autore autore = null;
		while(it.hasNext()){
			autore = it.next();
		%>
			<tr>
				<td><%=autore.getCodiceFiscale()%></td>
				<td><%=autore.getNome()%></td>
				<td><%=autore.getCognome()%></td>
				<td><a href="gestioneAutore?codop=VIS&id=<%=autore.getCodiceFiscale()%>">Visualizza</a></td>
				<td><a href="gestioneAutore?codop=MOD&id=<%=autore.getCodiceFiscale()%>">Modifica</a></td>
				<td><a href="gestioneAutore?codop=ELI&id=<%=autore.getCodiceFiscale()%>">Elimina</a></td>
			</tr>
		<%}%>
	<%}%>		
		
	</table>
	
</body>
</html>