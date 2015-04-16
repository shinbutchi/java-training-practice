<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div>This is Header</div>
<c:if test="${pageContext.request.userPrincipal.name == null}">
    <a href="/login">Login</a>
    <a href="/signup">Sign Up</a>
</c:if>

<%--<div ng-show="${pageContext.request.userPrincipal.name == null}">--%>
    <%--<a href="/login">Login</a>--%>
    <%--<a href="/signup">Sign Up</a>--%>
<%--</div>--%>

<sec:authorize access="hasRole('ROLE_USER')">
    <!-- For login user -->
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <div>
            Hi : ${pageContext.request.userPrincipal.name} |
            <a href="/logout">Logout</a>
        </div>
    </c:if>
</sec:authorize>