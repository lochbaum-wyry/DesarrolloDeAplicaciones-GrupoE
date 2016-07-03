(function () { 
'use strict';
angular.module('desappGrupoeFrontendApp')
  .factory('UserService',function ($http,$log){
    var url = 'http://localhost:8080/domain/servicesRest/user';

    var userService = {
      addRoute: addRoute,
      getUser: getUser,
      updateDataUser:updateDataUser,
      updateUser:updateUser,
      addVehicle:addVehicle,
      getUsers: getUsers
    };


    function getUser(user_id) {
        return $http.get(url + '/getUser/' + user_id)
          .then(onSuccessGetUser)
          .catch(onFailureGetUser);

          function onSuccessGetUser(response){
             return response.data;
          }
  
          function onFailureGetUser(error){
              $log.error('Ocurrio un error: ' + error.data);
              return 'Ocurrio un error';
          }
      };

    function getUsers() {
        return $http.get(url + '/getUsers/')
          .then(onSuccessGetUser)
          .catch(onFailureGetUser);

          function onSuccessGetUser(response){
             return response.data;
          }
  
          function onFailureGetUser(error){
              $log.error('Ocurrio un error: ' + error.data);
              return 'Ocurrio un error';
          }
      };

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

    };

    return userService;
  });

  function updateDataUser(user,vehicle){
    updateUser(user);
    addVehicle(user.id,vehicle);
  }

  function updateUser(user){
    return $http.post(url +  '/updateUser' , user)
          .then(onSuccessUpdateUser)
          .catch(onFailureUpdateUser);

          function onSuccessUpdateUser(response){
             return response.data;
          }
  
          function onFailureUpdateUser(error){
              $log.error('Ocurrio un error: ' + error.data);
              return 'Ocurrio un error';
          }
  };

  function addVehicle(userId,vehicle){
    return $http.post(url +  '/addVehicle/' + userId , vehicle)
          .then(onSuccessAddVehicle)
          .catch(onFailureAddVehicle);

          function onSuccessAddVehicle(response){
             return response.data;
          }
  
          function onFailureAddVehicle(error){
              $log.error('Ocurrio un error: ' + error.data);
              return 'Ocurrio un error';
          }
  };

  function onSucc(response) {
    return response.data;
  }

  function onFail(error){
    $log.error('Ocurrio un error: ' + error.data);
    return 'Ocurrio un error';
  }


})();