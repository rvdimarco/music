<%@page import="it.geek.musica.util.Constants"%>
<%@page import="it.geek.musica.model.Autore"%>
<%@page import="java.util.Iterator"%>
<%@page import="it.geek.musica.model.Ruolo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%List<Ruolo> ruoli = (List<Ruolo>)request.getAttribute("ruoli"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inserisci Utente</title>
<script type="text/javascript">
	function verifica(){
		
		if(document.insForm.username.value==""){
			alert("username obbligatorio!");
			document.insForm.username.focus();
			return false;
		}
		else if(document.insForm.cognome.value==""){
			alert("cognome obbligatorio!");
			document.insForm.cognome.focus();
			return false;
		}
		else if(document.insForm.nome.value==""){
			alert("nome obbligatorio!");
			document.insForm.nome.focus();
			return false;
		}
		else if(document.insForm.ruolo.value==""){
			alert("ruolo obbligatorio!");
			document.insForm.ruolo.focus();
			return false;
		}
		
		if(confirm("inserire?")){
			document.insForm.submit();
		}else{
			return false;
		}
		
		
	}
</script>
</head>
<body>
<jsp:include page="header.jsp"/><br/><br/>
<b>Inserisci Utente:</b><br/><br/>
<form name="insForm" method="GET" action="gestioneUtenti">
	<table>
		<tr><td>Username*:</td><td><input type="text" name="username" value=""/></td></tr>
		<tr><td>Password:</td><td>(come username, modificare al primo accesso)</td></tr>
		<tr><td>Cognome*:</td><td><input type="text" name="cognome" value=""/></td></tr>
		<tr><td>Nome*:</td><td><input type="text" name="nome" value=""/></td></tr>
		<tr><td>Profilo*:</td><td>
								<select name="ruolo">
									<option value=""></option>
									<%if(ruoli!=null){
										Iterator<Ruolo> it = ruoli.iterator();
										Ruolo r = null;
										while(it.hasNext()){
											r = it.next();%>
											<option value="<%=r.getCodice()%>"><%=r.getDescrizione()%></option>
										<%}%>
									<%}%>
								</select>
							</td>
		</tr>
	</table><br/>
	<input type="hidden" name="<%=Constants.CODOP_LABEL%>" value="<%=Constants.CODOP_DO_INS_VALUE%>"/>
	<input type="button" name="inserisciButton" value="inserisci" onclick="verifica();"/>
</form>
</body>
</html>