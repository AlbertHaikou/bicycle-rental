<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'en_US'}"/>
<fmt:setBundle basename="messages" />
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
        <jsp:include page="navigation.jsp"/>
        ﻿<div class="auth-window animated  fadeInDown" style="margin-top: 50px;">
            <div class="title"><fmt:message key="REGISTRATION"/></div>
            <form role="form" action="RegisterController" name="myForm"
                  onsubmit="return validateForm()" method="post">
                <div class="wrap">
                    <div class="form-inline">
                        <div class="form-group">
                            <label for="firstName" class="sr-only"></label>
                            <input type="text" class="form-control "
                                   name="firstName" placeholder="<fmt:message key="FIRST_NAME"/>"  
                                   rel="tooltp" title="<fmt:message key="ENTER_FIRST_NAME"/>">
                            <c:out value="${errormsg.firstName}" />
                        </div>
                        <div class="form-group">
                            <label for="lastName" class="sr-only"></label>
                            <input type="text" class="form-control "
                                   name="lastName" placeholder="<fmt:message key="LAST_NAME"/>" 
                                   rel="tooltp" title="<fmt:message key="ENTER_LAST_NAME"/>">
                            <c:out value="${errormsg.lastName}" />
                        </div>

                    </div>
                    <div class="form-group">
                        <label for="email"></label>
                        <input type="text" id="email"
                               class="form-control "
                               name="email" placeholder="<fmt:message key="EMAIL"/>"
                               rel="tooltp" title="<fmt:message key="ENTER_EMAIL"/>">
                        <c:out value="${errormsg.login}" />
                    </div>
                    <div class="form-group">
                        <label for="password"></label>
                        <input type="password"
                               class="form-control big "
                               id="password"
                               name="password" placeholder="<fmt:message key="PASSWORD"/>"
                               rel="tooltp" title='<fmt:message key="ENTER_PASSWORD"/>'>
                        <c:out value="${errormsg.password}" />
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
        <jsp:include page="footer.jsp"/>
    </body>
</html>
