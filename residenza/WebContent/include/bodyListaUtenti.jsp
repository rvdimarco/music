<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<center>
	<html:form action="gestioneUtente.do">
	<html:hidden property="method" value="modifica"></html:hidden>
	<table border="1">
		<tr><td colspan="5"><h3>Statistiche</h3></td></tr>
		<tr><td><b>sel.</b></td>
			<td><b>Username</b></td><td><b>Nome</b></td><td><b>Cognome</b></td>
			<td><b>DataNascita</b></td><td><b>Ruolo</b></td><td><b>DataRegistrazione</b></td>
		</tr>
		<c:forEach var="utente" items="${ulist}">
		<tr>
			<td>
				<html:multibox property="utentiSelezionati">
					${utente.username}
				</html:multibox>
			</td>
			<td>${utente.nome}</td>
			<td>${utente.cognome}</td>
			<td>${utente.dataNascita}</td>
			<td>${utente.ruolo}</td>
			<td>${utente.dataRegistrazione}</td>
		</tr>
		</c:forEach>
	</table>
	</html:form>
</center>
