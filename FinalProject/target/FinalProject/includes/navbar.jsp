<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="./controller?command=all songs"><fmt:message key="label.main" bundle="${var}"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto">
            <c:choose>
                <c:when test="${user.userRole == 'ADMIN'}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button"
                           aria-haspopup="true" aria-expanded="false">${user.firstName} ${user.lastName}</a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="#"><fmt:message key="label.change" bundle="${var}"/></a>
                            <a class="dropdown-item" href="./controller?command=cabinet"><fmt:message
                                    key="label.cabinet"
                                    bundle="${var}"/></a>
                            <div role="separator" class="dropdown-divider"></div>
                            <a class="dropdown-item" href="./controller?command=log out"><fmt:message key="label.logout"
                                                                                                      bundle="${var}"/></a>
                        </div>
                    </li>
                </c:when>
                <c:when test="${user.userRole == 'USER'}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button"
                           aria-haspopup="true" aria-expanded="false">${user.firstName} ${user.lastName}</a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="./controller?command=to cart"><fmt:message key="label.cart"
                                                                                                      bundle="${var}"/></a>
                            <a class="dropdown-item" href="#"><fmt:message key="label.orders"
                                                                           bundle="${var}"/></a>
                            <a class="dropdown-item" href="#"><fmt:message key="label.change" bundle="${var}"/></a>

                            <a class="dropdown-item" href="./controller?command=cabinet"><fmt:message
                                    key="label.cabinet"
                                    bundle="${var}"/></a>
                            <div role="separator" class="dropdown-divider"></div>
                            <a class="dropdown-item" href="./controller?command=log out"><fmt:message key="label.logout"
                                                                                                      bundle="${var}"/></a>
                        </div>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item"><a class="nav-link" href="./controller?command=sign up"><fmt:message
                            key="label.signUp"
                            bundle="${var}"/></a>
                    </li>
                    <li class="nav-item"><a class="nav-link" href="./controller?command=login"><fmt:message
                            key="label.signIn"
                            bundle="${var}"/></a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>