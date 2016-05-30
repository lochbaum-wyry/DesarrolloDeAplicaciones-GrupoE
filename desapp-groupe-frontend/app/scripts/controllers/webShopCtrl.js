(function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('WebShopCtrl', webShopCtrl );

/* @ngInject */
function webShopCtrl($localStorage,webShopService) {
  var vm = this; 

  /* String */ vm.ERROR_MSG = null;

  vm.user = $localStorage.user

  vm.search = '';
  vm.getProducts = getProducts;
  vm.products = [];

  getProducts();



  	function getProducts(){

        function onSuccess(result){
            vm.products = result;
        };

        function onFailure(error){
            vm.ERROR_MSG = error;
        };

        webShopService.products() 
              .then(onSuccess)
              .catch(onFailure);
    };

  }

})()