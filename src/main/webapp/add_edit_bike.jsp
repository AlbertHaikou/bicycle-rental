<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
    <center style="color:graytext;"><h3><fmt:message key="EDITING"/></h3>
        <br/>
        <div id="centerLayer" >
            <form method="post" action="BikeController?action=${empty bike ? 'create' : 'update'}">
                <input type="hidden"  name="id"
                       value="<c:out value="${bike.bicycleId}" />" /> <br />
                <label><fmt:message key="TYPE"/> :</label> <input
                    type="text" name="type"
                    value="<c:out value="${bike.type}" />" /> <br /><br />
                <label><fmt:message key="MODEL"/> :</label> <input
                    type="text" name="model"
                    value="<c:out value="${bike.model}" />" /> <br /><br />
                <label><fmt:message key="STATUS"/> :</label>
                <select name="available">
                    <option value="free" }>
                        <c:out value="free"/>
                    </option>
                    <option value="busy" }>
                        <c:out value="busy"/>
                    </option>
                </select><br /><br />

                <label><fmt:message key="SIZE"/> :</label> <input type="text" name="size"
                                             value="<c:out value="${bike.size}" />" /> <br /><br />
                <label><fmt:message key="PARKING"/>:</label>
                <select name="parkingId">
                    <c:forEach items="${parkings}" var="parking">
                        <option value="${parking.parkingId}"  ${parking.parkingId == bike.parkingId ? 'selected' : ''}>
                            <c:out value="${parking.street}"/>
                        </option>
                    </c:forEach>
                </select>
                <br /><br />
                <input
                    type="submit" value="SAVE"/>
            </form>
        </div>  </center>
        <jsp:include page="footer.jsp"/>
    </body>
</html>