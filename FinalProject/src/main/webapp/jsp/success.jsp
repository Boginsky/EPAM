<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${changeLanguage}"/>
<fmt:setBundle basename="pagecontent" var="var"/>
<html>
<head>
    <title><fmt:message key="label.mainPageTitle" bundle="${var}"/></title>
    <style>
        <%@include file="/static/css/main.css" %>
    </style>
    <%@include file="/includes/head.jsp" %>
</head>
<body>
<div class="bg">
    <%@include file="/includes/navbar.jsp" %>
    <br>
    <br>
    <br>
    <br>
    <br>
    <div class="card-container center">
        <div class="card card-1">
            <div class="jumbotron text-center">
                <head>
                        <div style="border-radius:200px; height:200px; width:200px; margin:0 auto; background-image: url("./static/image/vinyl-disk-image.jpg");">
                            <i class="checkmark">✓</i>
                        </div>
                </head>
                <h1 class="display-3">Operation was successfully completed</h1>
                <hr>
                <p class="lead">
                    <a class="btn btn-primary btn-sm" href="http://localhost:8080/FinalProject_war/controller?command=all_songs" role="button">Continue
                    </a>
                </p>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="/includes/footer.jsp" %>
</html>
