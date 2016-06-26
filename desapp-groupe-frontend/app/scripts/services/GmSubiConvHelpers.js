(function () {
'use strict';
angular.module('desappGrupoeFrontendApp')
  .factory('GmSubiConv',GmSubiConv);

/* @ngInyect */
function GmSubiConv(GoogleMapsService,GMRouteService) {
  var gmSubiConv = {
    latLngFromRoutePoint: buildLatLngFromRoutePoint, 
    routeFromDirections: buildRouteFromDirections
  };
  return gmSubiConv;

  function buildLatLngFromRoutePoint(routePoint) {
    return buildLatLng(routePoint['latitude'], routePoint['longitude']);
  }

  function buildRouteFromDirections(directions) {
    var dirRoute = directions.routes[0];
    console.log(dirRoute);
    var steps = dirRoute.legs[0].steps; 
    var distanceInKms = dirRoute.legs[0].distance.value / 1000 ; 

    var latLngs = [ GoogleMapsService.latLng(steps[0].start_point.lat(), steps[0].start_point.lng()) ];

    for (var i = 0 ; i < steps.length ; i++ )  { 
      var latLng = GoogleMapsService.latLng(steps[i].end_point.lat(), steps[i].end_point.lng()); 
      latLngs.push(latLng) ;
    }

    return {
      latLngs: latLngs,
      distanceInKms: distanceInKms
    };
  }

}

})();