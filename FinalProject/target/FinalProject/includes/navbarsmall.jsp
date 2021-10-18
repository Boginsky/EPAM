<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="col" style="text-align: center">
        <a class="navbar-brand" href="./controller?command=all_songs"><fmt:message key="label.songs" bundle="${var}"/> </a>
        <br/>
        <c:if test="${user.userRole == 'ADMIN'}">
        <a class="navbar-brand" href="./controller?command=add_song"><fmt:message key="label.add" bundle="${var}"/></a>
        </c:if>
    </div>
    <div class="col" style="text-align: center">
        <a class="navbar-brand small" href="./controller?command=all_albums&pageId=1"><fmt:message key="label.albums" bundle="${var}"/></a>
        <br/>
        <c:if test="${user.userRole == 'ADMIN'}">
            <button type="button" style="float: right" class="btn btn-dark btn-sm" data-toggle="modal" data-target="#addAlbum">
                <fmt:message key="label.add" bundle="${var}"/>
            </button>
            <%@include file="/includes/addAlbum.jsp" %>
        </c:if>
    </div>
    <div class="col" style="text-align: center">
        <a class="navbar-brand" href="./controller?command=all_authors&pageId=1"><fmt:message key="label.authors" bundle="${var}"/></a>
        <br/>
        <c:if test="${user.userRole == 'ADMIN'}">
            <button type="button" style="float: right" class="btn btn-dark btn-sm" data-toggle="modal" data-target="#addAuthor">
                <fmt:message key="label.add" bundle="${var}"/>
            </button>
            <%@include file="/includes/addAuthor.jsp" %>
        </c:if>
    </div>
</nav>