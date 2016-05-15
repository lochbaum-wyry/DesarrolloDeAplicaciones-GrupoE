'use strict';

/**
 * @ngdoc function
 * @name desappGrupoeFrontendApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the desappGrupoeFrontendApp
 */
angular.module('desappGrupoeFrontendApp')
  .controller('UserCtrl', function ($scope, UserService) {
    $scope.ERROR_MSG = null;
    $scope.userData = {};
    var onSuccess,onFailure,userPromise;

    $scope.getUserData = function (id){
    	onSuccess = function (result){
    		$scope.userData = result;
    	};

    	onFailure = function (error){
    		$scope.ERROR_MSG = error;
    	};

    	userPromise = UserService.getUser(id);
    	userPromise.then(onSuccess);
    	userPromise.catch(onFailure);
    };

    $scope.signUpData = function (data) {
        
        data = {'name':'asd4','lastName':'asd3','userName':'asd2','email':'asd'};

    	onSuccess = function (result){
    		$scope.ERROR_MSG = result;
    	};

    	onFailure = function (error){
    		$scope.ERROR_MSG = error;
    	};

    	userPromise = UserService.singUp(data);
    	userPromise.then(onSuccess);
    	userPromise.catch(onFailure);

    };

  });
