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
            <c:forEach var="album" items="${listOfSongs}">
                <div class="col-md-4 my-3">
                    <div class="card w-100" style="width: 18rem;">
                        <img class="card-img-top" src="${album.imageUrl}" alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title"><fmt:message key="label.songTitle" bundle="${var}"/><c:out
                                    value="${album.songName}"/></h5>
                            <h6 class="price"><fmt:message key="label.price" bundle="${var}"/><c:out
                                    value="${album.price}"/></h6>
                            <h7 class="author"><fmt:message key="label.author" bundle="${var}"/><c:out
                                    value="${album.author}"/></h7>
                            <h7 class="album"><fmt:message key="label.album" bundle="${var}"/><c:out
                                    value="${album.album}"/></h7>
                            <h7 class="genre"><fmt:message key="label.genre" bundle="${var}"/><c:out
                                    value="${album.genre}"/></h7>
                            <div class="mt-3 d-flex justify-content-between">
                                <a href="./controller?command=Add_to_cart&trackId=${album.id}"
                                   class="btn btn-dark"><fmt:message key="label.addToCart" bundle="${var}"/></a>
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
