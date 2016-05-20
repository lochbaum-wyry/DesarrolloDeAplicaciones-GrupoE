(function () { 
'use strict';
angular.module('desappGrupoeFrontendApp').factory('SessionService', sessionService);

/* @ngInject */
function sessionService($http,$log,$localStorage,UserService) {
    
  var sessionService = {
    initialize: initialize, 
    reloadUser: reloadUser,
    user: currentUser,
    isInitialized: isInitialized,
    finalize: finalize

  };

  function isInitialized() {
    return $localStorage.user != null ; 
  }

  function initialize(user) {
    $localStorage.user = user ; 
  }

  function finalize() {
    $localStorage.user = null; 
  }

  function currentUser() {
    return $localStorage.user; 
  }

  function reloadUser() {
    return UserService.getUser($localStorage.user.id)
      .then(onSuccessReload)
      .catch(onFailureReload);

    function onSuccessReload(user){
      return user;
    }

    function onFailureReload(error){
        $log.error('Ocurrio un error: ' + error.data);
        return 'Ocurrio un error';
    }
  };



  return sessionService; 

}

})();