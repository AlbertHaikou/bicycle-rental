<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'en_US'}"/>
<fmt:setBundle basename="messages" />
<html>
<head>
    <title>Error</title>
</head>
<body>
    <div class="error-div">
        <h1>${message}</h1>
        Here the message!
        <a href="<c:url value="main?command=showMainPage" />"><fmt:message  key="MAIN" /></a>
    </div>
</body>
</html>