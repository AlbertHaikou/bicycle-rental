<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'ru_RU'}"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="messages"/>
<link rel="stylesheet" href="css/bootstrap.css">
<script src="/js/changeLang.js"></script>
<div class="footer left">
<small style="margin-left: 38px;"><fmt:message key="COPYRIGHT"/></small>
        <ul class="left">
            <li>
                <button onclick="changeLang('rus')" class="btn" style=" background-color: transparent;">
                    <img src="/img/Russia.png">
                </button>
            </li>
            <li>
                <button onclick="changeLang('bel')" class="btn" style="background-color: transparent;">
                    <img src="/img/Belarus.png">
                </button>
            </li>
            <li>
                <button onclick="changeLang('eng')" class="btn" style=" background-color: transparent;">
                    <img src="/img/United-Kingdom.png">
                </button>
            </li>
        </ul>
</div>