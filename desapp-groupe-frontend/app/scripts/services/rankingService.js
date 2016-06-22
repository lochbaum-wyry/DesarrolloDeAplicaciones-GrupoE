(function () {
'use strict';
angular.module('desappGrupoeFrontendApp')
  .factory('RankingService',RankingService);

/* @ngInject */
function RankingService($http,$log){
  var url = 'http://localhost:8080/domain/servicesRest/gaming';

  var rankingService = {
    ranking : ranking,
    getRankingForDate: getRankingForDate
  };

  function ranking() {
    return $http.get(url + '/ranking')
              .then(succRanking)																																																																																																																																																	
              .catch(failRanking);

    function succRanking(response){
      return response.data;
    }

    function failRanking(error){
      $log.error('Ocurrio un error: ' + error.data);
      return 'Ocurrio un error';
    }
  };

  function getRankingForDate(year,month){
    return $http.get(url + '/getRankingForDate/' + year + '/' + month)
              .then(succgetRankingForDate)                                                                                                                                                                                                                                                                                                  
              .catch(failgetRankingForDate);

    function succgetRankingForDate(response){
      return response.data;
    }

    function failgetRankingForDate(error){
      $log.error('Ocurrio un error: ' + error.data);
      return 'Ocurrio un error';
    }
  };

   return rankingService;
}

})();