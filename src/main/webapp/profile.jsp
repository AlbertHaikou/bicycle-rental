<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'ru_RU'}"/>
<fmt:setBundle basename="messages"/>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title> rent-bike </title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/profile.css">

</head>
<body style="margin-bottom: 0px;">
<header>
    <jsp:include page="parts/navigation.jsp"/>
</header>
<main>
    <div class="container">
        <div class="row profile">
            <div class="col-md-4">
                <div class="profile-sidebar">
                    <div class="profile-userpic">
                        <img src="img/user.jpg"
                             class="img-responsive" alt="">
                    </div>
                    <div class="profile-usertitle">
                        <div class="profile-usertitle-name">
                            <c:out value="${requestScope.profile.firstName}"/> <c:out
                                value="${requestScope.profile.lastName}"/>
                        </div>
                        <div class="profile-usertitle-job">
                            <c:out value="${sessionScope.user.role}"/>
                        </div>
                        <div class="profile-usertitle-name">
                            <fmt:message key="BALANCE"/>:<c:out value="${requestScope.profile.balance}"/>
                        </div>
                    </div>

                    <div class="profile-userbuttons">

                    </div>

                    <div class="profile-usermenu">
                        <ul class="nav">
                            <li>
                                <a href="main?command=showEditProfilePage&id=<c:out value="${requestScope.profile.id}"/>">
                                    <i class="glyphicon glyphicon-pencil"></i>
                                    <fmt:message key="UPDATE"/> </a>
                            </li>
                            <li>
                                <a href="main?command=showBalanceManagePage">
                                    <i class="glyphicon glyphicon-edit"></i>
                                    <fmt:message key="BALANCE_MANAGE"/> </a>
                            </li>
                            <li>
                                <a href="main?command=showRentalHistory">
                                    <i class="glyphicon glyphicon-time"></i>
                                    <fmt:message key="FULL_RENTAL_HISTORY"/> </a>
                            </li>
                        </ul>
                    </div>

                </div>
            </div>
            <div class="col-md-8">
                <div class="profile-content">
                    <center style="color:graytext;"><h3><fmt:message key="RENTAL_HISTORY"/></h3></center>
                    <table class="table">
                        <thead>
                        <tr class="tab-pane">
                            <th><fmt:message key="DATE_OF_RENT"/></th>
                            <th><fmt:message key="№_BIKE"/></th>
                            <th><fmt:message key="PRICE"/></th>
                            <th><fmt:message key="DATE_OF_RETURN"/></th>
                            <th><fmt:message key="TOTAL_PRICE"/></th>
                            <th><fmt:message key="STATUS"/></th>

                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${rents}" var="rent">
                            <tr>
                                <td><fmt:formatDate type="both" value="${rent.fromDate}"/></td>
                                <td><c:out value="${rent.bikeId}"/></td>
                                <td><c:out value="${rent.price}"/></td>
                                <td><fmt:formatDate type="both" value="${rent.toDate}"/></td>
                                <td><c:out value="${rent.totalPrice}"/></td>
                                <c:choose>
                                    <c:when test="${null eq (rent.totalPrice)}">
                                        <td><fmt:message key="TAKEN"/>
                                            </p><a href="main?command=returnBike&id=<c:out value="${rent.bikeId}"/>">
                                                <button class="btn btn-success"><fmt:message key="TO_RETURN"/></button>
                                            </a>
                                        </td>

                                    </c:when>
                                    <c:otherwise>
                                        <td><fmt:message key="RETURNED"/></td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    </br>
    </br>
</main>
<footer>
    <jsp:include page="parts/footer.jsp"/>
</footer>
</body>
</html>