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
    <script>
        function validateForm() {
            var firstName = document.forms["myForm"]["firstName"].value;
            var lastName = document.forms["myForm"]["lastName"].value;
            var email = document.forms["myForm"]["email"].value;
            var password = document.forms["myForm"]["password"].value;

            if (firstName == null || firstName.trim() == "") {
                alert("<fmt:message key="EMPTY_FIRST_NAME_ERROR_MESSAGE"/>");
                return false;
            }
            if (lastName == null || lastName.trim() == "") {
                alert("<fmt:message key="EMPTY_LAST_NAME_ERROR_MESSAGE"/>");
                return false;
            }
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
    <h3><c:out value="${errorMsg.email}"/></h3></center>
</h3></center>
<div class="auth-window animated  fadeInDown" style="margin-top: 50px;">
    <div class="title"><fmt:message key="REGISTRATION"/></div>
    <form role="form" action="main?command=register" name="myForm"
          onsubmit="return validateForm()" method="post">
        <div class="wrap">
            <div class="form-inline">
                <div class="form-group">
                    <label for="firstName" class="sr-only"></label>
                    <input type="text" class="form-control" id="firstName"
                           required
                           pattern="[A-Za-zа-яА-Я]{3,}"
                           title="3+ letters"
                           name="firstName"
                           placeholder="<fmt:message key="FIRST_NAME"/>">
                    <c:out value="${errormsg.firstName}"/>
                </div>
                <div class="form-group">
                    <label for="lastName" class="sr-only"></label>
                    <input type="text" class="form-control" id="lastName"
                           required
                           pattern="[A-Za-zа-яА-Я]{3,}"
                           title="3+ letters"
                           name="lastName"
                           placeholder="<fmt:message key="LAST_NAME"/>">
                    <c:out value="${errormsg.lastName}"/>
                </div>

            </div>
            <div class="form-group">
                <label for="email"></label>
                <input type="email" id="email"
                       class="form-control "
                       required
                       title="Use Latin letters, ._%+- and digits, then @, followed by Latin letters, symbols -. and numbers. Further . and after it domain of 2-4 Latin letters"
                       name="email" placeholder="<fmt:message key="EMAIL"/>"
                       pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$">
                <c:out value="${errormsg.login}"/>
            </div>
            <div class="form-group">
                <label for="phoneNumber"></label>
                <input type="number" id="phoneNumber"
                       class="form-control "
                       required
                       title="9 digits"
                       name="phoneNumber" placeholder="<fmt:message key="PHONE_NUMBER"/>"
                       min="111111111"
                       max="999999999">
                <c:out value="${errormsg.phoneNumber}"/>
            </div>
            <div class="form-group">
                <label for="password"></label>
                <input type="password"
                       class="form-control big "
                       id="password"
                       required
                       pattern="(?=^.{6,}$)^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$"
                       title="Min 1 UpperCase latin, 1 LowerCase latin and 1 Number, 6+ symbols"
                       name="password"
                       placeholder="<fmt:message key="PASSWORD"/>">
                <c:out value="${errormsg.password}"/>
            </div>
        </div>
        <div class="auth-window__bottom">
            <div class="row">
                <div class="col-md-12">
                    <button type="submit" class="btn btn-primary btn-lg"
                            data-loading-text="Регистрация..."><fmt:message key="REGISTRATION"/></button>
                </div>
            </div>
        </div>
    </form>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>
