<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'en_US'}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Error Page</title>
</head>
<body>
<jsp:include page="parts/navigation.jsp"/>
<div class="error-div">
    <div class="container">
        <div class="row">
            <div class="col-md-10 col-md-offset-1">
                <h3>${message}</h3>
                <h3 class="red"><fmt:message key="ERR_NOTIFICATION"/></h3>
                <a href="<c:url value="main?command=showMainPage" />"><fmt:message key="MAIN"/></a>
            </div>
        </div>
    </div>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>
