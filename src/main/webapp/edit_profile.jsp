<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'ru_RU'}"/>
<fmt:setBundle basename="messages"/>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title> City bike </title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/edit.css">
    <script src="js/jquery/jquery.js"></script>
    <script src="js/edit.js"></script>
</head>
<body>
<header>
    <jsp:include page="parts/navigation.jsp"/>
</header>
<center><h3><c:out value="${errorMsg}"/></h3></center>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class=" profile profile-content">
                <center><h3><fmt:message key="EDITING"/></h3></center>
                <form method="post" action="main?command=updateProfile">
                    <div role="form">
                        <input type="hidden" name="id" class="form-control"
                               value="<c:out value="${profile.id}" />"/>
                        <input type="hidden" name="currentEmail" required value="<c:out value="${profile.email}" />"/>
                        <div class="form-group float-label-control">
                            <label><fmt:message key="FIRST_NAME"/></label>
                            <input type="text" name="firstName"
                                   required
                                   class="form-control"
                                   pattern="[A-Za-zа-яА-Я]{3,}"
                                   placeholder="First name"
                                   title="3+ letters"
                                   value="<c:out value="${profile.firstName}" />"/>
                        </div>
                        <div class="form-group float-label-control">
                            <label><fmt:message key="LAST_NAME"/></label>
                            <input type="text" name="lastName"
                                   class="form-control"
                                   placeholder="Last name"
                                   required
                                   pattern="[A-Za-zа-яА-Я]{3,}"
                                   title="3+ letters"
                                   value="<c:out value="${profile.lastName}" />"/>
                        </div>
                        <div class="form-group float-label-control">
                            <label><fmt:message key="EMAIL"/></label>
                            <input type="email" name="email"
                                   class="form-control"
                                   placeholder="Email"
                                   required
                                   title="Use Latin letters, ._%+- and digits, then @, followed by Latin letters, symbols -. and numbers. Further . and after it domain of 2-4 Latin letters"
                                   name="email" placeholder="<fmt:message key="EMAIL"/>"
                                   pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
                                   value="<c:out value="${profile.email}" />"/>
                        </div>
                    </div>
                    <center>
                        <button class="btn btn-primary btn-success btn-block btn-lg"
                                type="submit"><fmt:message key="SAVE"/></button>
                    </center>
                </form>
            </div>
        </div>
    </div>
</div>
<footer>
    <jsp:include page="parts/footer.jsp"/>
</footer>
</body>
</html>