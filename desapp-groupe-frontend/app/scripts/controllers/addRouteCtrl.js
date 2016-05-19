'use strict';

angular.module('desappGrupoeFrontendApp')
  .controller('AddRouteCtrl', function ($scope,UserService,$localStorage,$UserCtrl) {

  $scope.routeList = [];
  $scope.latLng = {'latitude':'0','longitude':'0'};
  $scope.latitude = 0;


  $scope.addPoint = function () {
    $scope.routeList.push($scope.latLng);
    $scope.latLng = {'latitude':'0','longitude':'0'};
  };

  $scope.addRoute = function () {

      onSuccess = function (result){
            $scope.latLng = {'latitude':'0','longitude':'0'};
            $scope.routeList = [];
      };

      onFailure = function (error){
        $scope.ERROR_MSG = error;
      };

      var user = $localStorage.user;
      var schedules = [];

      userPromise = UserService.addRoute(user,routeList,100,10000,schedules);
      userPromise.then(onSuccess);
      userPromise.catch(onFailure);
  };

});