(function () { 
'use strict';
angular.module('desappGrupoeFrontendApp').factory('LoginService', LoginService);

function LoginService($http,$log,SessionService) {
    
  var url = 'http://localhost:8080/domain/servicesRest/user';

  var loginService = {
    googleLogin: googleLogin
  };

  //inicia login google token
  function googleLogin(code){
    return $http({ method: 'post',
          url: url + '/signUpAndLogin',
          data: { authorizationCode: code }})
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
  //termina login google token

  return loginService; 

}

})();