<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'en_US'}"/>
<fmt:setBundle basename="messages"/>
<header class="navbar navbar-default" role="navigation" style="
        height: 70px;">
    <div class="container">
        <div class="navbar-header">
            <a class="logo" href="../index.jsp" style="padding-top: 5px">
                <img src="../img/logo.png" alt="" class="animated bounceInDown">
                <span class="name" style="padding-bottom: 0px; border-top-width: 5px;
                      margin-top: 0px;
                      "> rent-bike </span>
                <small><fmt:message key="BIKE_RENTAL_SYSTEM"/></small>
                <c:if test="${not empty sessionScope.user}">
                    <h5 style="text-align:left;padding-left: 78px;margin-bottom: 0px;
                        margin-top: 3px;"><p style="
                         margin-bottom: 5px;
                         ">${sessionScope.user.email}
                            ${sessionScope.user.role}</p>
                    </h5>
                </c:if>
            </a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1"
             style="width: 1170px;">
            <ul class="nav navbar-nav navbar-right" style="
                height: 74px;">
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
                            <li class=""><a href="main?command=showUsers"><fmt:message key="CLIENTS"/></a></li>
                        </c:if>
                        <c:if test="${sessionScope.user.role.value eq 'MANAGER'}">
                            <li class=""><a href="main?command=showListItem"><fmt:message key="REQUEST_FOR_REPAIR"/></a>
                            </li>
                            <li class=""><a href="main?command=showUsers"><fmt:message key="CLIENTS"/></a></li>
                        </c:if>
                        <c:if test="${not empty sessionScope.user.role}">
                            <li class=""><a href="main?command=showProfile"><fmt:message key="MY_PROFILE"/></a></li>
                        </c:if>
                        <li class=""><a href="main?command=logout"><fmt:message key="EXIT"/></a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</header>