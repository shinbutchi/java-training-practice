<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta charset="ISO-8859-1">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="<c:url value="/resources/js/controllers/UserController.js" />"></script>
<div ng-controller="UserController">
    <div class="panel col-md-4 offset4">
        <div class="panel-heading">
            <h3 class="panel-title"><spring:message code="signup.label"/></h3>
        </div>
        <div class="panel-body" style="padding: 15px">
                <p class="alert alert-success" ng-show="success" ng-bind-html="successMessage">
                </p>
                <p class="alert alert-danger" ng-show="error" ng-bind-html="errorMessage">
                </p>
            <div class="form-horizontal">
                <div class="form-group">
                    <div class="control-group">
                        <label class="control-label" for="username"><spring:message code="username.label"/></label>
                        <div class="controls">
                            <input type="text" class="form-control ng-valid ng-dirty" id="username" ng-model="user.username"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="password"><spring:message code="password.label"/></label>
                        <div class="controls">
                            <input type="password" class="form-control ng-valid ng-dirty" id="password" ng-model="user.password">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="retypePassword"><spring:message code="retype.password.label"/></label>
                        <div class="controls">
                            <input type="password" class="form-control ng-valid ng-dirty" id="retypePassword" ng-model="user.retypePassword"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="emailAddress"><spring:message code="email.label"/></label>
                        <div class="controls">
                            <input type="text" class="form-control ng-valid ng-dirty" id="emailAddress" ng-model="user.emailAddress"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="firstName"><spring:message code="firstname.label"/></label>
                        <div class="controls">
                            <input type="text" class="form-control ng-valid ng-dirty" id="firstName" ng-model="user.firstName"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="lastName"><spring:message code="lastname.label"/></label>
                        <div class="controls">
                            <input type="text" class="form-control ng-valid ng-dirty" id="lastName" ng-model="user.lastName"/>
                        </div>
                    </div>
                    <div class="control-group col-md-6">
                        <label class="control-label"><spring:message code="country.label"/></label>
                        <select ng-model="user.country">
                            <option ng-repeat='country in countries' value='{{country.id}}'>{{country.countryName}}</option>
                        </select>
                    </div>
                    <div class="control-group col-md-6">
                        <label class="control-label"><spring:message code="city.label"/></label>
                        <select ng-model="user.city">
                            <option value=""><spring:message code="select.city"/></option>
                            <option ng-repeat='city in getCities()'>{{city.cityName}}</option>
                        </select>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="phoneNumber"><spring:message code="phone.label"/></label>
                        <div class="controls">
                            <input type="text" class="form-control ng-valid ng-dirty" id="phoneNumber" ng-model="user.phoneNumber"/>
                        </div>
                    </div>
                    <div class="clear-fix"></div>
                    <br>
                    <button type="button" class="btn btn-success" ng-click="addUser(user)">
                        <spring:message code="signup.label"/>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>