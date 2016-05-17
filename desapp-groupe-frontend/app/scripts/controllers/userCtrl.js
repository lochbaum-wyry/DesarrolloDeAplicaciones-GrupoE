'use strict';

/**
 * @ngdoc function
 * @name desappGrupoeFrontendApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the desappGrupoeFrontendApp
 */
angular.module('desappGrupoeFrontendApp')
  .controller('UserCtrl', function ($scope,UserService,$window) {
    $scope.ERROR_MSG = null;
    $scope.user = {};

    var onSuccess,onFailure,userPromise;

    $scope.signUpData = function (data) {
    	onSuccess = function (result){
    		$scope = result;
            $window.location.href = '/#/home2';
    	};

    	onFailure = function (error){
    		$scope.ERROR_MSG = error;
    	};

    	userPromise = UserService.signUp(data);
    	userPromise.then(onSuccess);
    	userPromise.catch(onFailure);

    };
  });
