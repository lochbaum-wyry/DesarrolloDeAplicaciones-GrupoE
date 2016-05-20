  (function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('RankingCtrl', RankingCtrl);

function RankingCtrl(RankingService,$localStorage) {

  var vm = this;
  vm.users = $localStorage.rankingUser;

  /* String */ vm.ERROR_MSG;

  function ranking() {

    function onSuccess(result){
        $localStorage.rankingUser =  result;
    };

    function onFailure(error){
        vm.ERROR_MSG = error;
    };

      var schedules = [];

      RankingService.ranking() 
              .then(onSuccess)
              .catch(onFailure);
  };




}

})()