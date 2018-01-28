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
    <link href="<c:url value="css/pagination.css"/>" rel="stylesheet" />
</head>
<body style="margin-bottom: 0px;">
<jsp:include page="parts/navigation.jsp"/>
<center style="color:graytext;"><h3><fmt:message key="CLIENTS"/></h3></center>
<br/>
<table class="table">
    <thead>
    <tr>
        <th>№</th>
        <th><fmt:message key="FIRST_NAME"/></th>
        <th><fmt:message key="LAST_NAME"/></th>
        <th><fmt:message key="EMAIL"/></th>
        <th><fmt:message key="BALANCE"/></th>
        <th><fmt:message key="ACTION"/></th>
        <c:if test="${sessionScope.user.role.value eq 'ADMINISTRATOR'}">
            <th><fmt:message key="CHANGE_ROLE"/></th>
        </c:if>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${items.elementList}" var="user">
        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.firstName}"/></td>
            <td><c:out value="${user.lastName}"/></td>
            <td><c:out value="${user.email}"/></td>
            <td><c:out value="${user.balance}"/></td>
            <c:choose>
                <c:when test="${user.banned}">
                    <td><a class="btn btn-success custom-width"
                           href="main?command=unbanUser&id=<c:out value="${user.id}"/>"><fmt:message key="UNBAN"/></a>
                    </td>
                </c:when>
                <c:otherwise>
                    <td><a class="btn btn-danger custom-width"
                           href="main?command=banUser&id=<c:out value="${user.id}"/>"><fmt:message key="BAN"/></a></td>
                </c:otherwise>
            </c:choose>
            <c:if test="${sessionScope.user.role.value eq 'ADMINISTRATOR'}">
                <td><a class="btn btn-info custom-width"
                       href="main?command=appointAsManager&id=<c:out value="${user.id}"/>">
                    <fmt:message key="APPOINT_AS_MANAGER"/></a></td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%@ include file="parts/pagination.jsp" %>
<br/>
<footer>
    <jsp:include page="parts/footer.jsp"/>
</footer>
</body>
</html>