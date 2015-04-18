<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta charset="ISO-8859-1">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="<c:url value="/resources/js/controllers/UserController.js" />"></script>
<div ng-controller="UserController">
    <div class="panel col-md-6 offset3">
        <header class="panel-heading">
            <h3 class="title">Sign Up</h3>
        </header>
        <div class="content" style="padding: 15px">
                <p class="alert alert-success" ng-show="success" ng-bind-html="successMessage">
                </p>
                <p class="alert alert-danger" ng-show="error" ng-bind-html="errorMessage">
                </p>
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
                    <div class="control-group">
                        <label class="control-label" for="retypePassword">Retype Password</label>
                        <div class="controls">
                            <input type="password" class="form-control ng-valid ng-dirty" id="retypePassword" ng-model="user.retypePassword"/>
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