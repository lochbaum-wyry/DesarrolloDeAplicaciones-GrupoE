(function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('NavBarCtrl', navBarCtrl);

/* @ngInject */
function navBarCtrl(SessionService,$window) {
  /* navBarCtrl */ var vm = this;
  vm.user = SessionService.user();

  vm.logout = logout; 

  function logout() {
    SessionService.finalize();
    $window.location.href = "/index.html";
  }
} // navBarCtrl


})()