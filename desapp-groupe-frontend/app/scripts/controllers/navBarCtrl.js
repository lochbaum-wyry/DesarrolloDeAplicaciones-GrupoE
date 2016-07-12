(function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('NavBarCtrl', navBarCtrl);

/* @ngInject */
function navBarCtrl(SessionService,$window, NotificationService) {
  /* navBarCtrl */ var vm = this;
  vm.user = SessionService.user();

} // navBarCtrl


})()