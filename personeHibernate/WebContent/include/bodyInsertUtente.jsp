<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<c:if test="${not empty messaggio}">
		<div class="alert alert-error">
			${messaggio}
		</div>
	</c:if>
	<html:form action="gestioneBiblioteca.do">
	<html:hidden property="method" value="insertUtente"/>
	<fieldset>
	<legend>Inserire i dati:</legend>
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
			<td align="left"><b>Libro scelto</b></td>
			<td>
				<html:select name ="bibliotecaForm" property="idLibro">
					<html:optionsCollection name="libriDisponibili" label="titolo" value="idLibro"/>
				</html:select>
			</td>
		</tr>
		
	</table>
	</fieldset>
	<html:submit styleClass="btn btn-primary">Esegui</html:submit>
	</html:form>
</div>
