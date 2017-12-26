<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'en_US'}"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="messages"/>
<footer class="dark-block" style="margin-top: 0px; height: 71px;">
    <div class="container" style="text-align: center">
        <a href="<c:url value="main?command=changeLocale&locale=eng" />">EN |</a>
        <a href="<c:url value="main?command=changeLocale&locale=rus" />">RUS</a><br/>
        <small><fmt:message key="COPYRIGHT"/></small>
    </div>


</footer>