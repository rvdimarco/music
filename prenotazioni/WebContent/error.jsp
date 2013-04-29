<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error Page</title>
</head>
<body>
${studente eq null ? "Attenzione: violazione di sicurezza!<br/><br/>..." : ""}
<%if(session.getAttribute("studente")!=null){%>
${messaggio}&nbsp;${pageContext.exception}
		<form name="indietro" action="ingresso.html">
			<input type="submit" value="Torna alla pagina di ingresso"/>
		</form>
<%}%>
</body>
</html>