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
<div class="bg">
    <%@include file="/includes/navbar.jsp" %>
    <div class="container">
        <br/>
        <div class="main-body">
            <div class="row gutters-sm">
                <div class="col-md-4">
                    <div class="d-flex flex-column align-items-center text-center">
                        <img class="item" src="./imageTransfer?imageUuid=${user.imageUrl}" alt="Card image cap">
                        <br/>
                        <%@include file="/includes/changePhoto.jsp" %>
                        <br/>
                        <button type="button" class="btn btn-dark btn-sm" data-toggle="modal"
                                data-target="#addAdmin">
                            <fmt:message key="label.addAdmin" bundle="${var}"/>
                        </button>
                        <%@include file="/includes/addAdmin.jsp" %>
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
                                    <%@include file="/includes/changeName.jsp" %>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0"><fmt:message key="label.email" bundle="${var}"/></h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${user.email}
                                    </td>
                                    <%@include file="/includes/changeEmail.jsp" %>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0"><fmt:message key="label.password" bundle="${var}"/></h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ***************
                                    <%@include file="/includes/changePassword.jsp" %>
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
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0"><fmt:message key="label.dateOfCreation" bundle="${var}"/></h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${user.userCreated}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <br/>
            <div id="accordion">
                <div class="card">
                    <div class="card-header" id="headingOne">
                        <h5 class="mb-0">
                            <button class="btn btn-link" data-toggle="collapse" data-target="#collapseOne"
                                    aria-expanded="true" aria-controls="collapseOne">
                                <fmt:message key="label.users" bundle="${var}"/>
                            </button>
                        </h5>
                    </div>
                    <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
                        <div class="card-body">
                            <table class="table table-light" style="text-align: center">
                                <thead>
                                <tr>
                                    <th style="width: 25%" scope="col"><fmt:message key="label.image"
                                                                                    bundle="${var}"/></th>
                                    <th style="width: 25%" scope="col"><fmt:message key="label.email"
                                                                                    bundle="${var}"/></th>
                                    <th style="width: 25%" scope="col"><fmt:message key="label.fullName"
                                                                                    bundle="${var}"/></th>
                                    <th style="width: 25%" scope="col"><fmt:message key="label.bonuses"
                                                                                    bundle="${var}"/></th>
                                    <th style="width: 25%" scope="col"><fmt:message key="label.userStatus"
                                                                                    bundle="${var}"/></th>
                                    <th style="width: 25%" scope="col"><fmt:message key="label.orders"
                                                                                    bundle="${var}"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="user" items="${listOfUsers}" varStatus="vs">
                                    <tr>
                                        <td><img src="./imageTransfer?imageUuid=${user.imageUrl}"
                                                 class="img-fluid img-thumbnail" alt="Image"/></td>
                                        <td><c:out value="${user.email}"/></td>
                                        <td><c:out value="${user.firstName} ${user.lastName}"/></td>
                                        <td><c:out value="${user.bonus}"/>
                                            <br/>
                                            <button type="button" class="btn btn-dark btn-sm" data-toggle="modal"
                                                    data-target="#addBonuses${vs.index}">
                                                <fmt:message key="label.topUp" bundle="${var}"/>
                                            </button>
                                        </td>
                                        <td><c:out value="${user.userStatus}"/>
                                            <br/>
                                            <c:choose>
                                                <c:when test="${user.userStatus == 'ACTIVE'}">
                                                    <a class="btn btn-sm btn-dark"
                                                       href="./controller?command=change_user_status&userId=${user.id}&userStatus=${user.userStatus}"><fmt:message
                                                            key="label.block" bundle="${var}"/></a>
                                                </c:when>
                                                <c:when test="${user.userStatus == 'BLOCKED'}">
                                                    <a class="btn btn-sm btn-dark"
                                                       href="./controller?command=change_user_status&userId=${user.id}&userStatus=${user.userStatus}"><fmt:message
                                                            key="label.unblock" bundle="${var}"/></a>
                                                </c:when>
                                            </c:choose>
                                            <br/>
                                            <br/>
                                            <a class="btn btn-sm btn-danger"
                                               href="./controller?command=remove_account&userId=${user.id}"><fmt:message
                                                    key="label.delete" bundle="${var}"/></a>
                                        </td>
                                        <td>
                                            <br/>
                                            <a class="btn btn-sm btn-dark"
                                               href="./controller?command=find_user_orders&userId=${user.id}"><fmt:message
                                                    key="label.details" bundle="${var}"/></a>
                                        </td>
                                    </tr>
                                    <%@include file="/includes/addBonuses.jsp" %>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
<%@include file="/includes/footer.jsp" %>
</html>
