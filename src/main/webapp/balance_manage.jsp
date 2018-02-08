<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'ru_RU'}"/>
<fmt:setBundle basename="messages"/>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/bootstrap.css">
    <title> City bike </title>
</head>
<body>
<jsp:include page="parts/navigation.jsp"/>
<center><h3 class="red"><c:out value="${errorMsg}"/></h3></center>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class=" profile profile-content">
                <center class="graytext"><h3><fmt:message key="BALANCE_MANAGE"/></h3></center>
                <center><h3><label><fmt:message key="BALANCE"/><c:out value=": ${balance}"></c:out></label></h3>
                </center>
                <c:if test="${debtor}">
                    <center><h3><label><fmt:message key="CREDIT"/><c:out
                            value=": ${credit}"></c:out></label></h3></center>
                </c:if>
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
                <c:if test="${balance < 10 and not debtor}">
                    <form method="post" action="main?command=takeALoan">
                        <div role="form">
                            <div class="form-group float-label-control">
                                <input type="number" class="form-control" required min="5" max="50" name="loan"
                                       placeholder="Enter amount of money"/>
                                <button type="submit" class="btn btn-primary btn-info btn-block btn-lg">
                                    <fmt:message key="TAKE_A_LOAN"/>
                                </button>
                            </div>
                        </div>
                    </form>
                </c:if>
            </div>
        </div>
    </div>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>
