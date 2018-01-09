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

</head>
<body style="margin-bottom: 0px;">
<jsp:include page="parts/navigation.jsp"/>
<center style="color:graytext;"><h3><fmt:message key="MY_PROFILE"/></h3></center>
<br/>
<table border=2>
    <thead>
    <tr>
        <th>№</th>
        <th><fmt:message key="FIRST_NAME"/></th>
        <th><fmt:message key="LAST_NAME"/></th>
        <th><fmt:message key="EMAIL"/></th>
        <th><fmt:message key="BALANCE"/></th>
        <c:set var="profile" value="profile"/>
        <c:if test="${not empty sessionScope.user.role}">
            <th><fmt:message key="ACTION"/></th>
        </c:if>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td><c:out value="${requestScope.profile.id}"/></td>
        <td><c:out value="${requestScope.profile.firstName}"/></td>
        <td><c:out value="${requestScope.profile.lastName}"/></td>
        <td><c:out value="${requestScope.profile.email}"/></td>
        <td><c:out value="${requestScope.profile.balance}"/></td>
        <c:if test="${not empty sessionScope.user.role}">
            <td><a href="main?command=showEditProfilePage&id=<c:out value="${requestScope.profile.id}"/>">
                <fmt:message key="UPDATE"/></a></td>
        </c:if>
    </tr>
    </tbody>
</table>
<br/>
<div class="center-block">
    <a href="main?command=showBalanceManagePage">
        <button type="button" class="btn btn-success center-block"><fmt:message key="BALANCE_MANAGE"/></button>
    </a>
</div>
<br/>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>