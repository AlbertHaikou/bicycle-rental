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
    <center style="color:graytext;"><h3><fmt:message key="RENTAL_OF_HISTORY"/></h3></center>
    <br/>
    <table border=2>
        <thead>
            <tr>
                <th>№</th>
                <th><fmt:message key="№_BIKE"/></th>
                <th><fmt:message key="DATE_OF_RENT"/></th>
                <th><fmt:message key="STATUS"/></th>

            </tr>
        </thead>
        <tbody> 
            <c:forEach items="${rents}" var="rent">
                <tr>
                    <td><c:out value="${rent.id}" /></td>
                    <td><c:out value="${rent.bicycleId}" /></td>
                    <td><fmt:formatDate type="both" value="${rent.date}" /></td>
                    <c:choose>
                        <c:when test="${'true'.equalsIgnoreCase(rent.status)}">
                            <td><fmt:message key="RETURNED"/></td>
                        </c:when>
                        <c:otherwise>
                            <td><fmt:message key="TAKEN"/></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br/>
    <jsp:include page="footer.jsp"/>
</body>
</html>