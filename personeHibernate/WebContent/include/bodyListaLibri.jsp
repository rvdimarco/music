<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<c:if test="${not empty messaggio}">
		<div class="alert alert-info">
			${messaggio}
		</div>
	</c:if>
	
	<html:form action="gestioneBiblioteca.do">
		<html:hidden property="method" value="searchLibro"/>
			<fieldset>
			<table>
				<tr>
					<td align="left"><b>Titolo</b></td>
					<td><html:text property="titolo" value=""></html:text></td>
				
					<td align="left"><b>Pagine max</b></td>
					<td><html:text property="pagine" value=""></html:text></td>
					
					<td><html:submit styleClass="btn btn-primary">filtra</html:submit></td>
				</tr>
			</table>
			</fieldset>
	</html:form>
	
	<table class="table-striped">
		<tr>
			<td colspan="4" align="left">
				<html:link styleClass="btn btn-primary" href="gestioneBiblioteca.do?method=showInsertLibro">Nuovo</html:link>
			</td>
		</tr>
		<tr
			><td colspan="4"><h3>Anagrafica Libri</h3></td>
		</tr>
		<tr>
			<td align="center"><b>Titolo</b></td>
			<td align="center"><b>Pagine</b></td>
			<td align="center"><b>disponibile</b></td>
			<td align="center"><b>azione...</b></td>
		</tr>
		<c:forEach var="libro" items="${llist}">
		<tr>
			<td>${libro.titolo}</td>
			<td>${libro.pagine}</td>
			<td>${not empty libro.utente ? 'NO' : 'SI'}</td>
			<td>
				<div class="btn-group">
					<html:link styleClass="btn" href="gestioneBiblioteca.do?method=showLibro&idLibro=${libro.idLibro}">Visualizza</html:link>
					<html:link styleClass="btn" href="gestioneBiblioteca.do?method=showUpdateLibro&idLibro=${libro.idLibro}">Modifica</html:link>
					<html:link styleClass="btn" href="gestioneBiblioteca.do?method=deleteLibro&idLibro=${libro.idLibro}">Elimina</html:link>	
				</div>
			</td>
		</tr>
		</c:forEach>
	</table>
</div>
