<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${changeLanguage}"/>
<fmt:setBundle basename="pagecontent"/>

<body>

<div class="container-fluid">
    <div class="row content">
        <div class="col-sm-3 sidenav hidden-xs">
            <h2>LYUPAudio</h2>
            <h4><fmt:message key="label.admin"/></h4>
            <ul class="nav nav-pills nav-stacked">
                <li class="active"><a href="/controller?command=main"><fmt:message key="label.main"/></a></li>
                <li><a href="/controller?command=Search"><fmt:message key="label.audio"/></a></li>
                <li><a href="/controller?command=log out"><fmt:message key="label.logOut"/></a></li>
            </ul><br>
        </div>
        <br>

        <div class="col-sm-9">
            <div class="well">
                <h4><fmt:message key="label.welcome"/>, ${login}</h4>
                <p><fmt:message key="label.helloAdmin"/></p>
            </div>
        </div>
    </div>
</div>
</div>

</body>
</html>