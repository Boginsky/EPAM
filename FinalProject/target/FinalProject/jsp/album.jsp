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
                    <img class="item" src="${album.imageUrl}" alt="Card image cap">
                </div>
            </div>
            <div class="col-md-8">
                <div class="card mb-3">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="label.albumName" bundle="${var}"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${album.albumName}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="label.author" bundle="${var}"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${album.authorName}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="label.informationAboutAlbum" bundle="${var}"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${album.informationAboutAlbum}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<table class="table table-light">
    <thead>
    <tr>
        <th style="width: 25%" scope="col"><fmt:message key="label.songTitle" bundle="${var}"/></th>
        <th style="width: 25%" scope="col"><fmt:message key="label.author" bundle="${var}"/></th>
        <th style="width: 25%" scope="col"><fmt:message key="label.genre" bundle="${var}"/></th>
        <th style="width: 25%" scope="col"><fmt:message key="label.price" bundle="${var}"/></th>
        <th style="width: 25%" scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="song" items="${listOfSongsForAlbum}">
        <tr>
            <td><c:out value="${song.songName}"/></td>
            <td><c:out value="${song.author}"/></td>
            <td><c:out value="${song.genre}"/></td>
            <td><c:out value="${song.price}"/></td>
            <td>
                <a class="btn btn-sm btn-dark" href="./controller?command=Add to cart&trackId=${song.id}"><fmt:message key="label.addToCart" bundle="${var}"/></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
        <%@include file="/includes/footer.jsp" %>
</body>
</html>

