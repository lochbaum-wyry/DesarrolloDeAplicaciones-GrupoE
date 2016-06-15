(function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('RequestedRidesCtrl', RequestedRidesCtrl);

/* @ngInject */
function RequestedRidesCtrl(RideService,SessionService) {
  /* RequestedRidesCtrl */ var vm = this;
  /* String */ vm.ERROR_MSG = null;

  //------------
  // Scope data
  //------------
  vm.user = SessionService.user();
  vm.requestedRides = []; 

  //------------------------------
  // Scope functions declarations
  //------------------------------

  //---------------------------
  // Controller initialization
  //---------------------------

  getRequestedRides();

  //--------------------------------
  // Scope functions implementation
  //--------------------------------
  function parseTimestamp(timestamp)
  {
    var ts = moment(timestamp);
    return ts;
  }

  function getRequestedRides() {
    var routePromise = RideService.pendingRequestsBy(vm.user.id)
              .then(onSuccRR)
              .catch(onFailRR);

    function onSuccRR(response) { 
      vm.requestedRides = response ;
    }

    function onFailRR(error) {
      vm.requestedRides = [] ;
      infoModal("failed_getting_requested_rides", 'error');
     }
  }


  //-----------
  // Functions
  //-----------

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


} // RouteFindCtrl

})()