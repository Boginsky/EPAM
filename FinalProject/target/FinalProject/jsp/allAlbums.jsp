<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${changeLanguage}"/>
<fmt:setBundle basename="pagecontent" var="var"/>
<head>
    <title><fmt:message key="label.welcome" bundle="${var}"/></title>
    <%@include file="/includes/head.jsp" %>
</head>
<body>
<div class="bg">
    <%@include file="/includes/navbar.jsp" %>
    <div class="container">
        <br/>
        <%@include file="/includes/navbarsmall.jsp" %>
        <br/>
        <div class="row">
            <c:forEach var="album" items="${listOfAlbums}" varStatus="vs">
                <div class="col-md-4 my-3">
                    <div class="card w-100" style="width: 18rem;">
                        <a href="./controller?command=all_album_song&albumId=${album.id}">
                            <img class="card-img-top" src="./imageTransfer?imageUuid=${album.imageUrl}" alt="Card image cap">
                        </a>
                        <div class="card-body" style="text-align: center">
                            <h6 class="card-title"><fmt:message key="label.albumName" bundle="${var}"/></h6>
                            <h5><c:out value="${album.albumName}"/></h5>
                            <h6 class="card-title"><fmt:message key="label.author" bundle="${var}"/></h6>
                            <c:forEach var="author" items="${album.listOfAuthors}">
                                <h5><c:out value="${author}"/></h5>
                            </c:forEach>
                            <div class="mt-3 d-flex justify-content-center">
                                <a href="./controller?command=all_songs_for_album&albumId=${album.id}"
                                   class="btn btn-dark" style="text-align: center"><fmt:message key="label.listOfSongs"
                                                                                                bundle="${var}"/></a>
                            </div>
                            <c:if test="${user.userRole == 'ADMIN'}">
                                <div class="mt-3 d-flex justify-content-center">
                                    <button type="button" style="float: right" class="btn btn-dark" data-toggle="modal" data-target="#changeAlbum${vs.index}">
                                        <fmt:message key="label.change" bundle="${var}"/>
                                    </button>
                                    <%@include file="/includes/changeAlbum.jsp" %>
                                </div>
                                <div class="mt-3 d-flex justify-content-center">
                                    <a href="./controller?command=remove_album&albumId=${album.id}"
                                       class="btn btn-danger"><fmt:message key="label.delete" bundle="${var}"/></a>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <br/>
        <c:if test="${pageId > 2}">
            <a class="btn btn-dark btn-lg btn-block"
               href="./controller?command=all_albums&pageId=${pageId-2}">Previous</a>
        </c:if>
        <c:if test="${fn:length(listOfAlbums) >= 5}">
            <a class="btn btn-dark btn-lg btn-block"
               href="./controller?command=all_albums&pageId=${pageId}">Next</a>
        </c:if>
    </div>
</div>
</body>
<%@include file="/includes/footer.jsp" %>
</html>
