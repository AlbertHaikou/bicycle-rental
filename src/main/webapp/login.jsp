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
    <link rel="stylesheet" type="text/css" href="css/login-form.css">
</head>
<body style="margin-bottom: 0px;">
<jsp:include page="parts/navigation.jsp"/>
<center style="color:graytext;">
    <h3><c:out value="${successMsg}"/></h3></center>
<center style="color:graytext;">
    <h3><c:out value="${errorMsg}"/></h3></center>
<div class="auth-window animated fadeInDown">
    <div class="title"><fmt:message key="LOGIN"/></div>
    <form class="loginForm" id="loginForm" role="form" method="post" name="myForm" action="main?command=login">
        <fieldset id="inputs">
            <div ID="items">
                <span>
                    <label for="username">
                        <input id="username" type="text" autofocus required
                               class="form-control big"
                               pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
                               title="Use Latin letters, ._%+- and digits, then @, followed by Latin letters, symbols -. and numbers. Further . and after it domain of 2-4 Latin letters"
                               name="email" placeholder="<fmt:message key="EMAIL"/>">
                            <ul class="input-requirements">
                                <li>Need's to contain @ after letters</li>
                                <li>Needs to contain . after @ and letter</li>
                            </ul>
                        <c:out value="${error.login}"/>
                </label>
                </span>
                <span>
                <label for="password">
                            <input id="password" type="password" placeholder="<fmt:message key="PASSWORD"/>"
                                   required class="form-control big"
                                   pattern="(?=^.{6,}$)^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$"
                                   title="Min 1 UpperCase latin, 1 LowerCase latin and 1 Number, 6+ symbols"
                                   minlength="6"
                                   name="password">
                            <ul class="input-requirements">
                                <li>At least 6 characters long</li>
                                <li>Contains at least 1 number</li>
                                <li>Contains at least 1 lowercase letter</li>
                                <li>Contains at least 1 uppercase letter</li>
                            </ul>
                    <c:out value="${errormsg.password}"/>
                </label>
            </span>
            </div>
        </fieldset>
        <div class="auth-window__bottom">
            <div class="row">
                <div class="col-md-6">
                    <button type="submit" class="btn btn-primary btn-block btn-lg"
                            data-loading-text="Авторизация..."><fmt:message key="LOGIN"/></button>
                </div>
                <div class="col-md-6">
                    <button type="button" onclick="javascript:window.location='registration.jsp'" class="btn btn-default btn-block btn-lg">
                       <fmt:message key="REGISTRATION"/></button>
                </div>
            </div>
        </div>
    </form>
</div>
<jsp:include page="parts/footer.jsp"/>
<script src="js/login.js"></script>
</body>
</html>