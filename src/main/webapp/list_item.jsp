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
    <center style="color:graytext;"><h3><fmt:message key="REQUEST_FOR_REPAIR"/></h3></center>
    <table border=2>
        <thead>
            <tr>
                <th>№</th>
                <th><fmt:message key="№_BIKE"/></th>
                <th><fmt:message key="DESCRIPTION"/></th>
                <c:set var="support" value="support"/>
                <c:if test="${sessionScope.user.getRoles().contains(support)}"> 
                    <th colspan=2><fmt:message key="ACTION"/></th>
                    </c:if>
            </tr>
        </thead>
        <tbody>
            <c:set var="support" value="support"/>
            <c:if test="${sessionScope.user.role.value=='SUPPORT'}">

                <c:choose>
                    <c:when test="${'unperformeditem'.equalsIgnoreCase(param.action)}">
                    <center>
                        <a href="SupportItemController?action=listItem"><button class="btn btn-mini" 
                        type="button" style="border-top-width: 2px;padding-left: 0px;
                        padding-right: 0px; padding-top: 0px; padding-bottom: 0px;border-bottom-width: 2px;
                        border-left-width: 5px; border-right-width: 5px;
                        color: navy;"><fmt:message key="SHOW_ALL_BIDS"/></button></a>
                    </center></br>   
                </c:when>
                <c:otherwise>
                    <center>
                        <a href="SupportItemController?action=unperformeditem"><button class="btn btn-mini" 
                        type="button" style="border-top-width: 2px;padding-left: 0px;
                        padding-right: 0px; padding-top: 0px; padding-bottom: 0px;border-bottom-width: 2px;
                        border-left-width: 5px; border-right-width: 5px;color: navy;"><fmt:message key="SHOW_UNPERFORMED_BIDS"/></button></a>
                    </center></br>
                </c:otherwise>
            </c:choose>      
        </c:if>
        <c:forEach items="${items}" var="item">
            <tr>
                <td><c:out value="${item.id}" /></td>
                <td><c:out value="${item.bicycleId}" /></td>
                <td><c:out value="${item.description}" /></td>
                <c:choose>
                    <c:when test="${item.status==true}">
                        <td><fmt:message key="DONE"/></td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="SupportItemController?action=repair&id=<c:out value="${item.bicycleId}"/>"><fmt:message key="PERFORM_REPAIR"/></a></td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
    </tbody>
</table>
<br/>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>