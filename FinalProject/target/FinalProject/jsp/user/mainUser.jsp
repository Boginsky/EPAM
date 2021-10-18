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
                        <img src="./imageTransfer?imageUuid=${user.imageUrl}" class="imageContainer" width="240px"
                             height="240px"/>
                        <br/>
                        <%@include file="/includes/changePhoto.jsp" %>
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
                                    <h6 class="mb-0"><fmt:message key="label.balance" bundle="${var}"/></h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${user.balance}$ + ${user.bonus} <fmt:message key="label.bonus" bundle="${var}"/>
                                    <%@include file="/includes/changeBalance.jsp" %>
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
                                <fmt:message key="label.orders" bundle="${var}"/>
                            </button>
                        </h5>
                    </div>
                    <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
                        <div class="card-body">
                            <table class="table table-light">
                                <thead>
                                <tr>
                                    <th style="width: 25%" scope="col"><fmt:message key="label.orderNumber"
                                                                                    bundle="${var}"/></th>
                                    <th style="width: 25%" scope="col"><fmt:message key="label.orderDateOfCreation"
                                                                                    bundle="${var}"/></th>
                                    <th style="width: 25%" scope="col"><fmt:message key="label.orderStatus"
                                                                                    bundle="${var}"/></th>
                                    <th style="width: 25%" scope="col"><fmt:message key="label.totalPrice"
                                                                                    bundle="${var}"/></th>
                                    <th style="width: 25%" scope="col"></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="order" items="${listOfOrders}">
                                    <tr>
                                        <td><c:out value="${order.id}"/></td>
                                        <td><c:out value="${order.dateOfCreation}"/></td>
                                        <td><c:out value="${order.orderStatus}"/></td>
                                        <td><c:out value="${order.totalPrice}"/></td>
                                        <td>
                                            <a class="btn btn-sm btn-dark"
                                               href="./controller?command=find_order&orderId=${order.id}"><fmt:message
                                                    key="label.details" bundle="${var}"/></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="card">
                    <div class="card-header" id="headingTwo">
                        <h5 class="mb-0">
                            <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwo"
                                    aria-expanded="false" aria-controls="collapseTwo">
                                <fmt:message key="label.comments" bundle="${var}"/>
                            </button>
                        </h5>
                    </div>
                    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
                        <div class="card-body">
                            <table class="table table-light">
                                <thead>
                                <tr>
                                    <th style="width: 25%" scope="col"><fmt:message key="label.commentNumber"
                                                                                    bundle="${var}"/></th>
                                    <th style="width: 25%" scope="col"><fmt:message key="label.commentMessage"
                                                                                    bundle="${var}"/></th>
                                    <th style="width: 25%" scope="col"><fmt:message key="label.album"
                                                                                    bundle="${var}"/></th>

                                    <th style="width: 25%" scope="col"></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="comment" items="${listOfComments}" varStatus="vs">
                                    <tr>
                                        <td><c:out value="${comment.id}"/></td>
                                        <td><c:out value="${comment.commentMessage}"/></td>
                                        <td><c:out value="${comment.albumName}"/></td>
                                        <td style="text-align: right">
                                            <form action="./controller" name="command" method="post">
                                                <input type="hidden" name="command" value="remove_comment"/>
                                                <input type="hidden" name="commentId" value="${comment.id}"/>
                                                <input type="hidden" name="commentAlbumId" value="${comment.albumId}"/>
                                                <button type="submit" class="btn btn-danger btn-sm"><fmt:message
                                                        key="label.removeComment"
                                                        bundle="${var}"/></button>
                                                <button type="button" class="btn btn-dark btn-sm" data-toggle="modal"
                                                        data-target="#changeComment${vs.index}">
                                                    <fmt:message key="label.change" bundle="${var}"/>
                                                </button>
                                            </form>
                                        </td>
                                        <%@include file="/includes/changeComment.jsp" %>
                                    </tr>
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
