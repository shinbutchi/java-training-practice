<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li>
                    <h4 class="text-center">This is Header</h4>
                </li>
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <li><a href="/edit-profile"><spring:message code="edit.profile"/></a></li>
                </c:if>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li><a href="/list-user"><spring:message code="list.user"/></a></li>
                </sec:authorize>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <c:if test="${pageContext.request.userPrincipal.name == null}">
                    <li><a href="/login"><spring:message code="login.label"/></a></li>
                    <li><a href="/signup"><spring:message code="signup.label"/></a></li>
                </c:if>
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <li><a href="javascript:void(0)"><spring:message code="hi"/>: ${pageContext.request.userPrincipal.name}</a></li>
                    <li><a href="/logout">Logout</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>