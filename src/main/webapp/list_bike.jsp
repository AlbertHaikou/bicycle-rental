<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'en_US'}"/>
<fmt:setBundle basename="messages"/>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title> rent-bike </title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link href="<c:url value="css/pagination.css"/>" rel="stylesheet"/>
</head>
<body style="margin-bottom: 0px;">
<jsp:include page="parts/navigation.jsp"/>
<center style="color:graytext;"><h3><fmt:message key="BIKES"/></h3></center>
<center style="color:graytext;"><h4><c:out value="${bid}"/></h4></center>
<table class="table table-hover">
    <thead>
    <tr>
        <th>№</th>
        <th><fmt:message key="TYPE"/></th>
        <th><fmt:message key="MODEL"/></th>
        <th><fmt:message key="SIZE"/></th>
        <th><fmt:message key="STATUS"/></th>
        <th><fmt:message key="№_PARKING"/></th>
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
    <c:choose>
        <c:when test="${'true'.equalsIgnoreCase(param.available)}">
            <center>
                <a href="main?command=showBikes">
                    <button class="btn btn-mini"
                            type="button" style="border-top-width: 2px;padding-left: 0px;
    padding-right: 0px; padding-top: 0px; padding-bottom: 0px;border-bottom-width: 2px;
    border-left-width: 5px; border-right-width: 5px;
    color: navy;"><fmt:message key="SHOW_ALL_BIKES"/></button>
                </a>
            </center>
            </br>
        </c:when>
        <c:otherwise>
            <center>
                <a href="main?command=showBikes&available=true">
                    <button class="btn btn-mini"
                            type="button" style="border-top-width: 2px;padding-left: 0px;
            padding-right: 0px; padding-top: 0px; padding-bottom: 0px;border-bottom-width: 2px;
            border-left-width: 5px; border-right-width: 5px;color: navy;">
                        <fmt:message key="SHOW_AVALAIBLE_BIKES"/></button>
                </a>
            </center>
            </br>
        </c:otherwise>
    </c:choose>
    <c:forEach items="${items.elementList}" var="bike">
        <tr>
            <td><c:out value="${bike.bicycleId}"/></td>
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
            <input type="hidden" name="parkingId" value="<c:out value="${bike.parkingId}" />"/>
            <td><c:out value="${bike.parkingId}"/></td>
            <td><c:out value="${bike.price}"/></td>
            <c:if test="${sessionScope.user.role.value eq 'ADMINISTRATOR' or sessionScope.user.role.value eq 'MANAGER'}">
                <td><a href="main?command=showEditBikePage&id=<c:out value="${bike.bicycleId}" />"
                       class="btn btn-success"><fmt:message
                        key="UPDATE"/></a></td>
                <td><a class="btn btn-danger"
                       href="main?command=deleteBike&id=<c:out value="${bike.bicycleId}" />"><fmt:message
                        key="DELETE"/></a></td>
                <td><a class="btn btn-info"
                       href="main?command=repair&id=<c:out value="${bike.bicycleId}"/>"><fmt:message
                        key="BID"/></a></td>
            </c:if>
            <c:set var="CLIENT" value="CLIENT"/>
            <c:if test="${not empty sessionScope.user}">
                <c:choose>
                    <c:when test="${bike.isAvailable}">
                        <td>
                            <c:if test="${not user.banned}">
                                <c:if test="${empty userRentedBikeId}">
                                    <a class="btn btn-rent" href="main?command=rentBike&id=<c:out value="${bike.bicycleId}"/>"><fmt:message
                                            key="TO_RENT"/></a>
                                </c:if>
                            </c:if>
                        </td>
                    </c:when>
                    <c:otherwise>
                        <td>
                            <c:if test="${not empty userRentedBikeId && bike.bicycleId eq userRentedBikeId}">
                                <a class="btn btn-rent" href="main?command=returnBike&id=<c:out value="${bike.bicycleId}"/>"><fmt:message
                                        key="TO_RETURN"/></a>
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
<br/>
<c:if test="${sessionScope.user.role.value.equalsIgnoreCase(ADMINISTRATOR)}">
    <div class="center-block"><a href="main?command=addBikePage">
        <button class="center-block btn-add btn"><fmt:message key="ADD_BIKE"/></button>
    </a></div>
    <br/><br/>
</c:if>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>