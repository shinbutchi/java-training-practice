<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-responsive.css"/>">
        <script src="<c:url value="/resources/js/jquery-1.8.3.min.js" />"></script>
        <script src="<c:url value="/resources/js/angular/angular.js" />"></script>
        <script src="<c:url value="/resources/js/app.js" />"></script>
        <script src="<c:url value="/resources/js/AngularApp.js" />"></script>
        <script src="<c:url value="/resources/js/bootstrap-toggle.min.js" />"></script>
        <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
    </head>
    <body>
    <!-- Start Header -->
    <header role="navigation">
        <tiles:insertAttribute name="header" flush="false" ignore="true" />
    </header>
    <!-- End Header -->

    <!-- Start Content -->
    <div class="wrapper" id="main">
        <tiles:insertAttribute name="content" flush="false" ignore="true" />
    </div>
    <!-- End Content -->

    <!-- Start Header -->
    <footer id="footer" class="bg-pcsmastercard">
        <tiles:insertAttribute name="footer" flush="false" ignore="true" />
    </footer>
    <!-- End Header -->
    </body>
</html>