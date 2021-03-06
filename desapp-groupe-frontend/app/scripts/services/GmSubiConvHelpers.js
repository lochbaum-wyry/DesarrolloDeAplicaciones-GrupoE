(function () {
'use strict';
angular.module('desappGrupoeFrontendApp').factory('GmSubiConv',GmSubiConv);

/* @ngInyect */
function GmSubiConv(GoogleMapsService,GMRouteService) {
  var gmSubiConv = {
    latLngFromRoutePoint: buildLatLngFromRoutePoint, 
    routePointFromLatLng: buildRoutePointFromLatLng,
    routeFromDirections: buildRouteFromDirections, 
    directionsReqFromRoute: buildDirectionsReqFromRoute
  };
  return gmSubiConv;

  function buildLatLngFromRoutePoint(routePoint) {
    return GoogleMapsService.latLng(routePoint['latitude'], routePoint['longitude']);
  }

  function buildRoutePointFromLatLng(latLng) {
    return { latitude: latLng.lat, longitude: latLng.lng } ;
  }


  function buildRouteFromDirections(directions) {
    /* DirectionsRoute */ var dirRoute = directions.routes[0];
    /* Step[] */ var steps = dirRoute.legs[0].steps; 
    /* Float */ var distanceInKms = dirRoute.legs[0].distance.value / 1000 ; 
    /* LatLng[] */ var latLngs = GoogleMapsService.latLngsFromDirections(directions);
    return {
      latLngs: latLngs.map(buildRoutePointFromLatLng),
      distanceInKms: distanceInKms
    };
  }

  function buildDirectionsReqFromRoute(route, optimizeWaypoints) {
    var ps = route.routePoints.map(GoogleMapsService.latLngFromRoutePoint);
    return GoogleMapsService.buildDirectionsReqFromPointList(ps, optimizeWaypoints);
  }

}

})();