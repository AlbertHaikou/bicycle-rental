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
                <center><h3><fmt:message key="${empty bike ?'ADDING':'EDITING'}"/></h3></center>
                <form method="post" action="main?command=${empty bike ? 'addBike' : 'editBike'}">
                    <div role="form">
                        <input type="hidden" name="id" class="form-control"
                               value="<c:out value="${bike.bicycleId}" />"/>
                        <div class="form-group float-label-control">
                            <label><fmt:message key="TYPE"/></label>
                            <input type="text" name="type" pattern="[a-zA-Z]{4,}" required
                                   class="form-control" placeholder="<fmt:message key="TYPE"/>"
                                   value="<c:out value="${bike.type}" />"/>
                        </div>
                        <div class="form-group float-label-control">
                            <label><fmt:message key="PRICE"/></label>
                            <input type="number" name="price" min="1" max="100" required class="form-control"
                                   placeholder="<fmt:message key="PRICE"/>"
                                   value="<c:out value="${bike.price}" />"/>
                        </div>
                        <div class="form-group float-label-control">
                            <label><fmt:message key="MODEL"/></label>
                            <input type="text" name="model" required class="form-control"
                                   placeholder="<fmt:message key="MODEL"/>"
                                   value="<c:out value="${bike.model}" />"/>
                        </div>
                        <div class="form-group float-label-control">
                            <label><fmt:message key="SIZE"/></label>
                            <input type="number" maxlength="2" name="size" class="form-control"
                                   placeholder="<fmt:message key="SIZE"/>"
                                   required value="<c:out value="${bike.size}"/>"/>
                        </div>

                        <div>
                            <label><fmt:message key="STATUS"/></label><br/>
                            <select class="input-sm" name="available">
                                <option value="free" }>
                                    <c:out value="free"/>
                                </option>
                                <option value="busy" }>
                                    <c:out value="busy"/>
                                </option>
                            </select>
                        </div>
                        <br/>
                        <div>
                            <label><fmt:message key="PARKING"/></label><br/>
                            <select class="input-sm" name="parkingId">
                                <c:forEach items="${parkings}" var="parking">
                                    <option value="${parking.parkingId}"  ${parking.parkingId == bike.parkingId ? 'selected' : ''}>
                                        <c:out value="${parking.street}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        </br>
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