<%@ include file="taglibInclude.jsp" %>
<div style="color:red">${messaggio}</div><br/><br/><br/>
<div>
<h1><fmt:message key="priceincrease.heading"/></h1>
<form:form method="post" action="increase.htm">
  <table class="table-striped" width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
    <tr>
      <td align="right" width="20%">Aumento (%):</td>
        <td width="20%">
          <form:input path="percentage"/>
        </td>
        <td width="60%">
          <form:errors path="percentage" cssClass="error"/>
        </td>
    </tr>
  </table>
  <br>
  <input type="submit" align="center" value="Esegui">
</form:form>
<a href="<c:url value="home.htm"/>">Home</a>
</div>