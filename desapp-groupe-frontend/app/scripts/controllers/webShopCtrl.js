(function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('WebShopCtrl', webShopCtrl );

/* @ngInject */
function webShopCtrl($localStorage,webShopService,SessionService) {
  var vm = this; 

  /* String */ vm.ERROR_MSG = null;

  vm.user = $localStorage.user

  vm.search = '';
  vm.getProducts = getProducts;
  vm.changeProduct = changeProduct;
  vm.products = [];

  getProducts();

    function changeProduct(product){

      function onSuccess(result){
            SessionService.reloadUser();
            vm.user = SessionService.user();
            getProducts();
        };

        function onFailure(error){
            vm.ERROR_MSG = error;
        };

        if(vm.user.points >= product.cost){
            webShopService.changeProduct(vm.user.id,product) 
                .then(onSuccess)
                .catch(onFailure);
        }
        else
          vm.ERROR_MSG = "No tienes puntos suficientes";

    };

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