<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%--<%@ page isELIgnored="false" %>--%>
<%--<fmt:setLocale value="ru"/>--%>
<%--<fmt:setBundle basename="pagecontent"/>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>AudioStore</title>--%>
<%--    <%@include file="/includes/head.jsp" %>--%>
<%--</head>--%>
<%--<body>--%>
<%--<%@include file="/includes/navbar.jsp"%>--%>
<%--<div class="container">--%>
<%--    <div class="card w-50 mx-auto my-5"/>--%>
<%--    <div class="card-header text-center"><fmt:message key="label.welcome"/></div>--%>
<%--    <div class="card-body">--%>
<%--        <form action="./controller" name="command" method="post">--%>
<%--            <div class="form-group">--%>
<%--                <label><fmt:message key="label.email"/></label>--%>
<%--                <input type="email" class="form-control" name="login-email" placeholder="<fmt:message key="label.enterYourEmail"/>" required>--%>
<%--            </div>--%>
<%--            <div class="form-group">--%>
<%--                <label><fmt:message key="label.password"/></label>--%>
<%--                <input type="password" class="form-control" name="login-password" placeholder="<fmt:message key="label.enterYourPassword"/>"--%>
<%--                       required>--%>
<%--            </div>--%>
<%--            <div class="text-center">--%>
<%--                <button type="submit" class="btn btn-primary"><fmt:message key="label.signIn"/></button>--%>
<%--            </div>--%>
<%--        </form>--%>
<%--    </div>--%>
<%--</div>--%>
<%--<%@include file="/includes/footer.jsp" %>--%>
<%--</body>--%>
<%--</html>--%>
<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${changeLanguage}"/>
<fmt:setBundle basename="resources.pagecontent" var="var"/>
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="label.title" bundle="${var}"/></title>
    <link href="/assets/css/auth/loginpage.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div>Audio Store</div>
<form id="login" action="./controller" name="command" method="post">
    <h1><fmt:message key="label.welcome" bundle="${var}"/></h1>
    <fieldset id="inputs">
        <input id="email" name="email" type="text" placeholder="Email" autofocus required>
        <input id="password" name="password" type="password" placeholder="<fmt:message key="label.password"
        bundle="${var}"/>" required>
    </fieldset>
    <error>${errorSignInMessage}</error>
    <fieldset id="actions">
        <input type="hidden" name="command" value="Sign in"/>
        <input type="submit" id="submit" value="<fmt:message key="label.signIn" bundle="${var}"/>"/>
        <a href="/controller?command=sign up"><fmt:message key="label.signUp" bundle="${var}"/></a>
    </fieldset>
    <br>
    <a href="/controller?command=english">EN</a>
    <a href="/controller?command=russian">RU</a>
</form>
</body>
</html>