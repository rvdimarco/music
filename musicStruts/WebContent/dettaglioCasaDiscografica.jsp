<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dettaglio Casa Discografica</title>

<script type="text/javascript">
	function checkComboAutore(){
		var comboValue = document.forms['insertForm'].autoreInsert.value;
		if(comboValue==0){
			alert("selezionare l'autore da affiliare!");
			document.forms['insertForm'].autoreInsert.focus();
			return false;
		}else{
			document.forms['insertForm'].submit();
		}
	}
</script>

</head>
<body>
<jsp:include page="header.jsp"/><br/><br/>
Dettaglio Casa Discografica:<br/>

	<table>
		<tr>
			<td><b>Nome</b></td>
			<td><b>Sede</b></td>
		</tr>
		<tr>
			<td>${casaDiscografica.nome}</td>
			<td>${casaDiscografica.sede}</td>
		</tr>
	</table><br/><br/>

	<html:form styleId="insertForm">
	Per inserire un nuovo autore&nbsp;
		<html:select property="idAutore" styleId="autoreInsert">
			<html:option value="0">selezione un artista</html:option>
			<html:optionsCollection name="gestCasaDiscograficaForm" property="autoriList"
									label="datiAnagrafici" value="codiceFiscale" />
			</html:select>
	&nbsp;e premi&nbsp;
		<html:hidden property="nome" value="${casaDiscografica.nome}"/>
		<html:hidden property="operazione" value="inserisciAutore"/>
		<html:button property="bottone" onclick="checkComboAutore();">invio</html:button>
	</html:form><br/>
Autori della Casa: 
	<table>
		<tr>
			<td><b>Codice Fiscale</b></td>
			<td><b>Nome</b></td>
			<td><b>Cognome</b></td>
			<td><b>op. 1</b></td>
			<td><b>op. 2</b></td>
			<td><b>op. 3</b></td>
		</tr>
		
		<logic:iterate collection="${casaDiscografica.autori}" id="autore">
		<c:if test="${not empty autore.codiceFiscale}">
		<tr>
			<td><bean:write name="autore" property="codiceFiscale"/></td> 
			<td><bean:write name="autore" property="nome"/></td> 
			<td><bean:write name="autore" property="cognome"/></td>
			<td><a href="gestioneCaseDiscografiche.do?nome=${casaDiscografica.nome}&operazione=visualizzaAutore&idAutore=<bean:write name="autore" property="codiceFiscale"/>">Visualizza(non implementata)</a></td>
			<td><a href="gestioneCaseDiscografiche.do?nome=${casaDiscografica.nome}&operazione=modificaAutore&idAutore=<bean:write name="autore" property="codiceFiscale"/>" >Modifica(non implementata)</a></td>
			<td><a href="gestioneCaseDiscografiche.do?nome=${casaDiscografica.nome}&operazione=eliminaAutore&idAutore=<bean:write name="autore" property="codiceFiscale"/>" >Elimina</a></td>
		</tr>
		</c:if>	
		</logic:iterate>
		
	</table>
	
</body>
</html>