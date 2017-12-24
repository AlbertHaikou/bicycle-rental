<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
        <meta charset="UTF-8">
        <title> rent-bike </title>
        <link rel="stylesheet" href="css/bootstrap.css">
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&key=AIzaSyAzilovi5xP3lY1UiXP7arjvOsrNB6b77s&callback=initMap"></script>
        <script src="js/map.js"></script>
    </head>
    <body style="margin-bottom: 0px;">
        <jsp:include page="navigation.jsp"/>
           <div id="maps" class="maps" style="width:100%;height:530px;"></div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>

