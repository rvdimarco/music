<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<c:if test="${not empty messaggio}">
		<div class="alert alert-error">
			${messaggio}
		</div>
	</c:if>
	<html:form action="gestioneBiblioteca.do">
	<html:hidden property="method" value="listUtenti"/>
	<fieldset>
	<legend>Dati Utente:</legend>
	<table>
		<tr>
			<td align="left"><b>Codice</b></td>
			<td><html:text property="idUtente" readonly="true"></html:text></td>
		</tr>	
		<tr>
			<td align="left"><b>Nome</b></td>
			<td><html:text property="nome" readonly="true"></html:text></td>
		</tr>
		<tr>
			<td align="left"><b>Cognome</b></td>
			<td><html:text property="cognome" readonly="true"></html:text></td>
		</tr>
		<tr>
			<td align="left"><b>Username</b></td>
			<td><html:text property="username" readonly="true"></html:text></td>
		</tr>
		<tr>
			<td align="left"><b>Password</b></td>
			<td><html:text property="password" readonly="true"></html:text></td>
		</tr>
	</table>
	</fieldset>
	<c:if test="${not empty bibliotecaForm.libros}">
		<br/><br/>
		<b>Libri presi in prestito:</b>
		<br/><br/>
		<table class="table-striped">
			
			<tr>
				<td align="center"><b>Titolo</b></td>
				<td align="center"><b>Pagine</b></td>
			</tr>
			
			<c:forEach var="libro" items="${bibliotecaForm.libros}">
			<tr>
				<td>${libro.titolo}</td>
				<td>${libro.pagine}</td>
			</tr>
			</c:forEach>
		
		</table>
	</c:if>
	<br/>
	<html:submit styleClass="btn btn-primary">Indietro</html:submit>
	</html:form>
</div>
