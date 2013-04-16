<%@page import="java.util.Iterator"%>
<%@page import="it.geek.musica.model.CasaDiscografica"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%List cadilist = (List)request.getAttribute("caseDiscografiche"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista Case Disc.</title>
</head>
<body>
Lista Case Discografiche:<br/><br/>
	<table>
		<tr>
			<td><b/>Nome</b></td><td><b>Sede</b></td>
		</tr>
		
	<%if(cadilist!=null){
		Iterator<CasaDiscografica> it = cadilist.iterator();
		CasaDiscografica cadi = null;
		while(it.hasNext()){
			cadi = it.next();
		%>
			<tr>
				<td><%=cadi.getNome()%></td><td><%=cadi.getSede()%></td>
				<td><a href="test.jsp?primo=visualizzando&secondo=<%=cadi.getNome()%>">Visualizza</a></td>
				<td><a href="test.jsp?primo=modificando&secondo=<%=cadi.getNome()%>">Modifica</a></td>
				<td><a href="test?primo=eliminando&secondo=<%=cadi.getNome()%>">Elimina</a></td>
			</tr>
		<%}%>
	<%}%>		
		
	</table>
	
</body>
</html>