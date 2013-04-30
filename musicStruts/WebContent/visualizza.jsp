<%@page import="it.geek.ms.form.HomeForm"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
${homeForm.parola}&nbsp;=&nbsp;
<%=((HomeForm)request.getAttribute("homeForm")).getParola()%><br/><br/>

<h2>Struts tag &lt;logic:iterate&gt; esempio1: Lista di Stringhe</h2>
 
<logic:iterate name="listaDiStringhe" id="stringa">
<p>
	stringa -  <bean:write name="stringa"/>
</p>
</logic:iterate><br/><br/>

<h2>Struts tag &lt;logic:iterate&gt; esempio2: Lista di Autori</h2>
 
<logic:iterate name="listaDiAutori" id="autore">
<p>
	autore - <bean:write name="autore" property="nome"/> , 
					<bean:write name="autore" property="cognome"/>
</p>
</logic:iterate>

</body>
</html>