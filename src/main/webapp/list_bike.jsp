<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'ru_RU'}"/>
<fmt:setBundle basename="messages"/>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title> City bike </title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link href="<c:url value="css/pagination.css"/>" rel="stylesheet"/>
</head>
<body>
<jsp:include page="parts/navigation.jsp"/>
<center class="graytext"><h3><fmt:message key="BIKES"/></h3></center>
<center><h3 class="red"><c:out value="${errorMsg}"/></h3></center>
<c:choose>
    <c:when test="${'true'.equalsIgnoreCase(param.available)}">
        <c:choose>
            <c:when test="${null ne sessionScope.parkingScope}">
                <center>
                    <a href="main?command=showBikesInParkingPage&id=${sessionScope.parkingScope}">
                        <button class="btn btn-xs text-success" type="button">
                            <fmt:message key="SHOW_ALL_BIKES"/></button>
                    </a>
                </center>
            </c:when>
            <c:otherwise>
                <center>
                    <a href="main?command=showBikes">
                        <button class="btn btn-xs text-success" type="button">
                            <fmt:message key="SHOW_ALL_BIKES"/></button>
                    </a>
                </center>
            </c:otherwise>
        </c:choose>
        </br>
    </c:when>
    <c:otherwise>
        <c:choose>
            <c:when test="${null ne sessionScope.parkingScope}">
                <center>
                    <a href="main?command=showBikesInParkingPage&id=${sessionScope.parkingScope}&available=true">
                        <button class="btn btn-xs text-success" type="button">
                            <fmt:message key="SHOW_AVALAIBLE_BIKES"/></button>
                    </a>
                </center>
            </c:when>
            <c:otherwise>
                <center>
                    <a href="main?command=showBikes&available=true">
                        <button class="btn btn-xs text-success" type="button">
                            <fmt:message key="SHOW_AVALAIBLE_BIKES"/></button>
                    </a>
                </center>
            </c:otherwise>
        </c:choose>
        </br>
    </c:otherwise>
</c:choose>
<table class="table table-hover">
    <thead>
    <tr>
        <th></th>
        <th><fmt:message key="TYPE"/></th>
        <th><fmt:message key="MODEL"/></th>
        <th><fmt:message key="SIZE"/></th>
        <th><fmt:message key="STATUS"/></th>
        <th><fmt:message key="ADRESS"/></th>
        <th><fmt:message key="PRICE"/></th>
        <c:set var="USER" value="USER"/>
        <c:if test="${sessionScope.user.role.value eq 'ADMINISTRATOR' or sessionScope.user.role.value eq 'MANAGER'}">
            <th colspan=3 width="100"><fmt:message key="ACTION"/></th>
        </c:if>
        <c:if test="${not empty sessionScope.user}">
            <th width="50"><fmt:message key="RENT"/></th>
        </c:if>
        <c:set var="ADMINISTRATOR" value="ADMINISTRATOR"/>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${items.elementList}" var="bike">
        <tr>
            <td><img height="100" src="main?command=getImage&bikeId=${bike.bicycleId}"></td>
            <td><c:out value="${bike.type}"/></td>
            <td><c:out value="${bike.model}"/></td>
            <td><c:out value="${bike.size}"/></td>
            <c:choose>
                <c:when test="${'true'.equalsIgnoreCase(bike.isAvailable)}">
                    <td><fmt:message key="FREE"/></td>
                </c:when>
                <c:otherwise>
                    <td><fmt:message key="BUSY"/></td>
                </c:otherwise>
            </c:choose>
            <c:forEach items="${parkings}" var="parking">
                <c:if test="${bike.parkingId==parking.parkingId}">
            <td><c:out value="${parking.street}"/></td>
                </c:if>
            </c:forEach>
            <td><c:out value="${bike.price}"/></td>
            <c:if test="${sessionScope.user.role.value eq 'ADMINISTRATOR' or sessionScope.user.role.value eq 'MANAGER'}">
                <td><a href="main?command=showEditBikePage&id=<c:out value="${bike.bicycleId}" />"
                       class="btn btn-success"><fmt:message
                        key="UPDATE"/></a></td>
                <td><a class="btn btn-danger"
                       href="main?command=deleteBike&id=<c:out value="${bike.bicycleId}" />"><fmt:message
                        key="DELETE"/></a></td>

                    <td> <c:if test="${bike.isAvailable}"><a class="btn btn-info"
                           href="main?command=showAddRepairPage&id=<c:out value="${bike.bicycleId}"/>"><fmt:message
                            key="BID"/></a></c:if></td>

            </c:if>
            <c:if test="${not empty sessionScope.user}">
                <c:choose>
                    <c:when test="${bike.isAvailable}">
                        <td>
                            <c:if test="${not user.banned}">
                                <c:if test="${empty userRentedBikeId}">
                                    <a class="btn btn-rent"
                                       href="main?command=rentBike&id=<c:out value="${bike.bicycleId}"/>"><fmt:message
                                            key="TO_RENT"/></a>
                                </c:if>
                            </c:if>
                        </td>
                    </c:when>
                    <c:otherwise>
                        <td>
                            <c:if test="${not empty userRentedBikeId and bike.bicycleId eq userRentedBikeId}">
                                <form method="post"
                                      action="main?command=returnBike&id=<c:out value="${bike.bicycleId}"/>">
                                    <p>
                                        <button type="submit" class="btn btn-rent"><fmt:message
                                                key="TO_RETURN"/></button>
                                    </p>
                                    <select class="btn btn-xs" name="chosenParkingId">
                                        <c:forEach items="${parkings}" var="parking">
                                            <option value="${parking.parkingId}"  ${parking.parkingId eq bike.parkingId ? 'selected' : ''}>
                                                <c:out value="${parking.street}"/>
                                            </option>
                                        </c:forEach>
                                    </select></form>
                            </c:if>
                        </td>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%@ include file="parts/pagination.jsp" %>
<p>
    <c:if test="${sessionScope.user.role.value eq 'ADMINISTRATOR' or sessionScope.user.role.value eq 'MANAGER'}">
<div class="center-block">
    <a href="main?command=addBikePage">
        <button class="center-block btn-add btn"><fmt:message key="ADD_BIKE"/></button>
    </a>
</div>
</c:if>
</p>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>