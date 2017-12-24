<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true"%>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'en_US'}"/>
<fmt:setBundle basename="messages" />
<header class="navbar navbar-default" roles="navigation" style="
        height: 70px;" >        
    <div class="container">
        <div class="navbar-header" >
            <a class="logo" href="index.jsp" style="padding-top: 5px">
                <img src="img/logo.png" alt="" class="animated bounceInDown">
                <span class="name" style="padding-bottom: 0px; border-top-width: 5px;
                      margin-top: 0px;
                      "> rent-bike </span>
                <small><fmt:message key="BIKE_RENTAL_SYSTEM"/></small>
                <c:if test="${not empty sessionScope.user}">
                    <h5 style="text-align:left;padding-left: 78px;margin-bottom: 0px;
                        margin-top: 3px;"><p style="
                         margin-bottom: 5px;
                         ">${sessionScope.user.email}
                            ${sessionScope.user.roles}</p>
                    </h5>
                </c:if>
            </a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1"
             style="width: 1170px;">
            <ul class="nav navbar-nav navbar-right" style="
                height: 74px;">
                <c:set var="admin" value="admin"/>
                <c:set var="client" value="client"/>
                <c:set var="support" value="support"/>
                <c:choose>
                    <c:when test="${empty sessionScope.user}">
                        <li ><a href="login.jsp"><fmt:message key="LOGIN"/></a></li>
                        <li ><a href="registration.jsp"><fmt:message key="REGISTRATION"/></a></li>
                        </c:when>
                        <c:otherwise>
                        <li class=""><a href="ParkingController?action=listParking"><fmt:message key="BIKE_PARKINGS"/></a></li>
                        <li class=""><a href="BikeController?action=listBike"><fmt:message key="BIKES"/></a></li>
                            <c:if test="${sessionScope.user.getRoles().contains(admin)}">
                            <li class=""><a href="UserController?action=listSupport"><fmt:message key="SERVICE_STAFF"/></a></li>
                            <li class=""><a href="UserController?action=getusers"><fmt:message key="CLIENTS"/></a></li>                        
                            </c:if>
                            <c:if test="${sessionScope.user.getRoles().contains(support)}">
                            <li class=""><a href="SupportItemController?action=listItem"><fmt:message key="REQUEST_FOR_REPAIR"/></a></li>
                            </c:if>
                            <c:if test="${sessionScope.user.getRoles().contains(client)}">
                            <li class=""><a href="ProfileController?action=listProfile"><fmt:message key="MY_PROFILE"/></a></li>
                            <li class=""><a href="ProfileController?action=historyrent"><fmt:message key="RENTAL_OF_HISTORY"/></a></li>                     
                            </c:if>
                        <li class=""><a href="LogoutController"><fmt:message key="EXIT"/></a></li>
                        </c:otherwise>
                    </c:choose>
            </ul>
        </div>
    </div>
</header>