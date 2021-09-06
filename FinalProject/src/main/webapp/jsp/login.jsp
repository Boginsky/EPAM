<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="en"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>AudioStore</title>
    <%@include file="/includes/head.jsp" %>
</head>
<body>
<%@include file="/includes/navbar.jsp"%>
<div class="container">
    <div class="card w-50 mx-auto my-5"/>
    <div class="card-header text-center"><fmt:message key="label.welcome"/></div>
    <div class="card-body">
        <form action="./controller" name="command" method="post">
            <div class="form-group">
                <label><fmt:message key="label.email"/></label>
                <input type="email" class="form-control" name="email" placeholder="<fmt:message key="label.enterYourEmail"/>" required>
            </div>git s
            <div class="form-group">
                <label><fmt:message key="label.password"/></label>
                <input type="password" class="form-control" name="password" placeholder="<fmt:message key="label.enterYourPassword"/>"
                       required>
            </div>
            <div class="text-center">
                <input type="hidden" name="command" value="Sign in"/>
                <button type="submit" class="btn btn-primary"><fmt:message key="label.signIn"/></button>
            </div>
            <br>
            <div style="text-align: center">
                <a href="/controller?command=english">EN </a>
                <a href="/controller?command=russian">RU</a>
            </div>
        </form>
    </div>
</div>
<%@include file="/includes/footer.jsp" %>
</body>
</html>
