(function () {
'use strict';
angular.module('desappGrupoeFrontendApp')
  .factory('GoogleMapsService',GoogleMapsService);

/* @ngInyect */
function GoogleMapsService() {

  var markers = []; 
  var routes = []; 
  var routeCreationMarkers = [];
  var routeCreationLocations = [] ; 
  var routeCreationAddMarkerMapListener = null ; 

  var mapservice = {
    instanceMap: instanceMap,
    clearMap: clearMap, 
    renderRoute: renderRoute, 
    latLng: buildLatLng, 
    latLngFromRoutePoint: buildLatLngFromRoutePoint, 
    addMarker: addMarker,
    initRouteCreationWithMarkers: initRouteCreationWithMarkers,
    addRouteCreationMarker: addRouteCreationMarker,
    endRouteCreationWithMarkers: endRouteCreationWithMarkers
  }; 

  return mapservice; 

  function clearMap() {
    directionsDisplay.setMap(null);
  }

  function instanceMap(containerDiv) {
    map = new google.maps.Map(document.getElementById(containerDiv), {
      zoom: 14,
      center: {lat: -34.585963, lng: -58.4473145}
    });

    directionsDisplay.setMap(map);
  }

  function initRouteCreationWithMarkers() {
    routeCreationMarkers = [];
    routeCreationAddMarkerMapListener = map.addListener('dblclick', function (e) { 
      var position = buildLatLng(e.latLng.lat(), e.latLng.lng());
      addRouteCreationMarker(e.latLng);
    });
  }

  function addRouteCreationMarker(location) {
    var marker = addMarker(location, false);
    routeCreationMarkers.push(marker);
    routeCreationLocations.push(location);

    if (routeCreationLocations.length >= 2) {
      var waypoints = [] ; 
      if (routeCreationLocations.length > 2) 
        waypoints = routeCreationLocations.slice(2).map(buildWaypoint); 

      clearMap();
      directionsDisplay.setMap(map);
      calculateAndDisplayRoute(routeCreationLocations[0],routeCreationLocations[1], waypoints);
    }
  }

  function endRouteCreationWithMarkers() {
    google.maps.event.removeListener(routeCreationAddMarkerMapListener);
    routeCreationAddMarkerMapListener = null; 
    return routeCreationLocations; 
  }

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

    stopover = stopover===true || false; 
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

    // map.setCenter(origin); 

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
