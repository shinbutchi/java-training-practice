<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<h1><spring:message code="error.403"/></h1>
<c:choose>
    <c:when test="${empty username}">
        <h2><spring:message code="not.have.permission"/></h2>
    </c:when>
    <c:otherwise>
        <h2><spring:message code="not.have.permission"/></h2>
    </c:otherwise>
</c:choose>