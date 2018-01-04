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
        <jsp:include page="parts/navigation.jsp"/>
           <center style="color:graytext;"><h3><fmt:message key="MANAGER"/></h3></center>
        <br/>
        <table border=2>
            <thead>
                <tr>
                    <th>№</th>
                     <th><fmt:message key="FIRST_NAME"/></th>
                    <th><fmt:message key="LAST_NAME"/></th>
                    <th><fmt:message key="EMAIL"/></th>
                    <th><fmt:message key="PASSWORD"/></th>
                        <c:set var="ADMINISTRATOR" value="ADMINISTRATOR"/>
                        <c:if test="${sessionScope.user.role.value=='ADMINISTRATOR'}">
                         <th colspan=2><fmt:message key="ACTION"/></th>
                        </c:if>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${supports}" var="support">
                    <tr>
                        <td><c:out value="${support.id}"/></td>
                        <td><c:out value="${support.firstName}"/></td>
                        <td><c:out value="${support.lastName}"/></td>
                        <td><c:out value="${support.email}"/></td>
                        <td><c:out value="${support.password}"/></td>
                        <c:if test="${sessionScope.user.getRole().getValue().equalsIgnoreCase(ADMINISTRATOR)}">
                            <td><a href="main?command=showEditManagerPage&id=<c:out value="${support.id}"/>"><fmt:message key="UPDATE"/></a></td>
                            <td><a href="main?command=deleteManager&id=<c:out value="${support.id}"/>"><fmt:message key="DELETE"/></a></td>
                        </c:if>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br/>
        <c:if test="${sessionScope.user.role.value=='ADMINISTRATOR'}">
        <center><a href="main?command=showAddManagerPage"><button><fmt:message key="ADD_MANAGER"/></button></a></center>
        </c:if>
        <jsp:include page="parts/footer.jsp"/>
</body>
</html>