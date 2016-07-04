(function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('RateRideCtrl', RateRideCtrl);

/* @ngInject */
function RateRideCtrl(RouteService,RideService,SessionService) {
  /* RateRideCtrl */ var vm = this;
  /* String */ vm.ERROR_MSG = null;

  vm.rateablesRides = [];
  vm.rideSelected = null;
  vm.userSelected = null;
  vm.comment = "Escrime un comentario aqui";
  vm.rateValue = 1;
  vm.rateType = 0;
  vm.getRateablesFromRide=getRateablesFromRide;
  vm.selectUser=selectUser;
  vm.selectDriver=selectDriver;
  vm.selectVehicle=selectVehicle;
  vm.title="";
  vm.goodRate=goodRate;
  vm.badRate=badRate;
  vm.rate=rate;


  function getRateablesRides() {
    var routePromise = RideService.getRateablesRides(SessionService.user().id)
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
    var routePromise = RideService.getRateablesFromRide(rideId,SessionService.user().id)
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
    var rate = {
      raterId:    SessionService.user().id,
      ratedUserId: vm.userSelected.id,
      rideId: vm.rideSelected.id,
      vehicleId: vm.rideSelected.vehicle.id,
      rateType: vm.rateType,
      rateValue: vm.rateValue,
      comment: vm.comment,
      date: moment().format("x")
    };

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
    vm.rateType = 1;
    vm.userSelected = user;
    vm.title=user.name;
  }

  function selectDriver(){
    vm.rateType = 0;
    vm.userSelected = vm.rideSelected.driver;
    vm.title=vm.userSelected.name;
  };

  function selectVehicle(){
    vm.rateType = 2;
    vm.userSelected = vm.rideSelected.vehicle;
    vm.title= "Vehicle";
  };

  function goodRate(){
    vm.rateValue = 0;
  };

  function badRate(){
    vm.rateValue = 1;
  };  

  function infoModal(msg, type) {
    if (type && type=='error') {
      msg = "Error: " + msg; 
    }
    alert(msg);
  }


  getRateablesRides();

  } // RateRideCtrl

})()

