<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
                <center class="graytext"><h3><fmt:message key="${empty manager ? 'ADDING' : 'EDITING'}"/></h3>
                </center>
                <form method="post" action="main?command=${empty manager ? 'addManager' : 'editManager'}">
                    <div role="form">
                        <input type="hidden" name="id" required value="<c:out value="${manager.id}" />"/>
                        <input type="hidden" name="currentEmail" required value="<c:out value="${manager.email}" />"/>
                        <div class="form-group float-label-control">
                            <label><fmt:message key="FIRST_NAME"/></label>
                            <input type="text" name="firstName" required
                                   pattern="[A-Za-zа-яА-Я]{3,}"
                                   title="3+ letters"
                                   placeholder="<fmt:message key="FIRST_NAME"/>"
                                   class="form-control" value="<c:out value="${manager.firstName}"/>"/>
                        </div>
                        <div class="form-group float-label-control">
                            <label><fmt:message key="LAST_NAME"/></label>
                            <input type="text" name="lastName" required
                                   placeholder="<fmt:message key="LAST_NAME"/>"
                                   pattern="[A-Za-zа-яА-Я]{3,}"
                                   title="3+ letters"
                                   class="form-control" value="<c:out value="${manager.lastName}"/>"/>
                        </div>
                        <div class="form-group float-label-control">
                            <label><fmt:message key="EMAIL"/></label>
                            <input type="email" name="email" required
                                   placeholder="<fmt:message key="EMAIL"/>"
                                   title="Use Latin letters, ._%+- and digits, then @, followed by Latin letters, symbols -. and numbers. Further . and after it domain of 2-4 Latin letters"
                                   pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
                                   class="form-control" value="<c:out value="${manager.email}"/>"/>
                        </div>
                        <c:if test="${empty manager}">
                            <div class="form-group float-label-control">
                                <label><fmt:message key="PHONE_NUMBER"/></label>
                                <input type="number" id="phoneNumber"
                                       class="form-control big"
                                       required
                                       title="9 digits"
                                       name="phoneNumber"
                                       placeholder="<fmt:message key="PHONE_NUMBER"/>"
                                       min="111111111"
                                       max="999999999"
                                       class="form-control"/>
                            </div>
                            <div class="form-group float-label-control">
                                <label><fmt:message key="PASSWORD"/></label>
                                <input type="text" name="password" class="form-control"
                                       pattern="(?=^.{6,}$)^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$"
                                       title="Min 1 UpperCase latin, 1 LowerCase latin and 1 Number, 6+ symbols"
                                       placeholder="<fmt:message key="PASSWORD"/>"
                                       required value="<c:out value="${manager.password}"/>"/>
                            </div>
                        </c:if>
                        <br/>
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

<jsp:include page="parts/footer.jsp"/>
</body>
</html>