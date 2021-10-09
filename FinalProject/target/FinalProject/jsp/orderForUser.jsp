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
    <div class="container">
        <br>
        <%@include file="/includes/navbarsmall.jsp" %>
        <div class="modal-body">
            <div class="modal-fade" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle"><fmt:message key="label.orderNumber"
                                                                                        bundle="${var}"/>${orderId}</h5>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <table class="table table-light">
                                <thead>
                                <tr>
                                    <th style="width: 25%" scope="col"><fmt:message key="label.songTitle"
                                                                                    bundle="${var}"/></th>
                                    <th style="width: 25%" scope="col"><fmt:message key="label.author"
                                                                                    bundle="${var}"/></th>
                                    <th style="width: 25%" scope="col"><fmt:message key="label.album"
                                                                                    bundle="${var}"/></th>
                                    <th style="width: 25%" scope="col"><fmt:message key="label.genre"
                                                                                    bundle="${var}"/></th>
                                    <th style="width: 25%" scope="col"><fmt:message key="label.price"
                                                                                    bundle="${var}"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="song" items="${listOfSongsForOrder}">
                                    <tr>
                                        <td><c:out value="${song.songName}"/></td>
                                        <td><c:out value="${song.author}"/></td>
                                        <td><c:out value="${song.album}"/></td>
                                        <td><c:out value="${song.genre}"/></td>
                                        <td><c:out value="${song.price}"/></td>

                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="/includes/footer.jsp" %>
</html>
