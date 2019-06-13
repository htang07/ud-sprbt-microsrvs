<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
An exception occurred! Please contact Support!
<%-- <c:if test="${empty stacktrace}"> --%>
	<p hidden="true"> ${stacktrace}</p>
	<p hidden="true"></p>
<%-- </c:if> --%>
</div>
<%@ include file="common/footer.jspf"%>