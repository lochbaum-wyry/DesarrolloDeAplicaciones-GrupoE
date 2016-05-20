  (function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('AddRouteCtrl', AddRouteCtrl);

function AddRouteCtrl(UserService,$localStorage, SessionService) {

  var vm = this;
  
  /* String */ vm.ERROR_MSG;

  /* User */ vm.user = SessionService.user();
  /* Route[] */ vm.userRoutes = vm.user.routes ; 
  /* RoutePoint[] */ vm.routePointList = [];
  /* LatLng */ vm.latLng = {};
  /* Float */ vm.distanceInKms = 10.0; 
  /* Float */ vm.fixedCosts = 100.0;

  //-------------------
  //-- Scope functions
  //-------------------
  vm.addPoint = addPoint;
  vm.addRoute = addRoute; 

  resetLatLng();

  function resetLatLng() {
    vm.latLng = { 
      latitude: 0,
      longitude: 0 
    }  ;
  }

  function addPoint() {
    var latlng = {
      latitude:  parseFloat(vm.latitude),
      longitude: parseFloat(vm.longitude)
    }

    vm.routePointList.push(vm.latLng);
    resetLatLng();
  };
  
  function addRoute() {

    function onSuccess(result){
      vm.latLng = {
        latitude:0,
        longitude:0
      };
      vm.routePointList = [];
      SessionService.reloadUser();
      vm.user = SessionService.user();
      vm.userRoutes = SessionService.user().routes;
    };

      function onFailure(error){
        vm.ERROR_MSG = error;
      };

      var schedules = [];

      UserService.addRoute(vm.user.id,vm.routePointList,vm.distanceInKms,vm.fixedCosts,schedules) 
              .then(onSuccess)
              .catch(onFailure);
  };

} // addRouteCtrl

})()