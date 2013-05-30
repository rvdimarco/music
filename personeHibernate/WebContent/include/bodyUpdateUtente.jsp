<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<c:if test="${not empty messaggio}">
		<div class="alert alert-error">
			${messaggio}
		</div>
	</c:if>
	<html:form action="gestioneBiblioteca.do">
	<html:hidden property="method" value="updateUtente"/>
	<html:hidden property="idUtente"/>
	<fieldset>
	<legend>Modificare i dati:</legend>
	<table>
		<tr>
			<td align="left"><b>Nome</b></td>
			<td><html:text property="nome"></html:text></td>
		</tr>
		<tr>
			<td align="left"><b>Cognome</b></td>
			<td><html:text property="cognome"></html:text></td>
		</tr>
		<tr>
			<td align="left"><b>Username</b></td>
			<td><html:text property="username"></html:text></td>
		</tr>
		<tr>
			<td align="left"><b>Password</b></td>
			<td><html:text property="password"></html:text></td>
		</tr>
		<tr>
			<td align="left"><b>Prendi in prestito</b></td>
			<td>
				<html:select name ="bibliotecaForm" property="idLibro">
					<html:option value=""></html:option>
					<html:optionsCollection name="libriDisponibili" label="titolo" value="idLibro"/>
				</html:select>
			</td>
		</tr>
	</table>
	<table class="table-striped">
		<tr>
			<td align="center" colspan="3"><h4>Restituisci</h4></td>
		</tr>	
		<tr>
			<td><b>sel.</b></td><td><b>Titolo</b></td><td><b>num. pagine</b></td>
		</tr>	
		<c:forEach var="libro" items="${bibliotecaForm.libros}">
		<tr>
			<td>
				<html:multibox property="libriSelezionati">
					${libro.idLibro}
				</html:multibox>
			</td>
			<td>${libro.titolo}</td>
			<td align="right">${libro.pagine}</td>
		</tr>
		</c:forEach>	
	</table>
	</fieldset><br/><br/>
	<html:submit styleClass="btn btn-primary">Esegui</html:submit>
	<html:link styleClass="btn btn-primary" href="gestioneBiblioteca.do?method=listUtenti">Indietro</html:link>
	</html:form>
</div>
