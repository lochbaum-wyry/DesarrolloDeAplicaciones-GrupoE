(function () { 
'use strict';
angular.module('desappGrupoeFrontendApp')
  .factory('webShopService',webShopService);
  
  function webShopService($http,$log){
  var url = 'http://localhost:8080/domain/servicesRest/product';

  var webShopService = {
    products: products
  };

  function products() {
    return $http.get(url + '/products')
              .then(succProducts)
              .catch(failProducts);

    function succProducts(response){
      return response.data;
    }

    function failProducts(error){
      $log.error('Ocurrio un error: ' + error.data);
      return 'Ocurrio un error';
    }
  };

  return webShopService;
}

})();