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
<%@include file="/includes/navbar.jsp" %>
<div class="container">
    <br/>
    <%@include file="/includes/navbarsmall.jsp"%>
    <br/>
    <div class="row">
        <c:forEach var="album" items="${listOfAlbums}">
            <div class="col-md-4 my-3">
                <div class="card w-100" style="width: 18rem;">
                    <img class="card-img-top" src="${album.imageUrl}" alt="Card image cap">
                    <div class="card-body">
                        <h5 class="card-title"><fmt:message key="label.albumName" bundle="${var}"/><c:out
                                value="${album.albumName}"/></h5>
                        <h6 class="card-title"><fmt:message key="label.author" bundle="${var}"/><c:out
                                value="${album.authorName}"/></h6>
                        <h7 class="price"><fmt:message key="label.informationAboutAlbum" bundle="${var}"/><c:out value="${album.informationAboutAlbum}"/></h7>
                        <div class="mt-3 d-flex justify-content-center" >
                            <a href="./controller?command=All album song&albumId=${album.id}"
                               class="btn btn-dark" style="text-align: center"><fmt:message key="label.listOfSongs" bundle="${var}"/></a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<%@include file="/includes/footer.jsp" %>
</body>
</html>
