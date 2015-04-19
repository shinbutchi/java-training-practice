<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta charset="ISO-8859-1">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="<c:url value="/resources/js/controllers/UserController.js" />"></script>
<body ng-controller="UserController" ng-init="listUsers()">
    <div class="content">
        <div ng-hide="users" class="empty alert alert-info">
            <span><spring:message code="empty.user.label"/> </span>
        </div>
        <div ng-show="success" class="alert alert-success" ng-bind-html="successMessage">
        </div>
        <div class="listUser">
            <table class="table table-bordered table-striped-modifier" ng-show="users" id="user-table">
                <thead style="text-align: center">
                <tr style="background-color: #F3F5F9">
                    <td><spring:message code="no"/>.</td>
                    <td><spring:message code="firstname.label"/></td>
                    <td><spring:message code="lastname.label"/></td>
                    <td><spring:message code="email.label"/></td>
                    <td><spring:message code="country.label"/></td>
                    <td><spring:message code="city.label"/></td>
                    <td><spring:message code="phone.label"/></td>
                    <td><spring:message code="created.date.label"/></td>
                    <td><spring:message code="modified.date.label"/></td>
                </tr>
                </thead>
                <tr ng-repeat="user in users">
                    <td width="3%"
                        style="text-align: center">{{(currentPage-1) * maxRecordPerPage + $index + 1}}
                    </td>
                    <td width="8%" align="center">
                        <span>{{user.firstName}}</span>
                    </td>
                    <td width="8%" align="center">
                        <span>{{user.lastname}}</span>
                    </td>
                    <td width="8%" align="center">
                        <span>{{user.emailAddress}}</span>
                    </td>
                    <td width="8%" align="center">
                        <span>{{countries[user.country].countryName}}</span>
                    </td>
                    <td width="8%" align="center">
                        <span>{{user.city}}</span>
                    </td>
                    <td width="8%" align="center">
                        <span>{{user.phoneNumber}}</span>
                    </td>
                    <td width="8%" align="center">
                        <span>{{user.createdDate | date:'dd-MM-yyyy'}}</span>
                    </td>
                    <td width="8%" align="center">
                        <span>{{user.lastModifiedDate | date:'dd-MM-yyyy'}}</span>
                    </td>
                    <%--<td width="15%" style="text-align: center">--%>
                        <%--<button class="btn btn-mini btn-danger"  ng-click="confirmDeleteFaq(faq.id)"><spring:message code="remove"/> </button>--%>
                        <%--<button class="btn btn-mini btn-success"ng-click="editFaq(faq.id)" ><spring:message code="faq.edit.button"/> </button>--%>
                    <%--</td>--%>
                </tr>
            </table>
        </div>
        <div class="align-center" ng-show="users && totalPages>1">
            <pagination boundary-links="true" total-items="totalRecord" ng-model="currentPage"
                        ng-change="getData(currentPage)" max-size="maxSize"
                        class="pagination-sm" previous-text="&lsaquo;" items-per-page="maxRecordPerPage"
                        next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"></pagination>
        </div>
    </div>
</body>