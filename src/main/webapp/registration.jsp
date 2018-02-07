<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'ru_RU'}"/>
<fmt:setBundle basename="messages"/>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title> rental-bicycle </title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="css/login-form.css">

</head>
<body style="margin-bottom: 0px;">
<jsp:include page="parts/navigation.jsp"/>

<center style="color:graytext;">
    <h3><c:out value="${errorMsg.email}"/></h3></center>
</h3></center>
<div class="auth-window animated  fadeInDown" style="margin-top: 50px;">
    <div class="title"><fmt:message key="REGISTRATION"/></div>
    <form class="loginForm" id="loginForm" role="form" method="post" name="myForm" action="main?command=register">
        <fieldset id="inputs">
            <div ID="items">
                <span>
                    <label for="username">
                        <input id="username" type="text" autofocus required
                               class="form-control"
                               pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
                               title="Use Latin letters, ._%+- and digits, then @, followed by Latin letters, symbols -. and numbers. Further . and after it domain of 2-4 Latin letters"
                               name="email" placeholder="<fmt:message key="EMAIL"/>">
                            <ul class="input-requirements">
                                <li>Need's to contain @ after letters</li>
                                <li>Needs to contain . after @ and letter</li>
                            </ul>
                </label>
                </span>
                <span>
                <label for="password">
                            <input id="password" type="password" placeholder="<fmt:message key="PASSWORD"/>"
                                   required class="form-control"
                                   pattern="(?=^.{6,}$)^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$"
                                   title="Min 1 UpperCase latin, 1 LowerCase latin and 1 Number, 6+ symbols"
                                   minlength="6"
                                   name="password">
                            <ul class="input-requirements">
                                <li>At least 6 characters long</li>
                                <li>Contains at least 1 number</li>
                                <li>Contains at least 1 lowercase letter</li>Ы
                                <li>Contains at least 1 uppercase letter</li>
                            </ul>
                </label>
            </span>
                <span>
                <label for="password_repeat">
                            <input id="password_repeat" type="password" placeholder="<fmt:message key="REPEAT_PASSWORD"/>"
                                   required class="form-control"
                                   pattern="(?=^.{6,}$)^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$"
                                   title="Min 1 UpperCase latin, 1 LowerCase latin and 1 Number, 6+ symbols"
                                   minlength="6"
                                   name="password">
                </label>
            </span>

                <span>
                <label for="firstName">
                            <input id="firstName" type="text" placeholder="<fmt:message key="FIRST_NAME"/>"
                                   required class="form-control big"
                                   pattern="[A-Za-zа-яА-Я]{3,}"
                                   title="3+ letters"
                                   name="firstName">
                </label>
                    <c:out value="${errormsg.firstName}"/>
            </span>
                <span>
                <label for="lastName">
                            <input id="lastName" type="text"
                                   placeholder="<fmt:message key="LAST_NAME"/>"
                                   required
                                   class="form-control big"
                                   pattern="[A-Za-zа-яА-Я]{3,}"
                                   title="3+ letters"
                                   name="lastName">
                </label>
                     <c:out value="${errormsg.lastName}"/>
            </span>

                <span>
                <label for="phoneNumber">
                            <input type="number" id="phoneNumber"
                                   class="form-control big"
                                   required
                                   title="9 digits"
                                   name="phoneNumber"
                                   placeholder="<fmt:message key="PHONE_NUMBER"/>"
                                   min="111111111"
                                   max="999999999">
                </label>
                    <c:out value="${errormsg.phoneNumber}"/>
            </span>

                <div class="auth-window__bottom">
                    <div class="row">
                        <div class="col-md-12">
                            <button type="submit" class="btn btn-primary btn-lg"
                                    data-loading-text="Registration..."><fmt:message key="REGISTRATION"/></button>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>
    </form>
</div>
<jsp:include page="parts/footer.jsp"/>
<script src="js/login.js"></script>
</body>
</html>
