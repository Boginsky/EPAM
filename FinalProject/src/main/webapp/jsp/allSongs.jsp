<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${changeLanguage}"/>
<fmt:setBundle basename="pagecontent" var="var"/>
<head>
    <title><fmt:message key="label.songs" bundle="${var}"/></title>
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
            <div class="container">
                <table class="table table-image" style="background: lightgrey;text-align: center;font-style: italic">
                    <thead>
                    <tr>
                        <th scope="col"><fmt:message key="label.image" bundle="${var}"/></th>
                        <th scope="col"><fmt:message key="label.songTitle" bundle="${var}"/></th>
                        <th scope="col"><fmt:message key="label.author" bundle="${var}"/></th>
                        <th scope="col"><fmt:message key="label.album" bundle="${var}"/></th>
                        <th scope="col"><fmt:message key="label.genre" bundle="${var}"/></th>
                        <th scope="col"><fmt:message key="label.price" bundle="${var}"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="song" items="${listOfSongs}">
                        <tr>
                            <td class="w-25">
                                <img src="${song.imageUrl}"
                                     class="img-fluid img-thumbnail" alt="Image"/>
                            </td>
                            <td><c:out value="${song.songName}"/></td>
                            <td><c:out value="${song.author}"/></td>
                            <td><c:out value="${song.album}"/></td>
                            <td><c:out value="${song.genre}"/></td>
                            <td><c:out value="${song.price}"/>
                                <div class="mt-3 d-flex justify-content-between">
                                    <a href="./controller?command=add_to_cart&trackId=${song.id}"
                                       class="btn btn-dark"><fmt:message key="label.addToCart"
                                                                         bundle="${var}"/></a>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <a href="./controller?command=all_songs"
                   class="btn btn-dark btn-lg btn-block"><fmt:message key="label.next"
                                                                      bundle="${var}"/></a>
            </div>
        </div>
    </div>
</div>
</div>
</div>
</body>
<%@include file="/includes/footer.jsp" %>
</html>
