<%@ page isELIgnored="false"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<center>
	<table border="1">
		<tr><td colspan="5"><h3>Statistiche</h3></td></tr>
		<tr>
			<td><b>Regione</b></td><td><b>Abitanti</b></td><td><b>Residenti</b></td>
		</tr>
		<c:forEach var="info" items="${infos}">
		<tr>
			<td>${info.regione}</td>
			<td>${info.numAbitanti}</td>
			<td>${info.numResidenti}</td>
		</tr>
		</c:forEach>
	</table>
</center>