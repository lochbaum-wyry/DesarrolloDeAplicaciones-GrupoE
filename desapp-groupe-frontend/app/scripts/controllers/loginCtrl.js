(function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('LoginCtrl', loginCtrl );

/* @ngInject */
function loginCtrl(SessionService,LoginService,$window) {
  var vm = this; 

  /* String */ vm.ERROR_MSG = null;

  vm.signUpAndLogin = signUpAndLogin;
  vm.unsetError = unsetError;

  function unsetError() {
    vm.ERROR_MSG = null; 
  }

  function signUpAndLogin(data) {
    LoginService.signUpAndLogin(data)
      .then(onSuccess)
      .catch(onFailure);

    function onSuccess() {
      if (SessionService.isInitialized())
        $window.location.href = "/indexHome.html";
      else {
        vm.ERROR_MSG = "Oops... could not login. Try again."
      }
    }

    function onFailure(error) {
      vm.ERROR_MSG = error;
    }
  }
}

})()