<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'en_US'}"/>
<fmt:setBundle basename="messages"/>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title> rent-bike </title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/edit.css">
    <script src="js/jquery/jquery.js"></script>
    <script src="js/edit.js"></script>
</head>
<body style="margin-bottom: 0px;">
<header>
    <jsp:include page="parts/navigation.jsp"/>
</header>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class=" profile profile-content">
                <center style="color:graytext;"><h3><fmt:message key="${empty manager ? 'ADDING' : 'EDITING'}"/></h3>
                </center>
                <form method="post" action="main?command=${empty manager ? 'addManager' : 'editManager'}">
                    <div role="form">
                        <input type="hidden" name="id" required value="<c:out value="${manager.id}" />"/>
                        <div class="form-group float-label-control">
                            <label><fmt:message key="FIRST_NAME"/></label>
                            <input type="text" name="firstName" required
                                   placeholder="<fmt:message key="FIRST_NAME"/>"
                                   class="form-control" value="<c:out value="${manager.firstName}"/>"/>
                        </div>
                        <div class="form-group float-label-control">
                            <label><fmt:message key="LAST_NAME"/></label>
                            <input type="text" name="lastName" required
                                   placeholder="<fmt:message key="LAST_NAME"/>"
                                   class="form-control" value="<c:out value="${manager.lastName}"/>"/>
                        </div>
                        <div class="form-group float-label-control">
                            <label><fmt:message key="EMAIL"/></label>
                            <input type="email" name="email" required
                                   placeholder="<fmt:message key="EMAIL"/>"
                                   class="form-control" value="<c:out value="${manager.email}"/>"/>
                        </div>
                        <c:if test="${empty manager}">
                            <div class="form-group float-label-control">
                                <label><fmt:message key="PASSWORD"/></label>
                                <input type="text" name="password" class="form-control"
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