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
        <center style="color:graytext;"><h3><fmt:message key="BIKES"/></h3></center>
         <center style="color:graytext;"><h4> <c:out value="${bid}" /></h4></center>
              
        <table border=2>
            <thead>
                <tr>
                    <th>№</th>
                    <th><fmt:message key="TYPE"/></th>
                    <th><fmt:message key="MODEL"/></th>
                    <th><fmt:message key="SIZE"/></th>
                    <th><fmt:message key="STATUS"/></th>
                    <th><fmt:message key="№_PARKING"/></th>
                        <c:set var="client" value="client"/>
                        <c:if test="${sessionScope.user.getRoles().contains(client)}"> 
                        <th><fmt:message key="RENT"/></th>
                        <th><fmt:message key="REPAIRS"/></th>
                        </c:if>
                        <c:set var="admin" value="admin"/>
                        <c:if test="${sessionScope.user.getRoles().contains(admin)}"> 
                        <th colspan=2><fmt:message key="ACTION"/></th>
                        </c:if>
                </tr>
            </thead>
            <tbody>   
                <c:choose>
                    <c:when test="${'isAvailable'.equalsIgnoreCase(param.action)}">
                    <center>
                        <a href="BikeController?action=listBike"><button class="btn btn-mini" 
     type="button" style="border-top-width: 2px;padding-left: 0px;
    padding-right: 0px; padding-top: 0px; padding-bottom: 0px;border-bottom-width: 2px;
    border-left-width: 5px; border-right-width: 5px;
    color: navy;"><fmt:message key="SHOW_ALL_BIKES"/></button></a>
                    </center></br>   
                </c:when>
                <c:otherwise>
                    <center>
                        <a href="BikeController?action=isAvailable"><button class="btn btn-mini"
            type="button" style="border-top-width: 2px;padding-left: 0px;
            padding-right: 0px; padding-top: 0px; padding-bottom: 0px;border-bottom-width: 2px;
            border-left-width: 5px; border-right-width: 5px;color: navy;">
                                <fmt:message key="SHOW_AVALAIBLE_BIKES"/></button></a>
                    </center></br>
                </c:otherwise>
            </c:choose>
            <c:forEach items="${bikes}" var="bike">
                <tr>
                    <td><c:out value="${bike.bicycleId}" /></td>
                    <td><c:out value="${bike.type}" /></td>
                    <td><c:out value="${bike.model}" /></td>
                    <td><c:out value="${bike.size}" /></td>
                    <c:choose>
                        <c:when test="${'true'.equalsIgnoreCase(bike.isAvailable)}">
                            <td><fmt:message key="FREE"/></td>
                        </c:when>
                        <c:otherwise>
                            <td><fmt:message key="BUSY"/></td>
                        </c:otherwise>
                    </c:choose>
                              <input type="hidden" name="parkingId" value="<c:out value="${bike.parkingId}" />" /> 
                    <td><c:out value="${bike.parkingId}" /></td>
                    <c:if test="${sessionScope.user.getRoles().contains(admin)}"> 
                        <td><a href="BikeController?action=edit&id=<c:out value="${bike.bicycleId}"/>"><fmt:message key="UPDATE"/></a></td>
                        <td><a href="BikeController?action=delete&id=<c:out value="${bike.bicycleId}"/>"><fmt:message key="DELETE"/></a></td>
                    </c:if>
                    <c:set var="client" value="client"/>
                    <c:if test="${sessionScope.user.getRoles().contains(client)}"> 
                        <c:choose>
                            <c:when test="${bike.isAvailable}">
                                <td>
                                    <c:if test="${empty userRentedBikeId}">
                                        <a href="BikeController?action=rentbike&id=<c:out value="${bike.bicycleId}"/>"><fmt:message key="TO_RENT"/></a>
                                    </c:if>
                                </td>
                                <td><a href="SupportItemController?action=add&id=<c:out value="${bike.bicycleId}"/>"><fmt:message key="BID"/></a></td>
                            </c:when>
                            <c:otherwise>
                                <td>
                                    <c:if test="${not empty userRentedBikeId && bike.bicycleId == userRentedBikeId}">
                                        <a href="BikeController?action=returnbike&id=<c:out value="${bike.bicycleId}"/>"><fmt:message key="TO_RETURN"/></a>
                                    </c:if>
                                </td>                                    
                                <td></td>   
                            </c:otherwise>
                        </c:choose>
                    </c:if>  
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br/>
    <c:if test="${sessionScope.user.getRoles().contains(admin)}"> 
    <center><a href="BikeController?action=add"><button><fmt:message key="ADD_BIKE"/></button></a>
    </center>
</c:if>
<jsp:include page="footer.jsp"/>
</body>
</html>