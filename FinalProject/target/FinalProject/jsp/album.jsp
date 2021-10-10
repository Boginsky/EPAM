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
        <br/>
        <%@include file="/includes/navbarsmall.jsp" %>
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
                                    <h6 class="mb-0"><fmt:message key="label.informationAboutAlbum"
                                                                  bundle="${var}"/></h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${album.informationAboutAlbum}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <br/>
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
                            <a class="btn btn-sm btn-dark"
                               href="./controller?command=Add_to_cart&trackId=${song.id}"><fmt:message
                                    key="label.addToCart" bundle="${var}"/></a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="card" id="addComment">
                <div class="p-3">
                    <h6><fmt:message key="label.comments" bundle="${var}"/></h6>
                </div>
                <c:if test="${user.userRole == 'USER'}">
                    <form action="./controller" name="command" method="post">
                        <div class="mt-3 d-flex flex-row align-items-center p-3 form-color">
                            <img src="${user.imageUrl}" width="50" class="rounded-circle mr-2"/>
                            <input type="text" class="form-control" name="newComment" required/>
                        </div>
                        <input type="hidden" name="command" value="add_comment"/>
                        <input type="hidden" name="albumId" value="${album.id}"/>
                        <button type="submit" class="btn btn-dark btn-lg btn-block"><fmt:message key="label.addComment"
                                                                                                 bundle="${var}"/></button>
                    </form>
                </c:if>
                <c:forEach var="comment" items="${listOfComments}" varStatus="vs">
                    <div class="mt-2">
                        <div class="mt-3 d-flex flex-row align-items-center p-3 form-color"><img
                                src="${comment.userImageUrl}" width="40" height="40"
                                class="rounded-circle mr-2">
                            <div class="w-100">
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="d-flex flex-row align-items-center"><span
                                            class="mr-2"><c:out value="${comment.userName}"/></span>
                                    </div>
                                </div>
                                <p class="text-justify comment-text mb-0"><c:out
                                        value="${comment.commentMessage}"/></p>
                                <c:if test="${user.userRole == 'USER'}">
                                    <c:if test="${user.id == comment.userId}">
                                        <form action="./controller" name="command" method="post">
                                            <input type="hidden" name="command" value="remove_comment"/>
                                            <input type="hidden" name="commentId" value="${comment.id}"/>
                                            <input type="hidden" name="commentAlbumId" value="${comment.albumId}"/>
                                            <button type="submit" class="btn btn-danger btn-sm"><fmt:message
                                                    key="label.removeComment"
                                                    bundle="${var}"/></button>
                                            <button type="button" class="btn btn-dark btn-sm" data-toggle="modal"
                                                    data-target="#changeComment${vs.index}">
                                                <fmt:message key="label.change" bundle="${var}"/>
                                            </button>
                                        </form>
                                        <%@include file="/includes/changeComment.jsp" %>
                                    </c:if>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <br/>
        </div>
    </div>
</div>
</body>
<%@include file="/includes/footer.jsp" %>
</html>

