<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">



<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Home</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body><center>
  	
  	
  	<table width="100%" height="100%" border="2">
  	
  	<tr>
  	<td colspan="2" height="20%"><jsp:include flush="true" page="include/header.jsp" /></td></tr>
  	<tr>
  	<td width="15%"><jsp:include flush="true" page="include/menu.jsp" /></td>
  	<td width="85%"><jsp:include flush="true" page="include/bodyHome.jsp" /></td></tr>
  	
  	</table>      	
    
    
  </center></body>
</html>
