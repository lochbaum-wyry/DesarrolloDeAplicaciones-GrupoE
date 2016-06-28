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
        requestRide: requestRide,
        pendingRequestsBy: pendingRequestsBy,
        pendingRequestsFor: pendingRequestsFor,
        cancelRequest: cancelRequest,
        rate: rate,
        rejectRequest: rejectRequest,
        getRateablesFromRide: getRateablesFromRide,
        getRateablesRides: getRateablesRides,
        acceptRequest: acceptRequest
    };

    //////////////////////////////////////////////////
    // RideService interface function implementations
    //////////////////////////////////////////////////

    /**
     * data format expected
     * { }
     */
    function getRateablesRides(userId){
        return $http.get(url + '/getRateablesRides/' + userId).then(onSuccess).catch(onFail);
    }

    function rate(rate){
        return $http.post(url + '/rate',rate).then(onSuccess).catch(onFail);
    }

    function getRateablesFromRide(rideId,userId){
        return $http.get(url + '/getRateablesFrom/' + rideId + '/' + userId).then(onSuccess).catch(onFail);
    }

    function requestRide(data) {
        return $http.post(url + '/requestRide',data).then(onSuccess).catch(onFail);
    }

    function cancelRequest(id) {
        return $http.get(url + '/cancelRequest/' + id).then(onSuccess).catch(onFail)
    }    

    function rejectRequest(id) {
        return $http.get(url + '/rejectRequest/' + id).then(onSuccess).catch(onFail)
    }    

    function acceptRequest(id) {
        return $http.get(url + '/acceptRequest/' + id).then(onSuccess).catch(onFail)
    }    


    function pendingRequestsBy(userId) {
        return $http.get(url + '/pendingRequestsBy/' + userId).then(onSuccess).catch(onFail)
    }

    function pendingRequestsFor(userId) {
        return $http.get(url + '/pendingRequestsFor/' + userId).then(onSuccess).catch(onFail)
    }

    return rideService;
}

function onSuccess(response){
    return response.data;
}

function onFail(error){
    $log.error('Ocurrio un error: ' + error.data);
    return 'Ocurrio un error';
}

})();