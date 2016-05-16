(function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('RouteFindCtrl', RouteFindCtrl);

RouteFindCtrl.$inject['RouteService'];

function RouteFindCtrl(RouteService,RideService) {

  /* User */ var myself = {}; 
  /* RideProposal[] */ var rideProposals = [];
  /* String */ var ERROR_MSG = null;
  /* $scope */ var vm = this; 

  ////////////////////////////////
  // Scope functions declarations
  ////////////////////////////////
  vm.getRideProposals = getRideProposals;
  vm.requestRide = requestRide; 

  //////////////
  // Scope data 
  //////////////
  
  // for getRideProposals
  /* DateTime */ vm.selectedDate    = null; // fecha seleccionada por el usuario para la búsqueda de rutas
  /* LatLng */ vm.selDeparturePoint = null; // puntos seleccionados por el usuario para la búsqueda de rutas
  /* LatLng */ vm.selArrivalPoint   = null; 

  // for submitRideRequest
  /* int */ vm.selRideProposalIdx = null; // Indice de propuesta seleccionada por el usuario para solicitar unirse


  //////////////////////////////////
  // Scope functions implementation
  //////////////////////////////////
  function getRideProposals() {
    var data = {
      date:           vm.selDate,
      departurePoint: LatLng.toString(vm.selDeparturePoint),
      arrivalPoint:   LatLng.toString(vm.selArrivalPoint)
    };

    var routePromise = RouteService.getRideProposals(data)
              .then(onSuccess)
              .catch(onFailure);
  
    function onSuccess(response) { 
      rideProposals = response ; 
      if (rideProposals.length > 0) renderRideProposal(0) ;
    }

    function onFailure(error) { }
  }

  function requestRide() {
    rideProposal = rideProposals[vm.selRideProposalIdx]
    RideService.requestRide(myself, rideProposal['driver'], )
  }

  /////////////
  // Functions
  /////////////
  
  function renderRideProposal(rideProposalIdx) { 
    /* RideProposal */ var rideProposal = rideProposals[rideProposalIdx];
    renderRouteOnMap(
      rideProposal['route']['routePoints'], 
      rideProposal['boardingPoint'],  // punto cercano al que el usuario solicito subirse 
      rideProposal['getOffPoint'],  // punto cercano al que el usuairo solicita bajarse 
      ""
    );
  } 

  /**
   * RoutePoint[] points : lista de todos los puntos que componen la ruta: 
   * RoutePoint boardingPoint: Punto mas cercano dentro de la ruta al que el usuario seleccionó para subirse
   * RoutePoint getOffPoint: Punto mas cercano dentro de la ruta al que el usuario seleccionó para bajarse
   * String: markerInfo: información para imprimir en los markers del mapa (info del usuario y horario de partida por ej.)
   */ 
  function renderRouteOnMap(points, boardingPoint, getOffPoint, markerInfo) { 
    // hacer la magia con google maps
  }


} // RouteFindCtrl

})()