<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'ru_RU'}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <meta charset="UTF-8">
    <title> City bike </title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link href="<c:url value="css/pagination.css"/>" rel="stylesheet"/>

</head>
<body>
<jsp:include page="parts/navigation.jsp"/>
<center class="graytext"><h3><fmt:message key="BIKE_PARKINGS"/></h3></center>
<br/>
<c:if test="${sessionScope.user.role.value eq 'ADMINISTRATOR' or sessionScope.user.role.value eq 'MANAGER'}">
    <div class="center-block"><a href="main?command=addParkingPage">
        <button class="btn center-block btn-success"><fmt:message key="ADD_PARKING"/></button>
    </a></div>
    <br/>
</c:if>
<table class="table table-hover">
    <thead>
    <tr>
        <th>№</th>
        <th><fmt:message key="ADRESS"/></th>
        <th><fmt:message key="SHOW"/></th>
        <c:if test="${sessionScope.user.role.value eq 'ADMINISTRATOR' or sessionScope.user.role.value eq 'MANAGER'}">
            <th colspan=2><fmt:message key="ACTION"/></th>
        </c:if>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${items.elementList}" var="parking">
        <tr>
            <td><c:out value="${parking.parkingId}"/></td>
            <td><c:out value="${parking.street}"/></td>
            <td><a class="btn btn-info"
                   href="main?command=showBikesInParkingPage&id=<c:out value="${parking.parkingId}"/>"><fmt:message
                    key="LIST_BIKES"/></a></td>
            <c:if test="${sessionScope.user.role.value eq 'ADMINISTRATOR' or sessionScope.user.role.value eq 'MANAGER'}">
                <td><a class="btn btn-success"
                       href="main?command=showEditParkingPage&id=<c:out value="${parking.parkingId}"/>"><fmt:message
                        key="UPDATE"/></a></td>
                <td><a class="btn btn-danger"
                       href="main?command=deleteParking&id=<c:out value="${parking.parkingId}"/>"><fmt:message
                        key="DELETE"/></a></td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%@ include file="parts/pagination.jsp" %>
<br/>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>