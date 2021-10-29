<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${changeLanguage}"/>
<fmt:setBundle basename="pagecontent" var="var"/>
<html>
<head>
    <title><fmt:message key="label.mainPageTitle" bundle="${var}"/></title>
    <%@include file="/includes/head.jsp" %>
    <style>
        <%@include file="/static/css/main.css" %>
    </style>
</head>
<body>
<div class="bg">
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <div class="card-container center">
        <div class="card"  style="height: 440px">
            <div class="jumbotron text-center">
                <h1 class="display-3"><fmt:message key="label.success" bundle="${var}"/></h1>
                <hr>
                <p class="lead" >
                    <a class="btn btn-dark" href="http://localhost:8080/FinalProject_war/controller?command=all_songs" role="button"><fmt:message key="label.continue" bundle="${var}"/>
                    </a>
                </p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
