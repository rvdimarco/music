<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-faces" prefix="faces" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<center>
Sessione&nbsp;di&nbsp;lavoro&nbsp;di&nbsp;${utenteSession.nome}&nbsp;${utenteSession.cognome}&nbsp;(ruolo:&nbsp;${utenteSession.ruolo.descrizione})
&nbsp;&nbsp;<a href="logout.do">logout</a>
&nbsp;&nbsp;<a href="home.jsp">home</a>
</center>