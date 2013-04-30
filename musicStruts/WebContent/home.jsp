<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		benvenuto ${loginForm.username}<br/>
		<html:errors/>
		<html:form styleId="homeForm" action="visualizza.do">
			<html:text property="parola" />
			<html:submit value="vai"/>
		</html:form>
		
	</body>
</html>