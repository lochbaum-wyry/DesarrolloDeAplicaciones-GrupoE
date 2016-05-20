(function () { 
'use strict';
angular.module('desappGrupoeFrontendApp').factory('LoginService', LoginService);

function LoginService($http,$log,SessionService) {
    
  var url = 'http://localhost:8080/domain/servicesRest/user';

  var loginService = {
    signUpAndLogin: signUpAndLogin
  };

  function signUpAndLogin(data) {

    return $http.post(url + '/signUpAndLogin',data)
        .then(onSuccessSignUp)
        .catch(onFailureSignUp);
    
    function onSuccessSignUp(response){
      var user = response.data;
      SessionService.initialize(user);
      return user; 
    }

    function onFailureSignUp(error){
      return 'Ocurrio un error';
    }

  };

  return loginService; 

}

})();