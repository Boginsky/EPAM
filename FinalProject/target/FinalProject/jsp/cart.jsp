<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${changeLanguage}"/>
<fmt:setBundle basename="pagecontent" var="var"/>
<html>
<head>
    <title>Cart</title>
    <%@include file="/includes/head.jsp" %>
</head>
<body>
<div class="bg">
    <%@include file="/includes/navbar.jsp" %>
    <div class="container">
        <br/>
        <%@include file="/includes/navbarsmall.jsp" %>
        <br/>
        <table class="table table-light">
            <thead>
            <tr>
                <th style="width: 25%" scope="col">Name</th>
                <th style="width: 25%" scope="col">Author</th>
                <th style="width: 25%" scope="col">Album</th>
                <th style="width: 25%" scope="col">Genre</th>
                <th style="width: 25%" scope="col">Price</th>
                <th style="width: 25%" scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <c:set var="total" value="${0}"/>
            <c:forEach var="song" items="${listOfSongsInCart}">
                <tr>
                    <c:set var="total" value="${total + song.price}"/>
                    <td><c:out value="${song.songName}"/></td>
                    <td><c:out value="${song.author}"/></td>
                    <td><c:out value="${song.album}"/></td>
                    <td><c:out value="${song.genre}"/></td>
                    <td><c:out value="${song.price}"/></td>
                    <td>
                        <a class="btn btn-sm btn-danger"
                           href="./controller?command=Remove_from_cart&trackId=${song.id}">Remove</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="float-right">
            <div class="d-flex py-3">
                <h3>Total price: ${total}</h3>
            </div>
            <%@include file="/includes/submitOrder.jsp" %>
        </div>
    </div>
</body>
<%@include file="/includes/footer.jsp" %>
</html>