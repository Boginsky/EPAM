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
            <div class="container" style="background: lightgrey;text-align: center">
                <form class="md-form" action="./controller" name="command" method="post" enctype="multipart/form-data">
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="inputSongName"><fmt:message key="label.songTitle" bundle="${var}"/></label>
                            <input type="text" id="inputSongName" class="form-control" name="songTitle"
                                   placeholder="<fmt:message key="label.songTitle" bundle="${var}"/>" required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="inputSongPrice"><fmt:message key="label.price" bundle="${var}"/></label>
                            <input type="number" class="form-control" id="inputSongPrice" name="songPrice"
                                   placeholder="<fmt:message key="label.price" bundle="${var}"/>" min="0" max="100">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <label for="genres"><fmt:message key="label.genre" bundle="${var}"/></label>
                            <select id="genres" name="genreId" class="form-control">
                                <option selected><fmt:message key="label.choose" bundle="${var}"/></option>
                                <c:forEach var="genre" items="${listOfGenres}">
                                    <option value="${genre.id}"><c:out value="${genre.genreName}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="albums"><fmt:message key="label.album" bundle="${var}"/></label>
                            <select id="albums" name="albumId" class="form-control">
                                <option selected><fmt:message key="label.choose" bundle="${var}"/></option>
                                <c:forEach var="album" items="${listOfAlbums}">
                                    <option value="${album.id}"><c:out value="${album.albumName}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="authors"><fmt:message key="label.author" bundle="${var}"/></label>
                            <select id="authors" name="authorId" class="form-control">
                                <option selected><fmt:message key="label.choose" bundle="${var}"/></option>
                                <c:forEach var="author" items="${listOfAuthors}">
                                    <option value="${author.id}"><c:out value="${author.authorName}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <c:choose>
                        <c:when test="${trackId != null}">
                    <input type="hidden" name="trackId" value="${trackId}">
                    <input type="hidden" name="command" value="change_song_confirm"/>
                        <button type="submit" class="btn btn-primary"><fmt:message key="label.change"
                                                                                   bundle="${var}"/></button>
                        </c:when>
                        <c:otherwise>
                            <input type="hidden" name="command" value="add_song_confirm"/>
                            <button type="submit" class="btn btn-primary"><fmt:message key="label.add"
                                                                                       bundle="${var}"/></button>
                        </c:otherwise>
                    </c:choose>

                </form>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="/includes/footer.jsp" %>
</html>
