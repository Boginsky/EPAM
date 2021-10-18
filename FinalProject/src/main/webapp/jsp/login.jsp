<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${changeLanguage}"/>
<fmt:setBundle basename="pagecontent" var="var"/>
<html>
<head>
    <title>AudioStore</title>
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
                    <label><fmt:message key="label.email" bundle="${var}"/></label>
                    <input type="email" class="form-control" name="email"
                           placeholder="<fmt:message key="label.enterYourEmail" bundle="${var}"/>" required>
                </div>
                <div class="form-group">
                    <label><fmt:message key="label.password" bundle="${var}"/></label>
                    <input type="password" class="form-control" name="password"
                           placeholder="<fmt:message key="label.enterYourPassword" bundle="${var}"/>"
                           required>
                </div>
                <div class="text-center">
                    <input type="hidden" name="command" value="Sign_in"/>
                    <button type="submit" class="btn btn-primary"><fmt:message key="label.signIn"
                                                                               bundle="${var}"/></button>
                </div>
                <br>
            </form>
        </div>
    </div>
</div>
<%@include file="/includes/footer.jsp" %>
</body>
</html>
