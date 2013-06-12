<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login Page</title>
		
		<script type="text/javascript">
			
			function controllaLunghezzeCampi(){
				var userVal = document.forms['loginForm'].username.value;
				if(userVal.length<3){
					alert("il campo username deve essere di almeno 3 caratteri");
					return false;
				}
				
				var passVal = document.forms['loginForm'].password.value;
				if(passVal.length<3){
					alert("il campo password deve essere di almeno 3 caratteri");
					return false;
				}
				
				document.forms['loginForm'].submit();
			}
			

		</script>
	</head>
	<body><center><br/><br/><div style="color:red">${messaggio}</div><br/><br/>
		<b>Effettuare la login:</b>
		&nbsp;&nbsp;&nbsp;...e&nbsp;se&nbsp;non&nbsp;sei&nbsp;ancora&nbsp;registrato&nbsp;<a href="gestioneUtente.do?method=registrazione">registrati&nbsp;ora!</a><br/><br/>
		<html:errors/>
		<form name="loginForm" method="POST" action="login.do">
			username&nbsp;<input type="text" name="username" maxlength="8"/><br/>
			password&nbsp;<input type="password" name="password" maxlength="8"/><br/>
			<input type="button" name="loginButton" value="Invia" onclick="controllaLunghezzeCampi();"/>
		</form>
	</center></body>
</html>