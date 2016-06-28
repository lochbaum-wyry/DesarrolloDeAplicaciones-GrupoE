(function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('RateRideCtrl', RateRideCtrl);

/* @ngInject */
function RateRideCtrl(RouteService,RideService,$localStorage) {
  /* RateRideCtrl */ var vm = this;
  /* String */ vm.ERROR_MSG = null;

  vm.rateablesRides = [];
  vm.rideSelected = {'driver':{'name':'fede','lastName':'Lochbaum','userName':'fede'},'vehicle':{},'users':[{'name':'fede2','lastName':'Lochbaum2','userName':'fede2'},{'name':'blabla','lastName':'Nada','userName':'sarasa'}]};
  vm.userSelected = {'name':'fede','lastName':'Lochbaum','userName':'fede'};
  vm.comment = "Escrime un comentario aqui";
  vm.rateValue = 1;
  vm.rateType = "";

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

  function getRateablesFromRide(rideId) {
    var routePromise = RideService.getRateablesFromRide(rideId,$localStorage.user.id)
              .then(onSuccess)
              .catch(onFailure);

    function onSuccess(response) { 
      vm.rideSelected = response;
    }

    function onFailure(error) {
      infoModal('error');
     }
  }

  function rate() {
    var rate = {'rater':$localStorage.user,'ratedUser':vm.userSelected,'ride':vm.rideSelected,'rateType':vm.rateType,'rateValue':vm.rateValue,'comment':vm.comment};
    var routePromise = RideService.rate(rate)
              .then(onSuccess)
              .catch(onFailure);

  function onSuccess(response) {
      vm.userSelected = {};
      vm.rateType = "";
      vm.rateValue = {};
      vm.comment = "";
      infoModal('Succes Rate');
    }

    function onFailure(error) {
      infoModal('error');
     }
  }

  function selectUser(user) {
    vm.rateType = "Accompany";
    vm.userSelected = user;
  }

  function selectDriver(){
    vm.rateType = "Driving";
    vm.userSelected = vm.selectDriver.driver;
  };

  function selectVehicle(){
    vm.rateType = "CarState";
    vm.userSelected = vm.selectDriver.vehicle;
  };

  function goodRate(){
    vm.rateValue = 1;
  };

  function badRate(){
    vm.rateValue = -1;
  };  



  getRateablesRides();

  } // RateRideCtrl

})()

