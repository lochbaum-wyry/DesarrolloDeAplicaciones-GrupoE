(function () { 
'use strict';
angular.module('desappGrupoeFrontendApp')
	.factory('UserService',function ($http,$log,$localStorage){
		var url = 'http://localhost:8080/domain/servicesRest/user';

		var userService = {
			addRoute: addRoute,
			reloadUser: reloadUser
		};

		function reloadUser(){
      		return $http.get(url + '/getUser/' + $localStorage.user.id)
        		.then(onSuccessReload)
        		.catch(onFailureReload);
    
    		function onSuccessReload(user){
    			return user;
    		}

    		function onFailureReload(error){
      			$log.error('Ocurrio un error: ' + error.data);
      			return 'Ocurrio un error';
    		}
  		};

		function addRoute(user,points,distanceInKms,fixedCosts,schedules) {
			var data = {
				user: user,
				points: points,
				distanceInKms: distanceInKms, 
				fixedCosts: fixedCosts, 
				schedules: schedules
			};
			return $http.post(url + '/addRoute', data).then(onSuccessAddRoute).catch(onFailureddRoute);


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