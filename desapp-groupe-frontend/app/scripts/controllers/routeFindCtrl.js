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

  //-- for getRideProposals
  /* DateTime */ vm.selDate; // fecha seleccionada por el usuario para la búsqueda de rutas
  /* LatLng */ vm.selDeparturePoint; // puntos seleccionados por el usuario para la búsqueda de rutas
  /* LatLng */ vm.selArrivalPoint;
  /* RideProposal[] */ vm.rideProposals = [];

  //-- for submitRideRequest
  /* int */ vm.selRideProposalIdx ; // Indice de propuesta seleccionada por el usuario para solicitar unirse

  //------------------------------
  // Scope functions declarations
  //------------------------------
  vm.getRideProposals = getRideProposals;
  vm.requestRide = requestRide; 
  vm.onTimeSet = onTimeSet; 
  vm.selectRideProposal = selectRideProposal;
  vm.parseTimestamp = parseTimestamp;

  //---------------------------
  // Controller initialization

  setDefaultValues() 



  //--------------------------------
  // Scope functions implementation
  //--------------------------------
  function parseTimestamp(timestamp)
  {
    var ts = moment(timestamp);
    return ts;
  }

  function onTimeSet(newDate, oldDate) {
    // vm.selDate = newDate;
  }

  function getRideProposals() {
    /* String */ var departurePoint = latLngToString(vm.selDeparturePoint);
    /* String */ var arrivalPoint = latLngToString(vm.selArrivalPoint);
    var routePromise = RouteService.getRideProposals(vm.selDate.valueOf(), departurePoint, arrivalPoint)
              .then(onSuccess)
              .catch(onFailure);

    vm.selRideProposalIdx= null;

    function onSuccess(response) { 
      vm.rideProposals = response ;
      if (vm.rideProposals.length > 0) renderRideProposal(0) ;
    }

    function onFailure(error) {
      infoModal("failed_getting_proposals", 'error');
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
    RideService.requestRide(rideRequest).then(onReqSucc).catch(onReqFail);

    function onReqSucc(data) {
      infoModal("successfull_ride_request_msg");
      setDefaultValues();
      vm.rideProposals = [];
    }

    function onReqFail(error) {
      infoModal("failed_requesting_ride", 'error');
      console.log(error);
    }
  }

  //-----------
  // Functions
  //-----------

  function setDefaultValues() {
    vm.selDate = moment("2016-07-08 9:20", "YYYY-MM-DD HH:mm"); 
    vm.selDeparturePoint = '-34.702742,-58.2937437'; 
    vm.selArrivalPoint = '-34.6753329,-58.342759';
    vm.selRideProposalIdx= null;
  }

  function rideProposalToRideRequest(/* RideProposal */ rideProposal, /* User */ requester) {
    return {
      driverId: rideProposal['driver']['id'],
      requesterId: requester['id'],
      routeId: rideProposal['route']['id'],
      date: rideProposal['departureDateTime'],
      boardingAtId: rideProposal['boardingPoint']['id'],
      getOffAtId: rideProposal['getOffPoint']['id']
    };
  }

  //TODO: Implementar la conversión adecuadamente. 
  function latLngToString( /* LatLng */ latLng) {

    return latLng;
  }

  function infoModal(msg, type) {
    if (type && type=='error') {
      msg = "Error: " + msg; 
    }
    alert(msg);
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