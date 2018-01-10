<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'en_US'}"/>
<fmt:setBundle basename="messages"/>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link href="<c:url value='css/bootstrap.css' />" rel="stylesheet"></link>
    <title>Bicycle Rental</title>
</head>
<body>
<jsp:include page="parts/navigation.jsp"/>
<!-- Default panel contents -->
<div class="center-block">
    <div class="panel-heading center-block"><h3><fmt:message key="BALANCE_MANAGE"/></h3></div>
    <label class="info"><c:out value="Your balance: ${balance}"></c:out></label>
    <form class="form-horizontal" method="post" action="main?command=fillUpBalance">
        <input type="number" required min="5" max="1000" name="sum" placeholder="Введите сумму">
        <button type="submit" class="btn-info">Пополнить баланс</button>
    </form>
    <br/>
    <form class="form-horizontal" method="post" action="main?command=takeCredit">
        <input type="number" required min="5" max="1000" name="sum" placeholder="Введите сумму"/>
        <button type="submit"  class="btn-info">Взять кредит</button>
    </form>
</div>

<jsp:include page="parts/footer.jsp"/>
</body>
</html>
