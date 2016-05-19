  (function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('AddRouteCtrl', AddRouteCtrl);

function AddRouteCtrl(UserService,$localStorage) {

  var vm = this;
  
  vm.routeList = [];
  vm.latLng = {'latitude':'0','longitude':'0'};
  vm.latitude = 0;
  vm.ERROR_MSG;
  vm.user = $localStorage.user

  
  vm.addPoint = function() {
    vm.routeList.push(vm.latLng);
    vm.latLng = {'latitude':'0','longitude':'0'};
  };

  vm.addRoute = function() {

      function onSuccess(result){
            vm.latLng = {'latitude':'0','longitude':'0'};
            vm.routeList = [];
            $localStorage.user = UserService.reloadUser();
      };

      function onFailure(error){
        vm.ERROR_MSG = error;
      };

      var schedules = [];

      var userPromise = UserService.addRoute(vm.user,vm.routeList,100,10000,schedules);
      userPromise.then(onSuccess);
      userPromise.catch(onFailure);
  };

} // RouteFindCtrl

})()