<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${changeLanguage}"/>
<fmt:setBundle basename="pagecontent" var="var"/>
<head>
    <title><fmt:message key="label.welcome" bundle="${var}"/></title>
    <%@include file="/includes/head.jsp" %>
</head>
<body>
<div class="bg">

    <%@include file="/includes/navbar.jsp" %>
    <div class="container" style="text-align: center">
        <br/>
        <%@include file="/includes/navbarsmall.jsp" %>
        <br/>
        <div class="row">
            <c:forEach var="author" items="${listOfAuthors}" varStatus="vs">
                <div class="col-md-4 my-3">
                    <div class="card w-100" style="width: 18rem;">
                        <a href="./controller?command=albums_for_author&authorId=${author.id}">
                            <img class="card-img-top" src="./imageTransfer?imageUuid=${author.imageUrl}"
                                 alt="Card image cap" style="height: 22rem">
                        </a>
                        <div class="card-body">
                            <h5 class="card-title"><c:out value="${author.authorName}"/></h5>
                            <c:forEach var="genre" items="${author.listOfGenres}">
                                <h6 class="card-title"><c:out value="${genre}"/></h6>
                            </c:forEach>
                            <div class="mt-3 d-flex justify-content-center">
                                <a href="./controller?command=albums_for_author&authorId=${author.id}"
                                   class="btn btn-dark"><fmt:message key="label.albums" bundle="${var}"/></a>
                            </div>
                            <c:if test="${user.userRole == 'ADMIN'}">
                                <div class="mt-3 d-flex justify-content-center">
                                    <button type="button" style="float: right" class="btn btn-dark"
                                            data-toggle="modal" data-target="#changeAuthor${vs.index}">
                                        <fmt:message key="label.change" bundle="${var}"/>
                                    </button>
                                    <%@include file="/includes/changeAuthor.jsp" %>
                                </div>
                                <div class="mt-3 d-flex justify-content-center">
                                    <a href="./controller?command=remove_author&authorId=${author.id}"
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
               href="./controller?command=all_authors&pageId=${pageId-2}">Previous</a>
        </c:if>
        <c:if test="${fn:length(listOfAuthors) >= 2}">
            <a class="btn btn-dark btn-lg btn-block"
               href="./controller?command=all_authors&pageId=${pageId}">Next</a>
        </c:if>
    </div>
</div>
</body>
<%@include file="/includes/footer.jsp" %>
</html>
