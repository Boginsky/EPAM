<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${changeLanguage}"/>
<fmt:setBundle basename="pagecontent" var="var"/>
<html>
<head>
    <title><fmt:message key="label.confirm" bundle="${var}"/></title>
    <%@include file="/includes/head.jsp" %>
</head>
<body>
<div class="bg">
    <%@include file="/includes/navbar.jsp" %>
    <div class="container">
        <div class="card w-50 mx-auto my-5"/>
        <div class="card-header text-center"><fmt:message key="label.welcome" bundle="${var}"/></div>
        <div class="card-body">
            <form action="./controller" name="command" method="post">
                <div class="form-group">
                    <label><fmt:message key="label.confirmationCode" bundle="${var}"/></label>
                    <input type="text" class="form-control" name="submittedConfirmCode"
                           placeholder="<fmt:message key="label.enterConfirmationCode" bundle="${var}"/>" required>
                </div>
                <div class="text-center">
                    <input type="hidden" name="realConfirmCode" value="${realConfirmCode}">
                    <input type="hidden" name="encryptedPassword" value="${encryptedPassword}">
                    <input type="hidden" name="command" value="Confirm registration"/>
                    <button type="submit" class="btn btn-primary"><fmt:message key="label.confirm"
                                                                               bundle="${var}"/></button>
                </div>
                <br>
            </form>
        </div>
    </div>
</div>
</body>
<%@include file="/includes/footer.jsp" %>
</html>
