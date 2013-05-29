<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
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

	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>

  </head>
  
  <body>
	<jsp:include flush="true" page="include/header.jsp" />
	<br/><br/><br/>
			
	<div class="container-fluid">
	
	    <div class="row-fluid">
	    	<div class="span3">
	  			<jsp:include flush="true" page="include/menu.jsp" />
	  		</div><!--/span3-->
	  			
	  		<div class="span9" align="center">
	  			<jsp:include flush="true" page="include/bodyInsertUtente.jsp" />
	  		</div><!--/span9-->
	  	</div><!--/row-fluid-->
	  	
	  	<div class="row-fluid">
	  		<div class="span12">
	  			<jsp:include flush="true" page="include/header.jsp" />
	  		</div><!--/span12-->
	  	</div><!--/row-fluid-->
	  	
	  	<hr/>
	  	
  		<footer align="center">
     		<p>&copy; Geek 2013</p>
   		</footer>
    
  </div><!--/container-fluid-->
  </body>
</html>