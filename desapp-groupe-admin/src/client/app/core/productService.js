(function () {
'use strict';
angular.module('app.core')
  .factory('productService',productService);

/* @ngInject */
function productService($http,$log){
  var url = 'http://localhost:8080/domain/servicesRest/product';

  var productService = {
    createProduct: createProduct,
    products: products
  };

  function createProduct(data) {
    return $http.post(url + '/createProduct',data)
              .then(succCreateProduct)
              .catch(failCreateProduct);

    function succCreateProduct(response){
      return response.data;
    }

    function failCreateProduct(error){
      $log.error('Ocurrio un error: ' + error.data);
      return 'Ocurrio un error';
    }
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

  return productService;
}

})();