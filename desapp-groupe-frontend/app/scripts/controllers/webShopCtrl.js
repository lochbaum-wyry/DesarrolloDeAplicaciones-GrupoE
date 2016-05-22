(function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('WebShopCtrl', webShopCtrl );

/* @ngInject */
function webShopCtrl($localStorage) {
  var vm = this; 

  /* String */ vm.ERROR_MSG = null;

  vm.user = $localStorage.user

  vm.search = '';

  vm.products = [{'name':'vale por 10 pesos','cost':'10','stock':'2'},{'name':'sarasa','cost':'10','stock':'2'},{'name':'sarasa','cost':'10','stock':'2'},{'name':'sarasa','cost':'10','stock':'2'}];

  }

})()