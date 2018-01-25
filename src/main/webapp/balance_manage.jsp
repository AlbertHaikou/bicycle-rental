<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'en_US'}"/>
<fmt:setBundle basename="messages"/>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link href="<c:url value='css/bootstrap.css' />" rel="stylesheet"></link>
    <title>Bicycle Rental</title>
</head>
<body>
<jsp:include page="parts/navigation.jsp"/>
<!-- Default panel contents -->
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class=" profile profile-content">
                <center><h3><fmt:message key="BALANCE_MANAGE"/></h3></center>
                <br/>
                <center><h3><label><c:out value="Your balance: ${balance}"></c:out></label></h3></center>
                <form method="post" action="main?command=fillUpBalance">
                    <div role="form">
                        <div class="form-group float-label-control">
                            <input type="number" class="form-control" required min="5" max="1000" name="sum"
                                   placeholder="Enter amount of money">
                            <button type="submit" class="btn btn-primary btn-success btn-block btn-lg"><fmt:message
                                    key="FILL_UP_BALANCE"/></button>
                        </div>
                    </div>
                </form>
                <br/>
                <form method="post" action="main?command=takeCredit">
                    <div role="form">
                        <div class="form-group float-label-control">
                            <input type="number" class="form-control" required min="5" max="1000" name="sum"
                                   placeholder="Enter amount of money"/>
                            <button type="submit" class="btn btn-primary btn-info btn-block btn-lg">
                                <fmt:message key="TAKE_A_LOAN"/>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>
