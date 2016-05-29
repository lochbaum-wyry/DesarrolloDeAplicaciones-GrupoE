  (function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('RankingCtrl', RankingCtrl);

function RankingCtrl(RankingService,$localStorage) {

  var vm = this; 
  vm.ranking = $localStorage.ranking ;

  /* String */ vm.ERROR_MSG;

  vm.getRanking = function() {

    function onSuccess(result){
        $localStorage.ranking =  result;
    };

    function onFailure(error){
        vm.ERROR_MSG = error;
    };

      var schedules = [];

      RankingService.ranking() 
              .then(onSuccess)
              .catch(onFailure);
  };

  vm.getRanking();

}

})()