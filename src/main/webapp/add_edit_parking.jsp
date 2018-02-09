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
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class=" profile profile-content">
                <center><h3><fmt:message key="${empty parking ? 'ADD_NEW_PARKING' : 'EDITING'}"/></h3></center>
                <form method="post" action="main?command=${empty parking ? 'addParking' : 'editParking'}">
                    <div role="form">
                        <input type="hidden" name="id" class="form-control"
                               value="<c:out value="${parking.parkingId}" />"/>
                        <div class="form-group float-label-control">
                            <label><fmt:message key="ADRESS"/></label>
                            <input type="text" required name="street"
                                   pattern="[a-zA-Zа-яА-ЯёЁ, 0-9]{4,}" class="form-control"
                                   title="4+ symbols"
                                   placeholder="<fmt:message key="ADRESS"/>"
                                   value="<c:out value="${parking.street}"/>"/>
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
<jsp:include page="parts/footer.jsp"/>
</body>
</html>