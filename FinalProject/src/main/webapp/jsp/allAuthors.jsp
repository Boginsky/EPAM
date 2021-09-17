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
<div class="container" style="text-align: center">
    <br/>
    <%@include file="/includes/navbarsmall.jsp"%>
    <br/>
    <div class="row">
        <c:forEach var="author" items="${listOfAuthors}">
            <div class="col-md-4 my-3">
                <div class="card w-100" style="width: 18rem;">
                    <img class="rounded-circle" src="${author.imageUrl}" alt="Card image cap">
                    <div class="card-body">
                        <h5 class="card-title"><c:out value="${author.firstName} ${author.lastName}"/></h5>
                        <h6 class="card-title"><c:out value="${author.genreName}"/></h6>
                        <div class="mt-3 d-flex justify-content-center">
                            <a href="./controller?command=All album for author&authorId=${author.id}"
                               class="btn btn-dark"><fmt:message key="label.albums" bundle="${var}"/></a>
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
