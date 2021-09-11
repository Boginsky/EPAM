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
<%@include file="/includes/navbar.jsp"%>
<div class="container">
    <div class="card-header my-4"><fmt:message key="label.allSongs" bundle="${var}"/></div>
    <div class="row">
        <c:forEach var="elem" items="${listOfSongs}">
        <div class="col-md-4 my-3">
            <div class="card w-100" style="width: 18rem;">
                <img class="card-img-top" src="${elem.imageUrl}" alt="Card image cap">
<%--                <img class="card-img-top" src="./static/image/background.jpg" alt="Card image cap">--%>
                <div class="card-body">
                    <h5 class="card-title"><fmt:message key="label.songTitle" bundle="${var}"/><c:out value="${elem.songName}"/></h5>
                    <h6 class="price"><fmt:message key="label.price" bundle="${var}"/><c:out value="${elem.price}"/></h6>
                    <h7 class="author"><fmt:message key="label.author" bundle="${var}"/><c:out value="${elem.author}"/></h7>
                    <h8 class="album"><fmt:message key="label.album" bundle="${var}"/><c:out value="${elem.album}"/></h8>
                    <h9 class="genre"><fmt:message key="label.genre" bundle="${var}"/><c:out value="${elem.genre}"/></h9>
                    <div class="mt-3 d-flex justify-content-between">
                        <a href="#" class="btn btn-primary"><fmt:message key="label.addToCart" bundle="${var}"/></a>
                        <a href="#" class="btn btn-primary"><fmt:message key="label.buyNow" bundle="${var}"/></a>
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
