(function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('LoginCtrl', loginCtrl );

/* @ngInject */
function loginCtrl(LoginService,$window,$localStorage) {
  var vm = this; 

  /* String */ vm.ERROR_MSG = null;

  vm.signUpAndLogin = signUpAndLogin;

  function signUpAndLogin(data) {
    LoginService.signUpAndLogin(data)
      .then(onSuccess)
      .catch(onFailure);

    function onSuccess(result) {
      $localStorage.user = result;
      $window.location.href = "/indexHome.html";
    }

    function onFailure(error) {
      vm.ERROR_MSG = error;
    }
  }
}

})()