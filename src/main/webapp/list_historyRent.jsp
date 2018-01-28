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
    <link href="<c:url value="css/pagination.css"/>" rel="stylesheet" />
</head>
<body style="margin-bottom: 0px;">
<jsp:include page="parts/navigation.jsp"/>
<main>
    <center style="color:graytext;"><h3><fmt:message key="FULL_RENTAL_HISTORY"/></h3></center>
    <br/>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>№</th>
            <th><fmt:message key="№_BIKE"/></th>
            <th><fmt:message key="PRICE"/></th>
            <th><fmt:message key="DATE_OF_RENT"/></th>
            <th><fmt:message key="DATE_OF_RETURN"/></th>
            <th><fmt:message key="TOTAL_PRICE"/></th>
            <th><fmt:message key="STATUS"/></th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${items.elementList}" var="rent">
            <tr>
                <td><c:out value="${rent.id}"/></td>
                <td><c:out value="${rent.bikeId}"/></td>
                <td><c:out value="${rent.price}"/></td>
                <td><fmt:formatDate type="both" value="${rent.fromDate}"/></td>
                <td><fmt:formatDate type="both" value="${rent.toDate}"/></td>
                <td><c:out value="${rent.totalPrice}"/></td>
                <c:choose>
                    <c:when test="${null eq (rent.totalPrice)}">
                        <td><fmt:message key="TAKEN"/></td>
                    </c:when>
                    <c:otherwise>
                        <td><fmt:message key="RETURNED"/></td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br/>
    <%@ include file="parts/pagination.jsp" %>
</main>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>