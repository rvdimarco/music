<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<c:if test="${not empty messaggio}">
		<div class="alert alert-error">
			${messaggio}
		</div>
	</c:if>
	<html:form action="gestioneBiblioteca.do">
	<html:hidden property="method" value="listLibri"/>
	<fieldset>
	<legend>Dati Libro:</legend>
	<table>
		<tr>
			<td align="left"><b>Codice</b></td>
			<td><html:text property="idLibro" readonly="true"></html:text></td>
		</tr>	
		<tr>
			<td align="left"><b>Titolo</b></td>
			<td><html:text property="titolo" readonly="true"></html:text></td>
		</tr>
		<tr>
			<td align="left"><b>Pagine</b></td>
			<td><html:text property="pagine" readonly="true"></html:text></td>
		</tr>
		<tr>
			<td align="left"><b>disponibile</b></td>
			<td>${not empty bibliotecaForm.utente ? 'NO' : 'SI'}</td>
		</tr>
		<c:if test="${not empty bibliotecaForm.utente}">
			<tr>
				<td align="left"><b>in prestito a</b></td>
				<td>${bibliotecaForm.utente.cognome}&nbsp;${bibliotecaForm.utente.nome}</td>
			</tr>	
		</c:if>
	</table>
	</fieldset>
	<html:submit styleClass="btn btn-primary">Indietro</html:submit>
	</html:form>
</div>
