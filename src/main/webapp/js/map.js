window.initialize = function () {
    var center = new google.maps.LatLng(53.9, 27.565);
    var pos1 = new google.maps.LatLng(53.950968, 27.691108);
    var pos2 = new google.maps.LatLng(53.847, 27.53);
    var pos3 = new google.maps.LatLng(53.9171, 27.6166);
    var mapOptions = {
        zoom: 12,
        disableDefaultUI: true,
        center: center
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
}
google.maps.event.addDomListener(window, 'load', initialize);