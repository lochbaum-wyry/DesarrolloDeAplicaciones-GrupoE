(function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('RideRequestsCtrl', RideRequestsCtrl);

/* @ngInject */
function RideRequestsCtrl(RideService,SessionService) {
  /* RideRequestsCtrl */ var vm = this;
  /* String */ vm.ERROR_MSG = null;

  //------------
  // Scope data
  //------------
  vm.user = SessionService.user();
  vm.rideRequests = []; 

  //------------------------------
  // Scope functions declarations
  //------------------------------

  vm.acceptRequest = acceptRequest; 
  vm.rejectRequest = rejectRequest; 
  //---------------------------
  // Controller initialization
  //---------------------------

  getRideRequests();

  //--------------------------------
  // Scope functions implementation
  //--------------------------------
  function parseTimestamp(timestamp)
  {
    var ts = moment(timestamp);
    return ts;
  }

  function acceptRequest(requestId) {    
    RideService.acceptRequest(requestId)
       .then( onSuccAccept.bind(requestId) )
       .catch(onFailAccept);

    function onSuccAccept(data) {
      getRideRequests();
      // vm.requestedRides = vm.requestedRides.filter( hasId.bind( this ) );
      // function hasId (rideReq) { return rideReq.id != this; }
    }

    function onFailAccept(error) { infoModal("failed_accepting_request", 'error');  }
  }

  function rejectRequest(requestId) {    
    RideService.rejectRequest(requestId)
       .then( onSuccReject.bind(requestId) )
       .catch(onFailReject);

    function onSuccReject(data) {
        getRideRequests();

      // vm.requestedRides = vm.requestedRides.filter( hasId.bind( this ) );
      // function hasId (rideReq) { return rideReq.id != this; }
    }


    function onFailReject(error) { infoModal("failed_rejecting_request", 'error');  }
  }



  //-----------
  // Functions
  //-----------

  function getRideRequests() {
    var routePromise = RideService.pendingRequestsFor(vm.user.id)
              .then(onSuccRR)
              .catch(onFailRR);

    function onSuccRR(response) { 
      vm.rideRequests = response ;
    }

    function onFailRR(error) {
      infoModal("failed_getting_ride_requests", 'error');
     }


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


} // RouteFindCtrl

})()