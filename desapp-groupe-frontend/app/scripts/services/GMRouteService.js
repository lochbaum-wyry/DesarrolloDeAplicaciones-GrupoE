(function () {
'use strict';
angular.module('desappGrupoeFrontendApp')
  .factory('GMRouteService',GMRouteService);

/* @ngInyect */
function GMRouteService(GoogleMapsService) {

  /* boolean */ var draggableOrigValue ; 
  /* Marker */ var initialMarker ; 
  var routeLocations = [] ; 
  var routeAddMarkerMapListener = null ; 

  var gmRouteService = {
    createRouteWithMarkers: createRouteWithMarkers,
    addRouteMarker: addRouteMarker,
    endRouteWithMarkers: endRouteWithMarkers
  }; 
  return gmRouteService; 

  //--------------------------------------
  //-- Interface functions implementation

  function createRouteWithMarkers() {
    draggableOrigValue = directionsDisplay.draggable; 
    directionsDisplay.setOptions({
      draggable:true, 
      suppressBicyclingLayer: true
    });

    routeAddMarkerMapListener = GoogleMapsService.onMapDblClick(function (e) { 
      var position = GoogleMapsService.latLng(e.latLng.lat(), e.latLng.lng());
      addRouteMarker(position);
    });
  }

  function addRouteMarker(location, displayInstantly) {

    if (routeLocations.length == 2) 
      routeLocations = [] ; 

    if (routeLocations.length == 0) {
      
      initialMarker = GoogleMapsService.addMarker(location, true);
      /* int */ var idx = routeLocations.length ; 
      GoogleMapsService.addListener(initialMarker, 'dragend', markerDragendEvtHandler.bind( idx ));

    } else if (routeLocations.length == 1) {

      initialMarker.setMap(null) ; 
      initialMarker = null ; 

    }

    routeLocations.push(location);
    
    displayInstantly = (displayInstantly == undefined) ? true : displayInstantly ; 
    if (displayInstantly)
      displayRouteWithMarkers();

    /**
     * Requiere que se le bindee la posición en routeLocations que se mapea directamente con la 
     * location que se modifica con el marcador
     */
    function markerDragendEvtHandler(e) {
      var routeLocationIdx = this; 
      routeLocations[routeLocationIdx] = latLngFromEvt(e);
      displayRouteWithMarkers();
    }
  }

  function endRouteWithMarkers() {
    google.maps.event.removeListener(routeAddMarkerMapListener);
    routeAddMarkerMapListener = null; 
    directionsDisplay.setOptions({draggable:draggableOrigValue});

    return directionsDisplay.getDirections(); 
  }

  function displayRouteWithMarkers() {
    if (routeLocations.length >= 2) {
      var request = GoogleMapsService.directionsReqFromPointList(routeLocations);
      GoogleMapsService.calculateAndDisplayRoute(request);
    }

  }

  function latLngFromEvt(e) { return GoogleMapsService.latLng(e.latLng.lat(), e.latLng.lng()) ; }


}

})();