(function () {
'use strict';
angular.module('desappGrupoeFrontendApp')
  .factory('GoogleMapsService',GoogleMapsService);

/* @ngInyect */
function GoogleMapsService() {

  var markers = [];
  var mapEvents = [] ; 

  var mapservice = {
    clearMap: clearMap, 
    instanceMap: instanceMap,
    addMarker: addMarker,
    renderRoute: renderRoute, 

    directionsRequest: buildDirectionsRequest,
    directionsReqFromPointList: buildDirectionsReqFromPointList,
    latLngsFromDirections: buildLatLngsFromDirections,
    latLng: buildLatLng, 
    waypoint: buildWaypoint,
    calculateAndDisplayRoute: calculateAndDisplayRoute, 

    latLngFromAddress: latLngFromAddress,
    addressFromLatLng: addressFromLatLng,

    addListener: google.maps.event.addListener, 
    onMapDblClick: onMapDblClick
  }; 

  return mapservice; 

  function clearMap() {
    for (var i in markers) {
      markers[i].setMap(null);
      markers[i] = null ; 
    }
    markers = []; 
    initMap();
  }

  function instanceMap(containerDiv) {
    map = new google.maps.Map(document.getElementById(containerDiv), {
      zoom: 14,
      center: {lat: -34.585963, lng: -58.4473145}
    });

    directionsDisplay.setMap(map);
  }


  function renderRoute(points) {
    var request = buildDirectionsReqFromPointList(points);
    calculateAndDisplayRoute(request); 
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

    markers.push(marker);

    return marker;
  }

  function buildInfoWindow(content) {
    return ( new google.maps.InfoWindow({ content: content }) );
  }

  function buildLatLngsFromDirections(directions) {
    /* DirectionsRoute */ var dirRoute = directions.routes[0];
    /* Step[] */ var steps = dirRoute.legs[0].steps; 
    /* LatLng[] */ var latLngs = [ buildLatLng(steps[0].start_point.lat(), steps[0].start_point.lng()) ];

    for (var i = 0 ; i < steps.length ; i++ ) {
      var latLng = buildLatLng(steps[i].end_point.lat(), steps[i].end_point.lng()); 
      latLngs.push(latLng);
    }

    return latLngs;
  }


  function buildDirectionsReqFromPointList(ps, optimizeWaypoints) {
      var waypoints = (ps.length < 3) ? [] : ps.slice(2, ps.length).map(buildWaypoint);
      console.log(waypoints);
      return buildDirectionsRequest(ps[0], ps[ps.length-1], waypoints, optimizeWaypoints);
  }

  function buildDirectionsRequest(origin, destination, waypoints, optimizeWaypoints) {
    optimizeWaypoints = (optimizeWaypoints == undefined) ? true : optimizeWaypoints ;
    var directionsRequest = {
      travelMode: google.maps.TravelMode.DRIVING,
      origin: origin,
      destination: destination,
      waypoints: waypoints, 
      optimizeWaypoints: optimizeWaypoints
    }; 
    return directionsRequest; 
  }

  function calculateAndDisplayRoute(request, callback) {
    directionsService.route(request, displayCallback);

    function displayCallback(response, status) {
      if (status === google.maps.DirectionsStatus.OK) { 
       directionsDisplay.setDirections(response);

       if (callback != undefined) 
        callback(response);

      } else {
        window.alert('Directions request failed due to ' + status);
      }
    }

  }

  function addressFromLatLng(address, onSuccCallback, onFailCallback) {
    console.log(address);
    geocoder.geocode({'location': address}, function(results, status) {
      console.log(status);
      if (status === google.maps.GeocoderStatus.OK) {
        onSuccCallback(results[1].formatted_address);
      } else {
        onFailCallback(status);
      }
    });
  }

  function latLngFromAddress(address, onSuccCallback, onFailCallback) {
    geocoder.geocode( { 'address': address}, function(results, status) {
      if (status == google.maps.GeocoderStatus.OK) {
        onSuccCallback(results[0].geometry.location);
      } else {
        onFailCallback(status);
      }
    });
  }

  function onMapDblClick(callback) {
    map.addListener('dblclick', callback);
  }

}

})();
