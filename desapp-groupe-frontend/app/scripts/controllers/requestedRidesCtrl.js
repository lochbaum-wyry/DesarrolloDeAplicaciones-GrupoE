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

  vm.cancelRequest = cancelRequest; 
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
      infoModal("failed_getting_requested_rides", 'error');
     }


  }

  function cancelRequest(requestId) {    
    RideService.cancelRequest(requestId)
       .then( onSuccCancel.bind(requestId) )
       .catch(onFailCancel);

    function onSuccCancel(data) {
      vm.requestedRides = vm.requestedRides.filter( hasId.bind( this ) );
      function hasId (rideReq) { return rideReq.id != this; }
    }


    function onFailCancel(error) { infoModal("failed_cancelling_request", 'error');  }
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