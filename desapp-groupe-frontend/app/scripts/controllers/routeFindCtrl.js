(function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('RouteFindCtrl', RouteFindCtrl);

/* @ngInject */
function RouteFindCtrl(RouteService,RideService,SessionService) {
  /* RouteFindCtrl */ var vm = this;
  /* String */ vm.ERROR_MSG = null;

  //------------
  // Scope data
  //------------

  // LatLng departurePoint = new LatLng(-34.702742,-58.2937437);
  // LatLng arrivalPoint   = new LatLng(-34.6753329,-58.342759);

  //-- for getRideProposals
  /* DateTime */ vm.selDate    = moment(); // fecha seleccionada por el usuario para la búsqueda de rutas
  /* LatLng */ vm.selDeparturePoint = '-34.702742,-58.2937437'; // puntos seleccionados por el usuario para la búsqueda de rutas
  /* LatLng */ vm.selArrivalPoint   = '-34.6753329,-58.342759';

  /* RideProposal[] */ vm.rideProposals = [];
  //-- for submitRideRequest
  /* int */ vm.selRideProposalIdx = null; // Indice de propuesta seleccionada por el usuario para solicitar unirse

  //------------------------------
  // Scope functions declarations
  //------------------------------
  vm.getRideProposals = getRideProposals;
  vm.requestRide = requestRide; 
  vm.onTimeSet = onTimeSet; 
  vm.selectRideProposal = selectRideProposal;
  vm.parseTimestamp = parseTimestamp;

  //--------------------------------
  // Scope functions implementation
  //--------------------------------
  function parseTimestamp(timestamp)
  {
    var ts = moment(timestamp);
    console.log(ts);
    return ts;
  }

  function onTimeSet(newDate, oldDate) {
    console.log('hey!');
    // vm.selDate = newDate;
  }

  function getRideProposals() {
    /* String */ var departurePoint = latLngToString(vm.selDeparturePoint);
    /* String */ var arrivalPoint = latLngToString(vm.selArrivalPoint);
    var routePromise = RouteService.getRideProposals(vm.selDate.valueOf(), departurePoint, arrivalPoint)
              .then(onSuccess)
              .catch(onFailure);
  
    function onSuccess(response) { 
      vm.rideProposals = response ;
      if (vm.rideProposals.length > 0) renderRideProposal(0) ;
    }

    function onFailure(error) {
      console.log(error);
     }
  }

  function selectRideProposal(index)
  {
    vm.selRideProposalIdx = index;
  }

  function requestRide() {
    var rideProposal = vm.rideProposals[vm.selRideProposalIdx];
    var rideRequest = rideProposalToRideRequest(rideProposal, SessionService.user());
    RideService.requestRide(rideRequest);
  }

  //-----------
  // Functions
  //-----------
  
  function rideProposalToRideRequest(/* RideProposal */ rideProposal, /* User */ requester) {
    return {
      driver:rideProposal['driver'],
      requester: requester,
      route: rideProposal['route'],
      date: rideProposal['departureDateTime'],
      boardingAt: rideProposal['boardingPoint'],
      getOffAt: rideProposal['getOffPoint']
    };
  }

  //TODO: Implementar la conversión adecuadamente. 
  function latLngToString( /* LatLng */ latLng) {

    return latLng;
  }

  function renderRideProposal(rideProposalIdx) { 
    /* RideProposal */ var rideProposal = vm.rideProposals[rideProposalIdx];
    renderRouteOnMap(
      rideProposal['route']['routePoints'], 
      rideProposal['boardingPoint'],  // punto cercano al que el usuario solicito subirse 
      rideProposal['getOffPoint'],  // punto cercano al que el usuairo solicita bajarse 
      "marker info"
    );
  } 

  /**
   * RoutePoint[] points: lista de todos los puntos que componen la ruta:
   * RoutePoint boardingPoint: Punto mas cercano dentro de la ruta al que el usuario seleccionó para subirse
   * RoutePoint getOffPoint: Punto mas cercano dentro de la ruta al que el usuario seleccionó para bajarse
   * String: markerInfo: información para imprimir en los markers del mapa (info del usuario y horario de partida por ej.)
   */ 
  function renderRouteOnMap(points, boardingPoint, getOffPoint, markerInfo) { 
    // hacer la magia con google maps
  }


} // RouteFindCtrl

})()