(function () {
'use strict';
angular.module('desappGrupoeFrontendApp')
  .factory('GoogleMapsService',GoogleMapsService);

/* @ngInyect */
function GoogleMapsService() {

  var markers = [];   

  var mapservice = {
    clearMap: clearMap, 
    instanceMap: instanceMap,
    addMarker: addMarker,
    renderRoute: renderRoute, 

    
    latLng: buildLatLng, 
    waypoint: buildWaypoint,
    calculateAndDisplayRoute: calculateAndDisplayRoute
  }; 

  return mapservice; 

  function clearMap() {
    directionsDisplay.setMap(null);
    directionsDisplay.setMap(map);
  }

  function instanceMap(containerDiv) {
    map = new google.maps.Map(document.getElementById(containerDiv), {
      zoom: 14,
      center: {lat: -34.585963, lng: -58.4473145}
    });

    directionsDisplay.setMap(map);
  }


  function renderRoute(points, draggablePoints, markersInfo) {
    var origin = points[0]; 
    var destination = points[points.length-1]; 
    var waypoints = getWaypoints(points) ; 

    calculateAndDisplayRoute(origin, destination, waypoints, false); 

    for (var idx in markersInfo) {
      var location = points[markersInfo[idx].index];
      addMarker(location, false, markersInfo[idx].content);
    }
  }

  function buildLatLng(lat,lng) {
    return {lat: lat, lng: lng };
  }


  function buildWaypoint(point,stopover) {

    stopover = (stopover===true) || false; 
    return {
      location: point, 
      stopover: stopover
    };
  }

  function getWaypoints(points) {
    var waypoints = []; 
    if (points.length > 2) {
      for (var i = 1 ; i <= points.length-2 ; ++i) {
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

    withInfoWindow = (withInfoWindow == undefined) ? false : withInfoWindow  ; 
    if (withInfoWindow) {
      var infoWindow = buildInfoWindow(withInfoWindow);
      infoWindow.open(map, marker);
    }

    return marker;
  }

  function buildInfoWindow(content) {
    return ( new google.maps.InfoWindow({ content: content }) );
  }

  function calculateAndDisplayRoute(origin, destination, waypoints, optimizeWaypoints) {
    optimizeWaypoints = (optimizeWaypoints == undefined) ? true : optimizeWaypoints ;
    var directionsRequest = {
      origin: origin,
      destination: destination,
      travelMode: google.maps.TravelMode.DRIVING,
      waypoints: waypoints, 
      optimizeWaypoints: optimizeWaypoints
    }; 
    directionsService.route(directionsRequest, displayCallback);

    function displayCallback(response, status) {
      if (status === google.maps.DirectionsStatus.OK) { 
       directionsDisplay.setDirections(response);
      } else {
        window.alert('Directions request failed due to ' + status);
      }
    }

  }

}

})();
