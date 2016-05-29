(function () {
    'use strict';

    angular
        .module('app.dashboard')
        .controller('DashboardController', DashboardController);

    DashboardController.$inject = ['$q', 'dataservice', 'logger','productService'];
    /* @ngInject */
    function DashboardController($q, dataservice, logger,productService) {
        var vm = this;
        vm.news = {
            title: 'subiAdministrador',
            description: 'generator-angular-crud allows creating entities ' +
                          'and CRUD operations very productively for AngularJS applications'
        };
        vm.messageCount = 0;
        vm.people = [];
        vm.title = 'Dashboard';

        vm.product = {'name':'','stock': 0,'cost':0} ;
        vm.products = products;
        vm.listproducts = vm.products();
        vm.createProduct = createProduct;
        

        activate();

    function createProduct(){

        function onSuccess(result){
            vm.product.name = '';
            vm.product.stock= 0;
            vm.product.cost= 0;
            vm.products();
        };

        function onFailure(error){
            vm.ERROR_MSG = error;
        };

        productService.createProduct(vm.product) 
              .then(onSuccess)
              .catch(onFailure);
    };
    
    function products(){

        function onSuccess(result){
            vm.listproducts = result;
        };

        function onFailure(error){
            vm.ERROR_MSG = error;
        };

        productService.products() 
              .then(onSuccess)
              .catch(onFailure);
    };

        function activate() {
            var promises = [getMessageCount(), getPeople()];
            return $q.all(promises).then(function() {
                //logger.info('Activated Dashboard View');
            });
        }

        function getMessageCount() {
            return dataservice.getMessageCount().then(function (data) {
                vm.messageCount = data;
                return vm.messageCount;
            });
        }

        function getPeople() {
            return dataservice.getPeople().then(function (data) {
                vm.people = data;
                return vm.people;
            });
        }
    }
})();
