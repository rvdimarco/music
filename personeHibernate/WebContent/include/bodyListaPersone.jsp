<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<c:if test="${not empty messaggio}">
		<div class="alert alert-info">
			${messaggio}
		</div>
	</c:if>
	<table class="table-striped">
		<tr>
			<td colspan="3" align="left">
				<html:link styleClass="btn btn-primary" href="gestionePersona.do?method=showInsert">Nuovo</html:link>
			</td>
		</tr>
		<tr
			><td colspan="3"><h3>Anagrafiche</h3></td>
		</tr>
		<tr>
			<td align="center"><b>Cod.</b></td><td align="center"><b>Nome</b></td><td align="center"><b>azione...</b></td>
		</tr>
		<c:forEach var="persona" items="${plist}">
		<tr>
			<td>${persona.idPersona}</td>
			<td>${persona.nome}</td>
			<td>
				<div class="btn-group">
					<html:link styleClass="btn" href="gestionePersona.do?method=show&idPersona=${persona.idPersona}">Visualizza</html:link>
					<html:link styleClass="btn" href="gestionePersona.do?method=showUpdate&idPersona=${persona.idPersona}">Modifica</html:link>
					<html:link styleClass="btn" href="gestionePersona.do?method=delete&idPersona=${persona.idPersona}">Elimina</html:link>	
				</div>
			</td>
		</tr>
		</c:forEach>
	</table>
</div>
