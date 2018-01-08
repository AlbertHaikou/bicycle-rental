<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'en_US'}"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="messages"/>
<footer class="dark-block" style="margin-top: 0px; height: 71px;">
    <small>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<fmt:message key="COPYRIGHT"/></small>
    <div class="container" style="text-align: center; top: 6px;
    position: absolute;">
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1"
             style="width: 1170px;">
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
</footer>