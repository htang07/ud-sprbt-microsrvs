<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
An exception occurred! Please contact Support!
<%-- <c:if test="${empty stacktrace}"> --%>
<c:if test="${not empty http_status}">
    <p hidden="true">${http_status}</p>
</c:if>
<c:if test="${not empty error_message}">
    <p hidden="true">${error_message}</p>
</c:if>
<c:if test="${not empty url_to_exception}">
    <p hidden="true">${url_to_exception}</p>
</c:if>
<c:if test="${not empty stacktrace}">
    <p hidden="true">${stacktrace}</p>
</c:if>

</div>
<%@ include file="common/footer.jspf"%>