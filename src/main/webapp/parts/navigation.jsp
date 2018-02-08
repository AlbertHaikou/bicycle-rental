<%@ page pageEncoding="UTF-8" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="/js/jquery/jquery.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ page session="true" %>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'ru_RU'}"/>
<fmt:setBundle basename="messages"/>

<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="logo" href="../index.jsp">
                <img src="../img/logo.png" alt="" class="animated bounceInDown">
                <span class="name"> city bike </span>
                <small><fmt:message key="BIKE_RENTAL_SYSTEM"/></small>
                <c:if test="${not empty sessionScope.user}">
                    <h5>
                        <p>
                            <ctg:hello role="${sessionScope.user.role}" name="${sessionScope.user.firstName}"/>
                        </p>
                    </h5>
                </c:if>
            </a>
        </div>
        <div class="collapse navbar-collapse" id="navbar">
            <ul class="nav navbar-nav navbar-right">
                <c:set var="ADMINISTRATOR" value="ADMINISTRATOR"/>
                <c:set var="CLIENT" value="CLIENT"/>
                <c:set var="MANAGER" value="MANAGER"/>
                <c:choose>
                    <c:when test="${empty sessionScope.user}">
                        <li><a href="../login.jsp"><fmt:message key="LOGIN"/></a></li>
                        <li><a href="../registration.jsp"><fmt:message key="REGISTRATION"/></a></li>
                    </c:when>
                    <c:otherwise>
                        <li class=""><a href="main?command=showParkings"><fmt:message key="BIKE_PARKINGS"/></a></li>
                        <li class=""><a href="main?command=showBikes"><fmt:message key="BIKES"/></a></li>
                        <c:if test="${sessionScope.user.role.value eq 'ADMINISTRATOR'}">
                            <li class=""><a href="main?command=showManagers"><fmt:message key="MANAGER"/></a></li>
                        </c:if>
                        <c:if test="${sessionScope.user.role.value eq 'MANAGER' or sessionScope.user.role.value eq 'ADMINISTRATOR'}">
                            <li class=""><a href="main?command=showRepairList"><fmt:message key="REQUEST_FOR_REPAIR"/></a>
                            </li>
                            <li class=""><a href="main?command=showUsers"><fmt:message key="CLIENTS"/></a></li>
                        </c:if>

                            <li class=""><a href="main?command=showProfile"><fmt:message key="MY_PROFILE"/></a></li>
                        <li class=""><a href="main?command=logout"><fmt:message key="EXIT"/></a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>

