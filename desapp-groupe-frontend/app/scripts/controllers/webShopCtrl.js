(function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('WebShopCtrl', webShopCtrl );

/* @ngInject */
function webShopCtrl($localStorage,webShopService,SessionService,AlertService) {
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
            AlertService.successAlert("El Producto fue canjeado con Exito");
        };

        function onFailure(error){
            AlertService.failureAlert(error);
        };

        if(vm.user.points >= product.cost && product.stock > 0){
            webShopService.changeProduct(vm.user.id,product) 
                .then(onSuccess)
                .catch(onFailure);
        }
        else
          AlertService.failureAlert("No tienes puntos suficientes o no hay stock de ese Producto");

    };

  	function getProducts(){

        function onSuccess(result){
            vm.products = result;
        };

        function onFailure(error){
            AlertService.failureAlert(error);
        };

        webShopService.products() 
              .then(onSuccess)
              .catch(onFailure);
    };


  }

})()