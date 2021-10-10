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
<div class="bg">
    <%@include file="/includes/navbar.jsp" %>
    <div class="container">
        <br>
        <%@include file="/includes/navbarsmall.jsp" %>
        <br/>
        <div class="main-body">
            <div class="row gutters-sm">
                <div class="col-md-4">
                    <div class="d-flex flex-column align-items-center text-center">
                        <img class="item" src="${author.imageUrl}" alt="Card image cap">
                    </div>
                </div>
                <div class="col-md-8">
                    <div class="card mb-3">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0"><fmt:message key="label.author" bundle="${var}"/></h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${author.name}
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0"><fmt:message key="label.genre" bundle="${var}"/></h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${author.genreName}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <br/>
        <div class="row">
            <c:forEach var="album" items="${listOfAlbums}">
                <div class="col-md-4 my-3">
                    <div class="card w-100" style="width: 18rem;">
                        <a href="./controller?command=all_album_song&albumId=${album.id}">
                            <img class="card-img-top" src="${album.imageUrl}" alt="Card image cap">
                        </a>
                        <div class="card-body" style="text-align: center">
                            <h6 class="card-title"><fmt:message key="label.albumName" bundle="${var}"/></h6>
                            <h5><c:out value="${album.albumName}"/></h5>
                            <h6 class="card-title"><fmt:message key="label.author" bundle="${var}"/></h5>
                            <h5><c:out value="${album.authorName}"/></h5>
                            <div class="mt-3 d-flex justify-content-center">
                                <a href="./controller?command=all_album_song&albumId=${album.id}"
                                   class="btn btn-dark" style="text-align: center"><fmt:message key="label.listOfSongs"
                                                                                                bundle="${var}"/></a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
<%@include file="/includes/footer.jsp" %>
</html>
