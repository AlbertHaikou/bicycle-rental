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
<body>
<jsp:include page="parts/navigation.jsp"/>
<center><h3><fmt:message key="MANAGER"/></h3></center>
<br/>
<c:if test="${sessionScope.user.role.value=='ADMINISTRATOR'}">
    <div class="center-block"><a href="main?command=showAddManagerPage">
        <button class="center-block btn btn-success"><fmt:message key="ADD_MANAGER"/></button>
    </a></div>
</c:if>
<br/>
<table class="table">
    <thead>
    <tr class="tab-pane">
        <th>№</th>
        <th><fmt:message key="FIRST_NAME"/></th>
        <th><fmt:message key="LAST_NAME"/></th>
        <th><fmt:message key="EMAIL"/></th>
        <c:set var="ADMINISTRATOR" value="ADMINISTRATOR"/>
        <c:if test="${sessionScope.user.role.value=='ADMINISTRATOR'}">
            <th colspan=2><fmt:message key="ACTION"/></th>
            <th><fmt:message key="CHANGE_ROLE"/></th>
        </c:if>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${items.elementList}" var="support">
        <tr>
            <td><c:out value="${support.id}"/></td>
            <td><c:out value="${support.firstName}"/></td>
            <td><c:out value="${support.lastName}"/></td>
            <td><c:out value="${support.email}"/></td>
            <c:if test="${sessionScope.user.role eq 'ADMINISTRATOR'}">
                <td><a href="main?command=showEditManagerPage&id=<c:out value="${support.id}"/>"
                       class="btn btn-success"><fmt:message
                        key="UPDATE"/></a></td>
                <td><a href="main?command=deleteManager&id=<c:out value="${support.id}"/>"
                       class="btn btn-danger"><fmt:message
                        key="DELETE"/></a></td>
                <td><a class="btn btn-info"
                       href="main?command=withdrawPrivileges&id=<c:out value="${support.id}" />">
                    <fmt:message key="WITHDRAW_PRIVILEGES"/></a></td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br/>
<%@ include file="parts/pagination.jsp" %>
<br/>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>