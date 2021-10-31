<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${changeLanguage}"/>
<fmt:setBundle basename="pagecontent" var="var"/>
<html>
<head>
    <title><fmt:message key="label.removeAccount" bundle="${var}"/></title>
    <%@include file="/includes/head.jsp" %>
</head>
<body>
<div class="bg">
    <%@include file="/includes/navbar.jsp" %>
    <div class="h-100 row align-items-center">
        <div class="container" style="text-align: center;background: white">
            <h1 class="display-1">
                <fmt:message key="label.removeAccountText1" bundle="${var}"/>
                <br/><fmt:message key="label.removeAccountText2" bundle="${var}"/>
                <br/><fmt:message key="label.removeAccountText3" bundle="${var}"/>
                <c:choose>
                <c:when test="${userToRemove == null}">
                <br/>${user.firstName} ${user.lastName}!
            </h1>
            <hr/>
            <a class="btn btn-dark btn-lg btn-block" style="text-align: center"
               href="./controller?command=remove_account_confirm&userId=${user.id}"><fmt:message
                    key="label.removeConfirm" bundle="${var}"/></a>\
            <br/>
            </c:when>
            <c:otherwise>
                <br/>${userToRemove.firstName} ${userToRemove.lastName}!
                </h1>
                <hr/>
                <a class="btn btn-danger btn-lg btn-block" style="text-align: center"
                   href="./controller?command=remove_account_confirm&userId=${userToRemove.id}"><fmt:message
                        key="label.removeConfirm" bundle="${var}"/></a>\
                <br/>
            </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
</body>
<%@include file="/includes/footer.jsp" %>
</html>
