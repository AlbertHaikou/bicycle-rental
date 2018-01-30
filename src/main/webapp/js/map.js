window.initialize = function () {
    var myLatlng = new google.maps.LatLng(53.9, 27.565);
    var pos1 = new google.maps.LatLng(53.950968, 27.691108);
    var pos2 = new google.maps.LatLng(53.85, 27.55);
    var pos3 = new google.maps.LatLng(53.89, 27.45);
    var pos4 = new google.maps.LatLng(53.86, 27.63);
    var pos5 = new google.maps.LatLng(53.94, 27.58);
    var pos6 = new google.maps.LatLng(53.93, 27.623);
    var mapOptions = {
        zoom: 12,
        disableDefaultUI: true,
        center: myLatlng
    }
    var map = new google.maps.Map(document.getElementById('maps'), mapOptions);
    var marker = new google.maps.Marker({
        position: pos1,
        map: map,
    });
    var marker = new google.maps.Marker({
        position: pos2,
        map: map,
    });
    var marker = new google.maps.Marker({
        position: pos3,
        map: map,
    });
    var marker = new google.maps.Marker({
        position: pos4,
        map: map,
    });
    var marker = new google.maps.Marker({
        position: pos5,
        map: map,
    });
    var marker = new google.maps.Marker({
        position: pos6,
        map: map,
    });
}
google.maps.event.addDomListener(window, 'load', initialize);