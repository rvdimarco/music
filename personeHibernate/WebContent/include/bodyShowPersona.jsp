<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<c:if test="${not empty messaggio}">
		<div class="alert alert-error">
			${messaggio}
		</div>
	</c:if>
	<html:form action="gestionePersona.do">
	<html:hidden property="method" value="list"/>
	<fieldset>
	<legend>Dati Persona:</legend>
	<table>
		<tr>
			<td align="left"><b>Codice</b></td>
			<td><html:text property="idPersona" readonly="true"></html:text></td>
		</tr>	
		<tr>
			<td align="left"><b>Nome</b></td>
			<td><html:text property="nome" readonly="true"></html:text></td>
		</tr>
		<tr>
			<td align="left"><b>e-mail</b></td>
			<td><html:text property="email" readonly="true"></html:text></td>
		</tr>
	</table>
	</fieldset>
	<html:submit styleClass="btn btn-primary">Indietro</html:submit>
	</html:form>
</div>
