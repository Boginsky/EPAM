<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="./controller?command=main"><fmt:message key="label.main" bundle="${var}"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item"><a class="nav-link" href=""><fmt:message key="label.cart" bundle="${var}"/></a>
            </li>
            <li class="nav-item"><a class="nav-link" href=""><fmt:message key="label.orders" bundle="${var}"/></a>
            </li>
            <li class="nav-item"><a class="nav-link" href="./controller?command=log out"><fmt:message key="label.logout" bundle="${var}"/></a>
            <li class="nav-item"><a class="nav-link" href="./controller?command=login"><fmt:message key="label.signIn" bundle="${var}"/></a>
            <li class="nav-item"><a class="nav-link" href="./controller?command=cabinet"><fmt:message key="label.cabinet" bundle="${var}"/></a>
            </li>
        </ul>
    </div>
</nav>