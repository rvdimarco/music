<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<c:if test="${not empty messaggio}">
		<div class="alert alert-error">
			${messaggio}
		</div>
	</c:if>
	<html:form action="gestioneBiblioteca.do">
	<html:hidden property="method" value="insertLibro"/>
	<fieldset>
	<legend>Inserire i dati:</legend>
	<table>
		<tr>
			<td align="left"><b>Titolo</b></td>
			<td><html:text property="titolo"></html:text></td>
		</tr>
		<tr>
			<td align="left"><b>Pagine</b></td>
			<td><html:text property="pagine"></html:text></td>
		</tr>
	</table>
	</fieldset>
	<html:submit styleClass="btn btn-primary">Esegui</html:submit>
	</html:form>
</div>
