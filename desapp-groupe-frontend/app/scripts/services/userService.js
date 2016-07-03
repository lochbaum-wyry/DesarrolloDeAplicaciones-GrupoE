(function () { 
'use strict';
angular.module('desappGrupoeFrontendApp').factory('UserService', UserService);


function UserService($http,$log) {
  var url = 'http://localhost:8080/domain/servicesRest/user';

  var userService = {
    addRoute: addRoute,
    getUser: getUser,
    updateDataUser: updateDataUser,
    updateUser:updateUser,
    addVehicle:addVehicle,
    getUsers: getUsers
  };

  return userService;

  function getUser(user_id) {
    return $http.get(url + '/getUser/' + user_id)
      .then(onSucc)
      .catch(onFail);
  }

  function getUsers() {
    return $http.get(url + '/getUsers/')
      .then(onSucc)
      .catch(onFail);
  }

  function addRoute(user_id,points,distanceInKms,fixedCosts,schedules) {
    var route = {
      latLngs: points,
      distanceInKms: distanceInKms, 
      fixedCosts: fixedCosts, 
      schedules: schedules
    };

    return $http.post(url + '/' + user_id + '/addRoute' , route)
          .then(onSucc)
          .catch(onFail);
  }

  function updateDataUser(user,vehicle){
    return updateUser(user)
      .then ( function () { return addVehicle(user.id,vehicle); } )
      .catch(onFail);

  }

  function updateUser(user){
    return $http.post(url +  '/updateUser' , user)
        .then(onSucc)
        .catch(onFail);
  }

  function addVehicle(userId,vehicle){
    return $http.post(url +  '/addVehicle/' + userId , vehicle)
          .then(onSucc)
          .catch(onFail);
  }

  function onSucc(response) {
    return response.data;
  }

  function onFail(error){
    $log.error('Ocurrio un error: ' + error.data);
    return 'Ocurrio un error';
  }
}




})();