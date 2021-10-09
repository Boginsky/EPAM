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
    <div class="container" style="text-align: center">
        <br/>
        <%@include file="/includes/navbarsmall.jsp" %>
        <br/>
        <div class="row">
            <c:forEach var="author" items="${listOfAuthors}">
                <div class="col-md-4 my-3">
                    <div class="card w-100" style="width: 18rem;">
                        <a href="./controller?command=Albums_for_author&authorId=${author.id}">
                            <img class="card-img-top" src="${author.imageUrl}" alt="Card image cap">
                        </a>
                        <div class="card-body">
                            <h5 class="card-title"><c:out value="${author.name}"/></h5>
                            <h6 class="card-title"><c:out value="${author.genreName}"/></h6>
                            <div class="mt-3 d-flex justify-content-center">
                                <a href="./controller?command=Albums_for_author&authorId=${author.id}"
                                   class="btn btn-dark"><fmt:message key="label.albums" bundle="${var}"/></a>
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
