  (function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('RankingCtrl', RankingCtrl);

function RankingCtrl(RankingService,$localStorage) {

  var vm = this; 
  vm.ranking = {};
  vm.rankingSelected = [];
  vm.dateSelected = new Date() ;
  vm.bestsSelected = true;

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

  vm.getBests = function(){
    vm.rankingSelected.selected = vm.rankingSelected.bests;
    vm.bestsSelected = true;
  };

  vm.getWorsts = function(){
    vm.rankingSelected.selected = vm.rankingSelected.worsts;
    vm.bestsSelected = false;
  };

  vm.getDrivers = function(){
    vm.rankingSelected.bests = vm.ranking.bestDrivers;
    vm.rankingSelected.worsts = vm.ranking.worstDrivers;
    vm.rankingSelected.selected = vm.rankingSelected.bests;
  };  

  vm.getPassengers = function(){
    vm.rankingSelected.bests = vm.ranking.bestPassenger;
    vm.rankingSelected.worsts = vm.ranking.worstPassenger;
    vm.rankingSelected.selected = vm.rankingSelected.bests;
  };

  vm.getvehicles = function(){
    vm.rankingSelected.bests = vm.ranking.bestVehicles;
    vm.rankingSelected.worsts = vm.ranking.worstVehicles;
    vm.rankingSelected.selected = vm.rankingSelected.bests;
  };

  vm.getRanking();

}

})()