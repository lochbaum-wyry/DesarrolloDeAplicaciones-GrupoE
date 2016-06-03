(function () { 
'use strict';
angular.module('desappGrupoeFrontendApp').factory('LoginService', LoginService);

function LoginService($http,$log,SessionService) {
    
  var url = 'http://localhost:8080/domain/servicesRest/user';

  var loginService = {
    signUpAndLogin: signUpAndLogin,
    googleLogin: googleLogin
  };

  //inicia login google token
  function googleLogin(code){
    return $http({ method: 'post',
          url: url + '/signUpAndLogin2',
          data: { authorizationCode: code }});
  };
  //termina login google token

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