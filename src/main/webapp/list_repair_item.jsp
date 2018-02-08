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
<center class="graytext"><h3><fmt:message key="REQUEST_FOR_REPAIR"/></h3></center>
<table class="table">
    <thead>
    <tr>
        <th></th>
        <th><fmt:message key="№_REPAIR"/></th>
        <th><fmt:message key="№_BIKE"/></th>
        <th><fmt:message key="DESCRIPTION"/></th>
        <c:if test="${sessionScope.user.role.value eq 'MANAGER' or sessionScope.user.role.value eq 'ADMINISTRATOR'}">
            <th><fmt:message key="ACTION"/></th>
        </c:if>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${items.elementList}" var="item">
        <tr>
            <td><img height="100" src="main?command=getImage&bikeId=${item.bikeId}"></td>
            <td><c:out value="${item.itemId}"/></td>
            <td><c:out value="${item.bikeId}"/></td>
            <td><c:out value="${item.description}"/></td>
            <td><a class="btn btn-info" href="main?command=repairBike&id=<c:out value="${item.itemId}"/>">
                <fmt:message key="PERFORM_REPAIR"/></a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%@ include file="parts/pagination.jsp" %>
<br/>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>