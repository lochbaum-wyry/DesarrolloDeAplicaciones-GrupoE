(function () { 
'use strict';
angular.module('desappGrupoeFrontendApp').factory('LoginService', LoginService);

function LoginService($http,$log) {
    
  var url = 'http://localhost:8080/domain/servicesRest/user';

  var loginService = {
    signUpAndLogin: signUpAndLogin
  };

  function signUpAndLogin(data) {

    return $http.post(url + '/signUpAndLogin',data)
        .then(onSuccessSignUp)
        .catch(onFailureSignUp);
    
    function onSuccessSignUp(response){
      return response.data;
    }

    function onFailureSignUp(error){
      $log.error('Ocurrio un error: ' + error.data);
      return 'Ocurrio un error';
    }

  };

  return loginService; 

}

})();