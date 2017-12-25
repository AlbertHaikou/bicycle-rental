<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
         <center style="color:graytext;"><h3><fmt:message key="APPLICATION_FOR_REPAIR_OF_BIKE"/></h3>
        <br/>  
        <div id="centerLayer" >
            <form method="post" action="SupportItemController?action=create">
                <input type="hidden" name="id"
                 value="<c:out value="${bike.bicycleId}" />" /> <br /><br />
                <input type="hidden" name="status" value="false" />
                <p><textarea rows="10" cols="45" name="description"></textarea></p>
                <input type="submit" value="<fmt:message key="TO_SEND"/>"/>
            </form>   
        </div>      </center>
            <jsp:include page="footer.jsp"/>
    </body>
</html>