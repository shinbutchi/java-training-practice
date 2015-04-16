<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta charset="ISO-8859-1">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script src="<c:url value="/resources/js/controllers/UserController.js" />"></script>
<div ng-controller="UserController">
    <div class="panel">
        <header class="panel-heading">
            <div class="title">Sign Up</div>
        </header>
        <div class="content" style="padding: 15px">
            <div id="messageContainer" class="clear">
                <div class="alert alert-success" ng-show="success" ng-if="!isPopupOpen">
                    <h4>{{successMessage}}</h4>
                </div>
            </div>
            <%--<div ng-hide="terms" class="alert alert-info">--%>
                <%--<spring:message code="term.none" />--%>
            <%--</div>--%>
            <div class="form-horizontal">
                <div class="form-group">
                    <div class="control-group">
                        <label class="control-label" for="emailAddress">Email Address</label>
                        <div class="controls">
                            <input type="text" class="form-control ng-valid ng-dirty" id="emailAddress" ng-model="user.emailAddress"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="password">Password</label>
                        <div class="controls">
                            <input type="password" class="form-control ng-valid ng-dirty" id="password" ng-model="user.password">
                        </div>
                    </div>
                    <button type="button" class="btn btn-primary" ng-click="addUser(user)">
                        Sign Up
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>