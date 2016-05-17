(function () {
'use strict';
angular.module('desappGrupoeFrontendApp')
	.factory('RouteService',RouteService);

/* @ngInject */
function RouteService($http,$log){
		var url = 'http://localhost:8080/domain/servicesRest/route';

        //////////////////////////
        // RouteService interface
        //////////////////////////
		var routeService = {
		    getRideProposals: getRideProposals
		};

        ///////////////////////////////////////////////////
        // RouteService interface function implementations
        ///////////////////////////////////////////////////

        /**
         * data format expected
         * {
         */
		function getRideProposals(data) {
			return $http.get(url + '/getRideProposals',data).then(successGetRideProposals).catch(failureGetRideProposals);

			function onSuccessSignUp(response){
				return response.data;
			}

			function onFailureSignUp(error){
				$log.error('Ocurrio un error: ' + error.data);
				return 'Ocurrio un error';
			}
		};

		return routeService;
	}

})();