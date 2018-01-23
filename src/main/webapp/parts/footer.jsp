<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'en_US'}"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="messages"/>
<link rel="stylesheet" href="css/bootstrap.css">
<div class="footer">
    <small>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<fmt:message key="COPYRIGHT"/></small>
    <div class="container">
        <ul>
            <li>
                <form role="form" method="post" name="myForm" action="main?command=changeLocale&locale=rus">
                    <input type="hidden" name="urlAdd"
                           value="<c:out value="${pageContext.request.queryString}" />"/>
                    <button type="submit" class="btn" style="background-image: none;
            background-color: transparent; color: #df0000"><img src="/img/Russia.png"></button>
                </form>
            </li>
            <li>
                <form role="form" method="post" name="myForm" action="main?command=changeLocale&locale=bel">
                    <input type="hidden" name="urlAdd"
                           value="<c:out value="${pageContext.request.queryString}" />"/>
                    <button type="submit" class="btn" style="background-image: none;
            background-color: transparent; color: #df0000"><img src="/img/Belarus.png"></button>
                </form>
            </li>
            <li>
                <form role="form" method="post" name="myForm" action="main?command=changeLocale&locale=eng">
                    <input type="hidden" name="urlAdd"
                           value="<c:out value="${pageContext.request.queryString}" />"/>
                    <button type="submit" class="btn" style="background-image: none;
            background-color: transparent; color: #df0000"><img src="/img/United-States.png"></button>
                </form>
            </li>
        </ul>
    </div>
</div>