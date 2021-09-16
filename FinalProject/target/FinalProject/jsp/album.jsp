<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${changeLanguage}"/>
<fmt:setBundle basename="pagecontent" var="var"/>
<html>
<head>
    <title><fmt:message key="label.mainPageTitle" bundle="${var}"/></title>
    <%@include file="/includes/head.jsp" %>
</head>
<body>
<%@include file="/includes/navbar.jsp" %>
<div class="container">
    <br>
    <%@include file="/includes/navbarsmall.jsp"%>
    <br/>
    <div class="main-body">
        <div class="row gutters-sm">
            <div class="col-md-4">
                <div class="d-flex flex-column align-items-center text-center">
                    <img class="item" src="./static/image/2.jpg" alt="Card image cap">
                </div>
            </div>
            <div class="col-md-8">
                <div class="card mb-3">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="label.fullName" bundle="${var}"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${user.firstName} ${user.lastName}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="label.email" bundle="${var}"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${user.email}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="label.password" bundle="${var}"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                *********
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="label.role" bundle="${var}"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${user.userRole}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="col-md-12">
    <div class="card mb-3">
        <div class="card-body">
            <div class="row">
                <div class="col-sm-3">
                    <h6 class="mb-0"><fmt:message key="label.fullName" bundle="${var}"/></h6>
                </div>
                <div class="col-sm-9 text-secondary">
                    ${user.firstName} ${user.lastName}
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-sm-3">
                    <h6 class="mb-0"><fmt:message key="label.email" bundle="${var}"/></h6>
                </div>
                <div class="col-sm-9 text-secondary">
                    ${user.email}
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-sm-3">
                    <h6 class="mb-0"><fmt:message key="label.password" bundle="${var}"/></h6>
                </div>
                <div class="col-sm-9 text-secondary">
                    *********
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-sm-3">
                    <h6 class="mb-0"><fmt:message key="label.role" bundle="${var}"/></h6>
                </div>
                <div class="col-sm-9 text-secondary">
                    ${user.userRole}
                </div>
            </div>
        </div>
    </div>
</div>
        <%@include file="/includes/footer.jsp" %>
</body>
</html>

