(function () { 
'use strict';
angular.module('desappGrupoeFrontendApp').factory('RateService', RateService);


function RateService($http,$log) {
  var url = 'http://localhost:8080/domain/servicesRest/user';

  var rateService = {
    getRates: getRates
  };

  return rateService;

  function getRates(userId){
    return $http.get(url + '/getRates/' + userId)
      .then(onSucc)
      .catch(onFail);
  }


  function onSucc(response) {
    return response.data;
  }

  function onFail(error){
    $log.error('Ocurrio un error: ' + error.data);
    return 'Ocurrio un error';
  }
}




})();