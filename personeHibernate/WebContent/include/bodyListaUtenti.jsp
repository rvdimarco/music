<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<c:if test="${not empty messaggio}">
		<div class="alert alert-info">
			${messaggio}
		</div>
	</c:if>
	
	<html:form action="gestioneBiblioteca.do">
		<html:hidden property="method" value="searchUtente"/>
			<fieldset>
			<table>
				<tr>
					<td align="left"><b>Nome</b></td>
					<td><html:text property="nome" value=""></html:text></td>
				
					<td align="left"><b>Cognome</b></td>
					<td><html:text property="cognome" value=""></html:text></td>
					
					<td align="left"><b>Username</b></td>
					<td><html:text property="username" value=""></html:text></td>
					
					<td><html:submit styleClass="btn btn-primary">filtra</html:submit></td>
				</tr>
			</table>
			</fieldset>
	</html:form>
	
	<table class="table-striped">
		<tr>
			<td colspan="4" align="left">
				<html:link styleClass="btn btn-primary" href="gestioneBiblioteca.do?method=showInsertUtente">Nuovo</html:link>
			</td>
		</tr>
		<tr
			><td colspan="4"><h3>Anagrafica Utenti</h3></td>
		</tr>
		<tr>
			<td align="center"><b>Nome</b></td>
			<td align="center"><b>Cognome</b></td>
			<td align="center"><b>Username</b></td>
			<td align="center"><b>azione...</b></td>
		</tr>
		<c:forEach var="utente" items="${ulist}">
		<tr>
			<td>${utente.nome}</td>
			<td>${utente.cognome}</td>
			<td>${utente.username}</td>
			<td>
				<div class="btn-group">
					<html:link styleClass="btn" href="gestioneBiblioteca.do?method=showUtente&idUtente=${utente.idUtente}">Visualizza</html:link>
					<html:link styleClass="btn" href="gestioneBiblioteca.do?method=showUpdateUtente&idUtente=${utente.idUtente}">Modifica</html:link>
					<html:link styleClass="btn" href="gestioneBiblioteca.do?method=deleteUtente&idUtente=${utente.idUtente}">Elimina</html:link>	
				</div>
			</td>
		</tr>
		</c:forEach>
	</table>
</div>
