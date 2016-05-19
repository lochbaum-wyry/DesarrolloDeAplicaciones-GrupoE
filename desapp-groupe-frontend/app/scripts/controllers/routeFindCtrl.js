(function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('RouteFindCtrl', RouteFindCtrl);

/* @ngInject */
function RouteFindCtrl(RouteService,RideService) {
  /* RouteFindCtrl */ var vm = this;
  /* String */ vm.ERROR_MSG = null;

  //------------
  // Scope data
  //------------

  //-- for getRideProposals
  /* DateTime */ vm.selDate    = moment(); // fecha seleccionada por el usuario para la búsqueda de rutas
  /* LatLng */ vm.selDeparturePoint = null; // puntos seleccionados por el usuario para la búsqueda de rutas
  /* LatLng */ vm.selArrivalPoint   = null;

  /* RideProposal[] */ vm.rideProposals = [];

  //-- for submitRideRequest
  /* int */ vm.selRideProposalIdx = null; // Indice de propuesta seleccionada por el usuario para solicitar unirse

  //------------------------------
  // Scope functions declarations
  //------------------------------
  vm.getRideProposals = getRideProposals;
  vm.requestRide = requestRide; 
  vm.onTimeSet = onTimeSet; 

  //--------------------------------
  // Scope functions implementation
  //--------------------------------
  function onTimeSet(newDate, oldDate) {
    console.log('hey!');
    // vm.selDate = newDate;
  }

  function getRideProposals() {

    var routePromise = RouteService.getRideProposals(vm.selDate.valueOf(), vm.selDeparturePoint, vm.selArrivalPoint)
              .then(onSuccess)
              .catch(onFailure);
  
    function onSuccess(response) { 
      vm.rideProposals = response ;
      if (vm.rideProposals.length > 0) renderRideProposal(0) ;
      alert('success papá!');
    }

    function onFailure(error) {
      console.log(error);
     }
  }

  function requestRide() {
    rideProposal = vm.rideProposals[vm.selRideProposalIdx]
//    RideService.requestRide(myself, rideProposal['driver'], )
  }

  //-----------
  // Functions
  //-----------
  
  function renderRideProposal(rideProposalIdx) { 
    /* RideProposal */ var rideProposal = vm.rideProposals[rideProposalIdx];
    renderRouteOnMap(
      rideProposal['route']['routePoints'], 
      rideProposal['boardingPoint'],  // punto cercano al que el usuario solicito subirse 
      rideProposal['getOffPoint'],  // punto cercano al que el usuairo solicita bajarse 
      ""
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