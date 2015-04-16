<%@page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<h1>Title : ${title}</h1>
<h1>Message : ${message}</h1>

<!-- For login user -->
<c:if test="${pageContext.request.userPrincipal.name != null}">
	<h2>
		User : ${pageContext.request.userPrincipal.name} |
		<a href="/logout">Logout</a>
	</h2>
</c:if>