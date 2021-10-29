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
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <div class="card-container center">
        <div class="card card-1">
            <a href="./controller?command=all_songs">
                <img class="topImage" src="./imageTransfer?imageUuid=${listOfAlbumsImgUrl[0]}" alt="Card image cap">
            </a>
            <div class="bottom">
                <a type="button" class="btn btn-secondary btn-lg btn-block"
                   href="./controller?command=all_songs"><fmt:message key="label.songs" bundle="${var}"/></a>
            </div>
        </div>
        <div class="card card-1">
            <a href="./controller?command=all_albums&pageId=1">
                <img class="topImage" src="./imageTransfer?imageUuid=${listOfAlbumsImgUrl[1]}" alt="Card image cap">
            </a>
            <div class="bottom">
                <a type="button" class="btn btn-secondary btn-lg btn-block"
                   href="./controller?command=all_albums&pageId=1"><fmt:message key="label.albums" bundle="${var}"/></a>
            </div>
        </div>
        <div class="card card-1">
            <a href="./controller?command=all_authors&pageId=1">
                <img class="topImage" src="./imageTransfer?imageUuid=${listOfAlbumsImgUrl[2]}" alt="Card image cap">
            </a>
            <div class="bottom">
                <a type="button" class="btn btn-secondary btn-lg btn-block"
                   href="./controller?command=all_authors&pageId=1"><fmt:message key="label.authors" bundle="${var}"/></a>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="/includes/footer.jsp" %>
</html>