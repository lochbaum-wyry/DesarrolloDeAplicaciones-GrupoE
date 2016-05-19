  (function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('AddRouteCtrl', AddRouteCtrl);

function AddRouteCtrl(UserService,$localStorage) {

  var vm = this;
  
  vm.routeList = [];
  vm.latLng = {'latitude':'0','longitude':'0'};
  vm.latitude = 0;
  vm.ERROR_MSG;

   function addPoint() {
    vm.routeList.push(vm.latLng);
    vm.latLng = {'latitude':'0','longitude':'0'};
  };

  function addRoute() {

      function onSuccess(result){
            vm.latLng = {'latitude':'0','longitude':'0'};
            vm.routeList = [];
      };

      function onFailure(error){
        vm.ERROR_MSG = error;
      };

      var user = $localStorage.user;
      var schedules = [];

      userPromise = UserService.addRoute(user,routeList,100,10000,schedules);
      userPromise.then(onSuccess);
      userPromise.catch(onFailure);
  };

} // RouteFindCtrl

})()