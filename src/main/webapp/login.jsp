<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'ru_RU'}"/>
<fmt:setBundle basename="messages"/>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title> rent-bike </title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <script>
        function validateForm() {
            var email = document.forms["myForm"]["email"].value;
            var password = document.forms["myForm"]["password"].value;

            if (email == null || email.trim() == "") {
                alert("<fmt:message key="EMPTY_EMAIL_ERROR_MESSAGE"/>");
                return false;
            }
            if (password == null || password.trim() == "") {
                alert("<fmt:message key="EMPTY_PASSWORD_ERROR_MESSAGE"/>");
                return false;
            }
        }
    </script>
</head>
<body style="margin-bottom: 0px;">
<jsp:include page="parts/navigation.jsp"/>
<center style="color:graytext;">
    <h3><c:out value="${successMsg}"/></h3></center>
<center style="color:graytext;">
    <h3><c:out value="${errorMsg}"/></h3></center>

﻿
<div class="auth-window animated fadeInDown" style="margin-top: 10px;">
    <div class="title"><fmt:message key="LOGIN"/></div>
    <form role="form" method="post" name="myForm"
          onsubmit="return validateForm()" action="main?command=login">
        <div class="wrap">
            <div class="form-group">
                <label for="email"></label>
                <input type="email"
                       class="form-control big "
                       id="email"
                       required
                       title="Use Latin letters, ._%+- and digits, then @, followed by Latin letters, symbols -. and numbers. Further . and after it domain of 2-4 Latin letters"
                       name="email" placeholder="<fmt:message key="EMAIL"/>"
                       pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$">
                <c:out value="${error.login}"/>
            </div>
            <div class="form-group">
                <label for="password"></label>
                <input type="password"
                       class="form-control big "
                       id="password"
                       name="password"
                       required
                       pattern="(?=^.{6,}$)^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$"
                       title="Min 1 UpperCase latin, 1 LowerCase latin and 1 Number, 6+ symbols"
                       placeholder="<fmt:message key="PASSWORD"/>">
                <c:out value="${error.password}"/>
            </div>
        </div>
        <div class="auth-window__bottom">
            <div class="row">
                <div class="col-md-6">
                    <button type="submit" class="btn btn-primary btn-block btn-lg"
                            data-loading-text="Авторизация..."><fmt:message key="LOGIN"/></button>
                </div>
                <div class="col-md-6">
                    <a href="registration.jsp" class="btn btn-block btn-default">
                        <i class="fa fa-user text-muted"></i> &nbsp;<fmt:message key="REGISTRATION"/></a>
                </div>
            </div>
        </div>
    </form>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>

