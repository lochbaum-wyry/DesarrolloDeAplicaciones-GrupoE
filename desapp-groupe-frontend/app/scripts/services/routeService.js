(function () {
'use strict';
angular.module('desappGrupoeFrontendApp')
  .factory('RouteService',RouteService);

/* @ngInject */
function RouteService($http,$log){
  var url = 'http://localhost:8080/domain/servicesRest/route';

      //////////////////////////
      // RouteService interface
      //////////////////////////
  var routeService = {
    getRideProposals: getRideProposals
  };

      ///////////////////////////////////////////////////
      // RouteService interface function implementations
      ///////////////////////////////////////////////////

      /**
       * data format expected
       * {
       */
  function getRideProposals(timestamp, boardAt, getOffAt) {
    return $http.get(url + '/getRideProposals/' + timestamp + '/' + boardAt + '/' + getOffAt )
              .then(succGetRideProposals)
              .catch(failGetRideProposals);

    function succGetRideProposals(response){
      return response.data;
    }

    function failGetRideProposals(error){
      $log.error('Ocurrio un error: ' + error.data);
      return 'Ocurrio un error';
    }
  };

  return routeService;
}

})();