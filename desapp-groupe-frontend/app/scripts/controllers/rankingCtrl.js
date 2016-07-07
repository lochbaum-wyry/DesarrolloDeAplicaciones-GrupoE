  (function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('RankingCtrl', RankingCtrl);

function RankingCtrl(RankingService,$localStorage) {

  var vm = this; 
  vm.ranking = {};
  vm.rankingSelected = [];
  vm.dateSelected = new Date() ;

  /* String */ vm.ERROR_MSG;

  vm.getRanking = function() {

    function onSuccess(result){
        vm.ranking =  result;
    };

    function onFailure(error){
        vm.ERROR_MSG = error;
    };

      var schedules = [];

      RankingService.ranking() 
              .then(onSuccess)
              .catch(onFailure);
  };


  vm.getRankingForDate = function(){
    function onSuccess(result){
        vm.ranking =  result;
        vm.rankingSelected = vm.ranking.bestDrivers;
    };

    function onFailure(error){
        vm.ERROR_MSG = error;
    };

      var schedules = [];

      RankingService.getRankingForDate(vm.dateSelected.getFullYear(),vm.dateSelected.getMonth()) 
              .then(onSuccess)
              .catch(onFailure);
  };

  vm.getDrivers = function(){
    vm.rankingSelected = vm.ranking.bestDrivers;
  };  

  vm.getPassengers = function(){
    vm.rankingSelected = vm.ranking.bestPassenger;
  };

  vm.getvehicles = function(){
    vm.rankingSelected = vm.ranking.bestVehicles;
  };

  vm.getRanking();

}

})()