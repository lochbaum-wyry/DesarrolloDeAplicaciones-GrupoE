(function () { 
'use strict';
angular.module('desappGrupoeFrontendApp')
	.factory('UserService',function ($http,$log){
		var url = 'http://localhost:8080/domain/servicesRest/user';

		var UserService = {};

		UserService.signUp = function(data){
			function onSuccessSignUp(response){
				return response.data;
			}

			function onFailureSignUp(error){
				$log.error('Ocurrio un error: ' + error.data);
				return 'Ocurrio un error';
			}
			return $http.post(url + '/signUp',data).then(onSuccessSignUp).catch(onFailureSignUp);
		};

		UserService.getUser =  function(id){

			function onSuccessGetUser(response){
				return response.data;
			}

			function onFailureGetUser(error){
				$log.error('Ocurrio un error: ' + error.data);
				return 'Ocurrio un error';	
			}
			return $http.get(url + '/user/' + id).then(onSuccessGetUser).catch(onFailureGetUser);
		};

		UserService.login =  function(email,passw){

			function onSuccessGetUser(response){
				return response.data;
			}

			function onFailureGetUser(error){
				$log.error('Ocurrio un error: ' + error.data);
				return 'Ocurrio un error';	
			}
			return $http.get(url + '/login/' + email + '/' + passw).then(onSuccessGetUser).catch(onFailureGetUser);
		};

		UserService.addRoute =  function(user,points,distanceInKms,fixedCosts,schedules) {

			var data = {
				user: user,
				points: points,
				distanceInKms: distanceInKms, 
				fixedCosts: fixedCosts, 
				schedules: schedules
			};

			function onSuccessAddRoute(response){
				return response.data;
			}

			function onFailureddRoute(error){
				$log.error('Ocurrio un error: ' + error.data);
				return 'Ocurrio un error';	
			}
			return $http.post(url + '/addRoute', data).then(onSuccessGetUser).catch(onFailureGetUser);
		};


		return UserService;
	});

})();