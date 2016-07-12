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
    $scope.ridesAsDriverSelected = true;
    $scope.ridesSelected = [];
    $scope.ridesAsDriver = [];
    $scope.ridesAsPassenger = [];


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

    $scope.getAsDriver = function(){
        $scope.ridesSelected = $scope.ridesAsDriver;
        $scope.ridesAsDriverSelected = true;
    };

    $scope.getAsPassenger = function(){
        $scope.ridesSelected = $scope.ridesAsPassenger;
        $scope.ridesAsDriverSelected = false;
    };

    $scope.awaitingRidesAsDriver = function(){
        onSuccess = function (result){
            $scope.ridesAsDriver =  result;
            $scope.ridesSelected = $scope.ridesAsDriver;
        };

        onFailure = function (error){
            $scope.ERROR_MSG = error;
        };

        userPromise = RideService.awaitingRidesAsDriver($scope.user.id);
        userPromise.then(onSuccess);
        userPromise.catch(onFailure);
    };

    $scope.awaitingRidesAsPassenger = function(){
        onSuccess = function (result){
            $scope.ridesAsPassenger =  result;
        };

        onFailure = function (error){
            $scope.ERROR_MSG = error;
        };

        userPromise = RideService.awaitingRidesAsPassenger($scope.user.id);
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
    $scope.awaitingRidesAsDriver();
    $scope.awaitingRidesAsPassenger();

  });
