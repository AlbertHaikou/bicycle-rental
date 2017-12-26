<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'en_US'}"/>
<fmt:setBundle basename="messages" />
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title> rent-bike </title>
    <link rel="stylesheet" href="css/bootstrap.css">

</head>
<body style="margin-bottom: 0px;">
<jsp:include page="navigation.jsp"/>
<center style="color:graytext;"><h3><fmt:message key="MY_PROFILE"/></h3></center>
<br/>
<table border=2>
    <thead>
    <tr>
        <th>№</th>
        <th><fmt:message key="FIRST_NAME"/></th>
        <th><fmt:message key="LAST_NAME"/></th>
        <th><fmt:message key="EMAIL"/></th>
        <th><fmt:message key="PASSWORD"/></th>
        <c:set var="client" value="client"/>
        <c:if test="${sessionScope.role.value=='CLIENT'}">
            <th><fmt:message key="ACTION"/></th>
        </c:if>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td><c:out value="${profile.id}" /></td>
        <td><c:out value="${profile.firstName}" /></td>
        <td><c:out value="${profile.lastName}" /></td>
        <td><c:out value="${profile.email}" /></td>
        <td><c:out value="${profile.password}" /></td>
        <c:if test="${sessionScope.user.role.value=='CLIENT'}">
            <td><a href="ProfileController?action=edit&id=<c:out value="${profile.id}"/>">
                <fmt:message key="UPDATE"/></a></td>
        </c:if>
    </tr>
    </tbody>
</table>
<br/>
<jsp:include page="footer.jsp"/>
</body>
</html>