<%@ include file="taglibInclude.jsp" %>
<div style="color:red">${messaggio}</div><br/><br/><br/>
<div>
	<p><b><fmt:message key="benvenuto"/><c:out value="${model.now}"/></b></p><br/><br/>
	<p><b><fmt:message key="info"/></b></p><br/><br/>
	<p><b><fmt:message key="prodottiDisponibili"/></b></p><br/>
	<c:forEach items="${model.products}" var="prod">
      <c:out value="${prod.description}"/> <i>$<c:out value="${prod.price}"/></i><br><br>
    </c:forEach>
    <br><br>
    	<a href="<c:url value="priceincrease.htm"/>">Aumenta i prezzi...</a>
</div>