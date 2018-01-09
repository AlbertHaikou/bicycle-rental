<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'en_US'}"/>
<fmt:setBundle basename="messages"/>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title> rent-bike </title>
    <link rel="stylesheet" href="css/bootstrap.css">
</head>
<body style="margin-bottom: 0px;">
<jsp:include page="parts/navigation.jsp"/>
<center style="color:graytext;"><h3><fmt:message key="EDITING"/></h3>
    <div id="centerLayer">
        <form method="post" action="main?command=updateProfile">
            <input type="hidden" name="id"
                   value="<c:out value="${profile.id}" />"/> <br/>
            <label><fmt:message key="FIRST_NAME"/>:</label>
            <input type="text" name="firstName"
                   required
                   pattern="[A-Za-z]{3,}"
                   title="First symbol latin, 3+ latin symbols only"
                   value="<c:out value="${profile.firstName}" />"/> <br/><br/>
            <label><fmt:message key="LAST_NAME"/>:</label>
            <input type="text" name="lastName"
                   required
                   pattern="[A-Za-z]{3,}"
                   title="First symbol latin, 3+ latin symbols only"
                   value="<c:out value="${profile.lastName}" />"/> <br/><br/>
            <label><fmt:message key="EMAIL"/>:</label>
            <input type="email" name="email"
                   required
                   title="Use Latin letters, ._%+- and digits, then @, followed by Latin letters, symbols -. and numbers. Further . and after it domain of 2-4 Latin letters"
                   name="email" placeholder="<fmt:message key="EMAIL"/>"
                   pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
                   value="<c:out value="${profile.email}" />"/> <br/><br/>
            <input
                    type="submit" value="<fmt:message key="SAVE"/>"/>
</center>
</form>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>