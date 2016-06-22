(function () {
'use strict';
angular.module('desappGrupoeFrontendApp')
  .factory('GoogleMapsService',GoogleMapsService);

/* @ngInyect */
function GoogleMapsService() {

  var markers = []; 
  var routes = []; 
  var routeCreationMarkers = [];

  var mapservice = {
    instanceMap: instanceMap,
    clearMap: clearMap, 
    renderRoute: renderRoute, 
    latLng: buildLatLng, 
    latLngFromRoutePoint: buildLatLngFromRoutePoint, 
    addMarker: addMarker
    /*,
    addRouteCreationMarker: addRouteCreationMarker 
    */
  }; 

  return mapservice; 

  function clearMap() {
    directionsDisplay.setMap(null);
  }

  function instanceMap(containerDiv) {
    map = new google.maps.Map(document.getElementById('mapRouteFind'), {
      zoom: 8,
      center: {lat: -34.397, lng: 150.644}
    });

    directionsDisplay.setMap(map);
  }
/*
  function initRouteCreationWithMarkers() {
    routeCreationMarkers = [];
  }

  function addRouteCreationMarker(location) {
    var marker = addMarker(location, false, markersInfo[idx].content);
  }

  function endRouteCreationWithMarkers() {
    return routeCreationMarkers; 
  }
*/
  function renderRoute(points, draggablePoints, markersInfo) {
    var origin = points[0]; 
    var destination = points[points.length-1]; 
    var waypoints = getWaypoints(points) ; 
    
    calculateAndDisplayRoute(origin, destination, waypoints); 

    for (var idx in markersInfo) {
      var location = points[markersInfo[idx].index];
      addMarker(location, false, markersInfo[idx].content);
    }
  }

  function buildLatLng(lat,lng) {
    return {lat: lat, lng: lng };
  }

  function buildLatLngFromRoutePoint(routePoint) {
    return buildLatLng(routePoint['latitude'], routePoint['longitude']);
  }

  function buildWaypoint(point,stopover) {
    stopover = stopover || true; 
    return {
      location: buildLatLngPoint, 
      stopover: stopover
    };
  }

  function getWaypoints(points) {
    var waypoints = []; 
    if (points.length > 2) {
      for (var i = 1 ; i < points.lengt-2 ; ++i) {
        waypoints.push( buildWaypoint(points[i]) );
      }
    }
    return waypoints; 
  }

  function addMarker(position, draggable, withInfoWindow) {

    var marker = new google.maps.Marker({
      position: position,
      map: map,
      draggable: draggable,
    });

    withInfoWindow = withInfoWindow || false; 
    if (withInfoWindow) {
      var infoWindow = buildInfoWindow(withInfoWindow);
      infoWindow.open(map, marker);
    }

    return marker;
  }

  function buildInfoWindow(content) {
    return ( new google.maps.InfoWindow({ content: content }) );
  }

  function calculateAndDisplayRoute(origin, destination, waypoints) {

    map.setCenter(origin); 

    var directiosnRequest = {
      origin: origin,
      destination: destination,
      travelMode: google.maps.TravelMode.DRIVING,
      waypoints: waypoints
    }; 

    var callback = function(response, status) {
      if (status === google.maps.DirectionsStatus.OK) {
        directionsDisplay.setDirections(response);
      } else {
        window.alert('Directions request failed due to ' + status);
      }
    };

    directionsService.route(directiosnRequest, callback);
  }

}

})();


  /*
    var infowindow = new google.maps.InfoWindow({
      content: "Inicio del Recorrido<br>Arrastre el marcador hasta el punto deseado"
    });

    var marker = new google.maps.Marker({
      position: start,
      map: map,
      draggable: draggablePoints,
    });

    marker.addListener('click', function() {
      infowindow.open(map, marker);
    });

    var marker2 = new google.maps.Marker({
      position: end,
      map: map,
      draggable: draggablePoints,
    });

    marker2.addListener('click', function() {
      infowindow2.open(map, marker2);
    });


    google.maps.event.addListener(marker, 'dragend', function() { 
      calculateAndDisplayRoute(directionsService, directionsDisplay);
    });
    google.maps.event.addListener(marker2, 'dragend', function() { 
      calculateAndDisplayRoute(directionsService, directionsDisplay);
    });

    infowindow.open(map, marker);

  */