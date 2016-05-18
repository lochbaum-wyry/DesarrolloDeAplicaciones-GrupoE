(function () { 
'use strict';
angular.module('desappGrupoeFrontendApp')
	.factory('UserService',function ($http,$log){
		var url = 'http://localhost:8080/domain/servicesRest/user';

		var userService = {
			addRoute: addRoute
		};

		function addRoute(user,points,distanceInKms,fixedCosts,schedules) {
			var data = {
				user: user,
				points: points,
				distanceInKms: distanceInKms, 
				fixedCosts: fixedCosts, 
				schedules: schedules
			};
			return $http.post(url + '/addRoute', data).then(onSuccessGetUser).catch(onFailureGetUser);


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