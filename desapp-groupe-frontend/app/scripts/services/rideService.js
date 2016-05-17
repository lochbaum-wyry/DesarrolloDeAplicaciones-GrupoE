(function () {
'use strict';
angular.module('desappGrupoeFrontendApp')
	.factory('RideService',RideService);

/* @ngInyect */
function RideService($http,$log) {
    var url = 'http://localhost:8080/domain/servicesRest/ride';

    //////////////////////////
    // RouteService interface
    //////////////////////////
    var rideService = {
        requestRide: requestRide
    };

    //////////////////////////////////////////////////
    // RideService interface function implementations
    //////////////////////////////////////////////////

    /**
     * data format expected
     * { }
     */
    function requestRide(data) {
        return $http.post(url + '/requestRide',data).then(successRequestRide).catch(failureRequestRide);

        function successRequestRide(response){
            return response.data;
        }

        function failureRequestRide(error){
            $log.error('Ocurrio un error: ' + error.data);
            return 'Ocurrio un error';
        }
    };

    return rideService;
}

})();