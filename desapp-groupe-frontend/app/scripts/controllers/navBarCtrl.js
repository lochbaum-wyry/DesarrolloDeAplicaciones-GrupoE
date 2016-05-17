(function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('NavBarCtrl', navBarCtrl);

/* @ngInject */
function navBarCtrl($localStorage) {
  /* navBarCtrl */ var vm = this;
  vm.user = $localStorage.user;
} // navBarCtrl


})()