(function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('WebShopCtrl', webShopCtrl );

/* @ngInject */
function webShopCtrl($localStorage,webShopService,SessionService,Flash) {
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
            successAlert("El Producto fue canjeado con Exito");
        };

        function onFailure(error){
            failureAlert(error);
        };

        if(vm.user.points >= product.cost){
            webShopService.changeProduct(vm.user.id,product) 
                .then(onSuccess)
                .catch(onFailure);
        }
        else
          failureAlert("No tienes puntos suficientes");

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

  function successAlert(msg) {
        var message = '<strong>Bien! </strong>' + msg;
        var id = Flash.create('success', message, 2000, {class: 'custom-class', id: 'custom-id'}, true);
  };

  function failureAlert(error) {
        var message = '<strong>Mal! </strong> ' + error;
        var id = Flash.create('danger', message, 2000, {class: 'custom-class', id: 'custom-id'}, true);
  };

  }

})()