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
<%@include file="/includes/navbar.jsp" %>
<div class="container">
    <br/>
    <div class="main-body">
        <div class="row gutters-sm">
            <div class="col-md-4">
                <div class="d-flex flex-column align-items-center text-center">
                    <img class="item" src="./static/image/2.jpg" alt="Card image cap">
                    <%--                            <form>--%>
                    <%--                                <div class="form-group">--%>
                    <%--                                    <input type="file" class="form-control-file" id="exampleFormControlFile1">--%>
                    <%--                                </div>--%>
                    <%--                            </form>--%>
                </div>
            </div>
            <div class="col-md-8">
                <div class="card mb-3">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="label.fullName" bundle="${var}"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${user.firstName} ${user.lastName}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="label.email" bundle="${var}"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${user.email}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="label.password" bundle="${var}"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                *********
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="label.role" bundle="${var}"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${user.userRole}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <br/>
        <table class="table table-striped table-responsive-md btn-table">
            <tbody>
            <tr>
                <td style="width: 25%">
                    <button type="button" class="btn btn-primary btn-lg btn-block">Добавить песню</button>
                </td>
                <td style="width: 25%">
                    <button type="button" class="btn btn-primary btn-lg btn-block">Добавить альбом</button>
                </td>
                <td style="width: 25%">
                    <button type="button" class="btn btn-primary btn-lg btn-block">Добавить жанр</button>
                </td>
            </tr>
            <tr>
                <td style="width: 25%">
                    <button type="button" class="btn btn-primary btn-lg btn-block">Удалить песню</button>
                </td>
                <td style="width: 25%">
                    <button type="button" class="btn btn-primary btn-lg btn-block">Удалить альбом</button>
                </td>
                <td style="width: 25%">
                    <button type="button" class="btn btn-primary btn-lg btn-block">Удалить жанр</button>
                </td>
            </tr>
            <tr>
                <td style="width: 25%">
                    <button type="button" class="btn btn-primary btn-lg btn-block">Заблокировать юзера</button>
                </td>
                <td style="width: 25%">
                    <button type="button" class="btn btn-primary btn-lg btn-block">Разблокировать юзера</button>
                </td>
                <td style="width: 25%">
                    <button type="button" class="btn btn-primary btn-lg btn-block">Назначить скидку юзеру</button>
                </td>
            </tr>
            <tr>
                <td style="width: 25%">
                    <button type="button" class="btn btn-primary btn-lg btn-block">Редактировать комментарии</button>
                </td>
                <td style="width: 25%">
                    <button type="button" class="btn btn-primary btn-lg btn-block">Посмотреть все заказы</button>
                </td>
                <td style="width: 25%">
                    <button type="button" class="btn btn-primary btn-lg btn-block">Редактировать комментарии</button>
                </td>
            </tr>
            <tr>
                <td style="width: 25%">
                    <button type="button" class="btn btn-primary btn-lg btn-block">Изменить пароль</button>
                </td>
            </tr>
            </tbody>
        </table>
        <%@include file="/includes/footer.jsp" %>
</body>
</html>
