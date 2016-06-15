'use strict';

/**
 * @ngdoc function
 * @name desappGrupoeFrontendApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the desappGrupoeFrontendApp
 */
angular.module('desappGrupoeFrontendApp')
  .controller('UserCtrl', function ($scope,UserService,$location,$localStorage) {
    $scope.ERROR_MSG = null;
    $scope.user = $localStorage.user;

    var onSuccess,onFailure,userPromise;

    $scope.signUpData = function (data) {
    	onSuccess = function (result){
    		$scope = result;
            $localStorage.user = result;
            $location.path('/home');
    	};

    	onFailure = function (error){
    		$scope.ERROR_MSG = error;
    	};

    	userPromise = UserService.signUp(data);
    	userPromise.then(onSuccess);
    	userPromise.catch(onFailure);

    };

    $scope.userProfile = function(id){
        $location.path('/profile/'+id);
    }

    $scope.percentageGoodRate = function(){
        var res;
        if($scope.user.totalRateCount <= 0){
            res = 0;
        }
        else
        res = ((($scope.user.goodRateCount * 100) - 
            ((($scope.user.goodRateCount * 100) % $scope.user.totalRateCount))) 
        / $scope.user.totalRateCount);
        
        return res
    }

  });
