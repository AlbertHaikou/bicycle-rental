<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'ru_RU'}"/>
<fmt:setBundle basename="messages"/>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title> City bike </title>
    <link rel="stylesheet" href="css/bootstrap.css">
</head>
<body>
<jsp:include page="parts/navigation.jsp"/>
<center class="graytext"><h3><fmt:message key="APPLICATION_FOR_REPAIR_OF_BIKE"/></h3>
    <br/>
    <div id="centerLayer">
        <form method="post" action="main?command=createRepairItem">
            <input type="hidden" name="id"
                   value="<c:out value="${id}" />"/> <br/><br/>
            <p><textarea class="input-lg" rows="10" cols="30" name="description"></textarea></p>
            <input class="btn btn-success" type="submit" value="<fmt:message key="TO_SEND"/>"/>
        </form>
    </div>
</center>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>