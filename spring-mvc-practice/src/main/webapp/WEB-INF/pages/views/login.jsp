<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:if test="${pageContext.request.userPrincipal.name != null}">
    <h3><spring:message code="hello"/>: ${pageContext.request.userPrincipal.name}</h3>
</c:if>
<c:if test="${pageContext.request.userPrincipal.name == null}">
    <div class="panel col-md-4 offset4">
        <form name="f" action="<c:url value='/j_spring_security_check' />" method="post">
            <div class="panel-heading">
                <h3><spring:message code="login.label"/></h3>
            </div>
            <c:if test="${not empty error}">
                <div class="alert alert-danger">
                    <spring:message code="invalid.username.password"/>
                </div>
            </c:if>
            <c:if test="${not empty logout}">
                <div class="alert alert-success">
                    <spring:message code="logout.message"/>
                </div>
            </c:if>
            <div class="panel-body">
                <div class="control-group">
                    <label class="control-label" for="username"><spring:message code="username.label"/></label>
                    <div class="controls">
                        <input class="form-control ng-valid ng-dirty" type="text" id="username" name="username"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="password"><spring:message code="password.label"/></label>
                    <div class="controls">
                        <input class="form-control ng-valid ng-dirty" type="password" id="password" name="password"/>
                    </div>
                </div>
                <div class="clear-fix"></div>
                <br>

                <button type="submit" class="btn btn-success"><spring:message code="login.label"/></button>
            </div>
        </form>
    </div>
</c:if>