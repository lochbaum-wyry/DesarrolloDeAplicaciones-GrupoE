(function () {
'use strict';
	angular.module('desappGrupoeFrontendApp')
		.factory('UserService',function ($http,$log){
			var url = 'http://localhost:8080/domain/servicesRest/user';
	
			var userService = {
				signUp: signUp,
				getUser: getUser, 
				addRoute: addRoute
			};
	
			function signUp(data) {
				
				return $http.post(url + '/signUp',data)
						.then(onSuccessSignUp)
						.catch(onFailureSignUp);
	
				function onSuccessSignUp(response) { return response.data; }
	
				function onFailureSignUp(error) {
					$log.error('Ocurrio un error: ' + error.data);
					return 'Ocurrio un error';
				}
			};
	
			function getUser(id) {
	
				return $http.get(url + '/user/' + id)
						.then(onSuccessGetUser)
						.catch(onFailureGetUser);
	
				function onSuccessGetUser(response) { return response.data; }
	
				function onFailureGetUser(error) {
					$log.error('Ocurrio un error: ' + error.data);
					return 'Ocurrio un error';	
				}
			};
	
			function addRoute(user, points, distanceInKms, fixedCosts, schedules) {
				var data = {
					user: user,
					points: points, 
					distanceInKms: distanceInKms, 
					fixedCosts: fixedCosts, 
					schedules: schedules
				};
	
				return $http.post(url + '/addRoute',data)
							.then(onSuccessAddRoute)
							.catch(onFailureAddRoute);
	
				function onSuccessGetUser(response) { return response.data; }
	
				function onFailureGetUser(error) {
					$log.error('Ocurrio un error: ' + error.data);
					return 'Ocurrio un error';	
				}
	
			}
	
			return userService;
		});
	})();
