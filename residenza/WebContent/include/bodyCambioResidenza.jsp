<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<center>
	
	<html:form styleId="residenzaForm" action="gestioneCittadino.do">
	<html:hidden property="method" value="residenza"/>
	<table>
	
		<tr>
			<td clospan="2"><center><h3>Scegli la nuova residenza...</h3></td>
		</tr>
		<tr>
			<td>Regione:</td>
			<td>					
				<html:select name ="cittadinoForm" property="codRegione" onchange="document.forms['residenzaForm'].submit();">
					<html:optionsCollection name="cittadinoForm" property="listaRegioni"/>
				</html:select>
			</td>
		</tr>
		<c:if test="${not empty cittadinoForm.codRegione}">
		<tr>
			<td>Provincia:</td>
			<td>
				<html:select name ="cittadinoForm" property="codProvincia" onchange="document.forms['residenzaForm'].submit();">
					<html:optionsCollection name="cittadinoForm" property="listaProvince"/>
				</html:select>			
			</td>
		</tr>
		</c:if>
		<c:if test="${not empty cittadinoForm.codProvincia}">
		<tr>
			<td>Citta:</td>
			<td>
				<html:select name ="cittadinoForm" property="codCitta" onchange="document.forms['residenzaForm'].submit();">
					<html:optionsCollection name="cittadinoForm" property="listaCitta"/>
				</html:select>			
			</td>
		</tr>
		</c:if>	
	</table>
	</html:form>
	<html:form styleId="updateForm" action="gestioneCittadino.do">
	<html:hidden property="method" value="update"/>
	<html:hidden property="codCitta"/>
	<table border="1">
		<tr><td colspan="5"><h3>...per il cittadino che ha richiesto il servizio</h3></td></tr>
		<tr>
			<td><b>sel.</b></td><td><b>Codice Fiscale</b></td><td><b>Nominativo</b></td><td><b>Impiego</b></td><td><b>Residenza<b></td>
		</tr>
		<c:forEach var="cittadino" items="${cittadini}">
		<tr>
			<td><html:radio property="codFis" value="cittadino.codiceFiscale" onclick="document.forms['residenzaForm'].method.value=update;"></html:radio></td>
			<td>${cittadino.codiceFiscale}</td>
			<td>${cittadino.nominativo}</td>
			<td>${cittadino.impiego}</td>
			<td>${cittadino.residenza}</td>
		</tr>
		</c:forEach>
	</table>
	${test}
	<html:submit value="invia" onclick="alert(document.forms['updateForm'].method.value);"></html:submit>
	</html:form>
</center>