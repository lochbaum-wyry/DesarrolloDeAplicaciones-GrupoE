'use strict';

/**
 * @ngdoc function
 * @name desappGrupoeFrontendApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the desappGrupoeFrontendApp
 */
angular.module('desappGrupoeFrontendApp')
  .controller('UserCtrl', function ($scope,UserService,RateService,RideService,$location,$localStorage) {
    $scope.ERROR_MSG = null;
    $scope.user = $localStorage.user;
    $scope.rates = [];
    $scope.rides = [];

    var onSuccess,onFailure,userPromise;

    $scope.getRates = function(){
        onSuccess = function (result){
            $scope.rates =  result;
        };

        onFailure = function (error){
            $scope.ERROR_MSG = error;
        };

        userPromise = RateService.getRates($scope.user.id);
        userPromise.then(onSuccess);
        userPromise.catch(onFailure);
    };

    $scope.getFutureRides = function(){
        onSuccess = function (result){
            $scope.rides =  result;
        };

        onFailure = function (error){
            $scope.ERROR_MSG = error;
        };

        userPromise = RideService.getFutureRides($scope.user.id);
        userPromise.then(onSuccess);
        userPromise.catch(onFailure);
    };

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

    $scope.getRates();
    $scope.getFutureRides();

  });
