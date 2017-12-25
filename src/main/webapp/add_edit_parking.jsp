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
    <center style="color:graytext;"><h3><fmt:message key="ADD_NEW_PARKING"/></h3>
        <br/>  
        <div id="centerLayer" >
            <form method="post" action="main?command=${empty parking ? 'createParking' : 'updateParking'}">
                <input type="hidden"  name="id"
                       value="<c:out value="${parking.parkingId}" />" /> <br /> 
                <label>Улица:</label> <input
                    type="text" name="street"
                    value="<c:out value="${parking.street}" />" /> <br /><br /> 
                <br /><br /> <input
                    type="submit" value="<fmt:message key="SAVE"/>"/>
            </form>   
        </div>      </center>
        <jsp:include page="footer.jsp"/>
</body>
</html>