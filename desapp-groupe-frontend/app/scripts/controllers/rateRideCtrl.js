(function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('RateRideCtrl', RateRideCtrl);

/* @ngInject */
function RateRideCtrl(RouteService,RideService,$localStorage) {
  /* RateRideCtrl */ var vm = this;
  /* String */ vm.ERROR_MSG = null;

  vm.rateablesRides = [];
  vm.rideSelected = {};
  vm.users = {};
  vm.userSelected = {};

  function getRateablesRides() {
    var routePromise = RideService.getRateablesRides($localStorage.user.id)
              .then(onSuccess)
              .catch(onFailure);

    function onSuccess(response) { 
      vm.rateablesRides = response;
    }

    function onFailure(error) {
      infoModal('error');
     }
  }

  function usersAwaingRates(rideId) {
    var routePromise = RideService.usersAwaingRates(rideId,$localStorage.user.id)
              .then(onSuccess)
              .catch(onFailure);

    function onSuccess(response) { 
      vm.users = response;
    }

    function onFailure(error) {
      infoModal('error');
     }
  }


  getRateablesRides();

  } // RateRideCtrl

})()

