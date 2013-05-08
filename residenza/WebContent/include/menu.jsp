<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<center>
	
	<b><h4>MENU'</h4></b>
	
	<table border=1>

		<%--
		<logic:iterate id="Operazione" name="myuserLogin" property="ruolo.operazioni">
    		<tr>
      		<td><a href="/gestioneRapportini/menu.do?codop=<bean:write name="Operazione" property="descri" /> "> 
      			<bean:write name="Operazione" property="descri" />
      		</a></td></tr>
  		</logic:iterate>
 		--%>
 		<c:if test="${utenteSession.ruolo.descrizione eq 'amministratore'}">
 			<tr><td><a href="gestioneUtente.do?method=list">Gestione Utenti</a></td></tr>
 		</c:if>
 		<c:if test="${utenteSession.ruolo.descrizione eq 'amministratore' or utenteSession.ruolo.descrizione eq 'standard'}">
 			<tr><td><a href="gestioneCittadino.do?method=sarch">Gestione Residenze</a></td></tr>
 		</c:if>
 		<c:if test="${utenteSession.ruolo.descrizione eq 'amministratore' or utenteSession.ruolo.descrizione eq 'ospite'}">
 		<tr><td><a href="gestioneCittadino.do?method=statistiche">Statistiche</a></td></tr>
		</c:if>
	</table>  
	
</center>