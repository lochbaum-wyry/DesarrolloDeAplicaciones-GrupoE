(function () { 
'use strict';
angular.module('desappGrupoeFrontendApp')
  .factory('UserService',function ($http,$log,$localStorage){
    var url = 'http://localhost:8080/domain/servicesRest/user';

    var userService = {
      addRoute: addRoute,
      getUser: getUser
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

    function addRoute(user_id,points,distanceInKms,fixedCosts,schedules) {
      var route = {
        latLngs: points,
        distanceInKms: distanceInKms, 
        fixedCosts: fixedCosts, 
        schedules: schedules
      };

      return $http.post(url + '/' + user_id + '/addRoute' , route)
            .then(onSuccessAddRoute)
            .catch(onFailureddRoute);

      function onSuccessAddRoute(response){
        return response.data;
      }

      function onFailureddRoute(error){
        $log.error('Ocurrio un error: ' + error.data);
        return 'Ocurrio un error';  
      }
    };

    return userService;
  });

})();