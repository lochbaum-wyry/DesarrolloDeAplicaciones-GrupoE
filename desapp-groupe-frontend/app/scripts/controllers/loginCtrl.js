(function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('LoginCtrl', loginCtrl );

/* @ngInject */
function loginCtrl(SessionService,LoginService,$window) {
  var vm = this; 

/*
"algun", 
"otro", 
"algunotro", "algunotrousuario@gmail.com", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/28/Albert_Einstein_Head_cleaned.jpg/250px-Albert_Einstein_Head_cleaned.jpg"
*/
  vm.userData = {
    'name':'Dan',
    'lastName':'Wyry',
    'userName':'danwyry',
    'email':'dandanielw@gmail.com',
    'image': 'https://upload.wikimedia.org/wikipedia/commons/thumb/2/28/Albert_Einstein_Head_cleaned.jpg/250px-Albert_Einstein_Head_cleaned.jpg'
    // 'image':'https://media.licdn.com/mpr/mpr/shrinknp_200_200/p/8/005/074/165/2458a49.jpg'
  }; 

  /* String */ vm.ERROR_MSG = null;

  vm.signUpAndLogin = signUpAndLogin;
  vm.unsetError = unsetError;

  function unsetError() {
    vm.ERROR_MSG = null; 
  }

  function signUpAndLogin() {
    LoginService.signUpAndLogin(vm.userData)
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