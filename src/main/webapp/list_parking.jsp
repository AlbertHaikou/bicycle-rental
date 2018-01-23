<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'en_US'}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <meta charset="UTF-8">
    <title> rent-bike </title>
    <link rel="stylesheet" href="css/bootstrap.css">

</head>
<body style="margin-bottom: 0px;">
<jsp:include page="parts/navigation.jsp"/>
<center style="color:graytext;"><h3><fmt:message key="BIKE_PARKINGS"/></h3></center>
<br/>
<table class="table table-hover">
    <thead>
    <tr>
        <th>№</th>
        <th><fmt:message key="STREET"/></th>
        <th><fmt:message key="SHOW"/></th>
        <c:set var="ADMINISTRATOR" value="ADMINISTRATOR"/>
        <c:if test="${sessionScope.user.role.value eq 'ADMINISTRATOR'}">
            <th colspan=2><fmt:message key="ACTION"/></th>
        </c:if>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${parkings}" var="parking">
        <tr>
            <td><c:out value="${parking.parkingId}"/></td>
            <td><c:out value="${parking.street}"/></td>
            <td><a class="btn btn-info
custom-width" href="main?command=showBikesInParkingPage&id=<c:out value="${parking.parkingId}"/>"><fmt:message
                    key="LIST_BIKES"/></a></td>
            <c:if test="${sessionScope.user.role.value eq 'ADMINISTRATOR'}">
                <td><a class="btn btn-success
custom-width" href="main?command=showEditParkingPage&id=<c:out value="${parking.parkingId}"/>"><fmt:message
                        key="UPDATE"/></a></td>
                <td><a class="btn btn-danger
custom-width" href="main?command=deleteParking&id=<c:out value="${parking.parkingId}"/>"><fmt:message
                        key="DELETE"/></a></td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br/>
<c:if test="${sessionScope.user.role.value=='ADMINISTRATOR'}">
    <div class="center-block"><a href="main?command=addParkingPage">
        <button class="center-block btn-add btn"><fmt:message key="ADD_PARKING"/></button>
    </a></div>
</c:if>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>