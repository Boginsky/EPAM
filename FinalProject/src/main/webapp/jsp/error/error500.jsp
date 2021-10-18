<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <style>
        <%@include file="/static/css/error.css" %>
    </style>
</head>
<body>
<div class="error-wrapper">
    <div class="error-container error-500">
        <div class="error">
            <div class="error-title">
                Server Error
            </div>
            <div class="error-number">
                500
            </div>
            <div class="error-description">
                Ooops!! Something went wrong!
            </div>
            <div class="error-description" style="text-align: center">
                Exception: ${pageContext.exception.message}
            </div>
            <div class="error-or">
                <div class="or-line">></div>
                <div class="or"> <a style="text-align: center" href="./controller?command=cabinet">
                    <button class="button" data-toggle="tooltip" title="" data-original-title="HOME">HOME</button>
                </a></div>
            </div>
        </div>
    </div>
</div>
</body>
</html>